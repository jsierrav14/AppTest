package com.app.ing.apptest.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.app.ing.apptest.model.UserDatabase


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "user_db.db", null, 1) {

    companion object {
        val TABLE_USER: String = "user"
        val NAME: String = "name"
        val IMGURL: String = "imgurl"
        val PHONE: String = "phone"
        val EMAIL: String = "email"
        val LOCATION: String = "location"


    }

    //Creating table
    val USER_DATABASE_CREATE =
            "CREATE TABLE if not exists " + TABLE_USER + " (" +
                    "${NAME} text," +
                    "${IMGURL} text," +
                    "${PHONE} text," +
                    "${EMAIL} text," +
                    "${LOCATION} text" +
                    ")"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(USER_DATABASE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    //Here inserting student data.
    fun insertUserData(name: String, imgUrl: String, phone:String,email:String, location:String): Long {
        val values = ContentValues()
        values.put(NAME, name)
        values.put(IMGURL,imgUrl )
        values.put(PHONE,phone)
        values.put(EMAIL,email)
        values.put(LOCATION,location)
        return getWritableDatabase().insert(TABLE_USER, null, values)
    }

    //Getting all students list
    fun getAllUserData(): ArrayList<UserDatabase> {
        val userList: ArrayList<UserDatabase> = ArrayList<UserDatabase>()
        val cursor: Cursor = getReadableDatabase().query(TABLE_USER, arrayOf(NAME, IMGURL, PHONE, EMAIL, LOCATION), null, null, null, null, null)
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst()
                if (cursor.getCount() > 0) {
                    do {
                        val name: String = cursor.getString(cursor.getColumnIndex(NAME))
                        val imgUrl: String = cursor.getString(cursor.getColumnIndex(IMGURL))
                        val phone: String = cursor.getString(cursor.getColumnIndex(PHONE))
                        val email: String = cursor.getString(cursor.getColumnIndex(EMAIL))
                        val location: String = cursor.getString(cursor.getColumnIndex(LOCATION))

                        userList.add(UserDatabase(name,location,email, imgUrl, phone))
                    } while ((cursor.moveToNext()))
                }
            }
        } finally {
            cursor.close()
        }

        return userList
    }

}