package com.example.livin_mandiri.helpers

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.livin_mandiri.model.UsersModel

class SQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "LivinMandiri.db"
        private const val TABLE_USER = "USER"

        private const val KEY_ID = "id"
        private const val KEY_NAME = "name"
        private const val KEY_PHONE_NUM = "phone_num"
        private const val KEY_EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val queryCreateTable = ("CREATE TABLE $TABLE_USER ("
                + "$KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$KEY_NAME TEXT, "
                + "$KEY_PHONE_NUM TEXT, "
                + "$KEY_EMAIL TEXT)")
        db.execSQL(queryCreateTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        onCreate(db)
    }

    // 👉 Insert user baru
    fun insertUser(user: UsersModel): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(KEY_NAME, user.name)
            put(KEY_PHONE_NUM, user.phoneNum)
            put(KEY_EMAIL, user.email)
        }
        val result = db.insert(TABLE_USER, null, values)
        db.close()
        return result
    }

    // 👉 Ambil semua user
    fun getAllUser(): List<UsersModel> {
        val userList = mutableListOf<UsersModel>()
        val selectQuery = "SELECT * FROM $TABLE_USER"
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val user = UsersModel(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID)),
                    name = cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)),
                    phoneNum = cursor.getString(cursor.getColumnIndexOrThrow(KEY_PHONE_NUM)),
                    email = cursor.getString(cursor.getColumnIndexOrThrow(KEY_EMAIL))
                )
                userList.add(user)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return userList
    }

    // 👉 Cek login berdasarkan email (optional)
    fun getUserByEmail(email: String): UsersModel? {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_USER,
            arrayOf(KEY_ID, KEY_NAME, KEY_PHONE_NUM, KEY_EMAIL),
            "$KEY_EMAIL = ?",
            arrayOf(email),
            null, null, null
        )

        var user: UsersModel? = null
        if (cursor.moveToFirst()) {
            user = UsersModel(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID)),
                name = cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)),
                phoneNum = cursor.getString(cursor.getColumnIndexOrThrow(KEY_PHONE_NUM)),
                email = cursor.getString(cursor.getColumnIndexOrThrow(KEY_EMAIL))
            )
        }
        cursor.close()
        db.close()
        return user
    }
}
