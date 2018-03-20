package com.example.wilson.negociapp.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by wmora on 15/03/18.
 */
class DatabaseHelper(context : Context) : SQLiteOpenHelper(context, "NegociAppDatabase.db", null, 1) {

    private lateinit var query : String
    private val initQuery  = "CREATE TABLE IF NOT EXISTS Enterprise" +
            "(id INTEGER PRIMARY KEY autoincrement," +
            "nit TEXT,"+
            "name TEXT, " +
            "address TEXT,"+
            "email, TEXT,"+
            "phone TEXT,"+
            "mobilePhone TEXT,"+
            "mission TEXT,"+
            "vision TEXT)"

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(initQuery)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}