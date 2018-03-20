package com.example.wilson.negociapp.provider

import android.content.*
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import com.example.wilson.negociapp.data.DatabaseHelper

/**
 * Created by wmora on 15/03/18.
 */
class NegociAppProvider : ContentProvider() {


    private lateinit var database: SQLiteDatabase
    private lateinit var helper: DatabaseHelper

    private lateinit var providerContext : Context

    companion object {
        private const val STRING_URI = "content://com.example.kotlincontentprovider/kotlin_contact"
        const val TABLE_ENTERPRISE = "Enterprise"
        private val CONTACT = 1
        private val CONTACT_ID = 2

        val CONTENT_URI = Uri.parse(STRING_URI)!!
        private var matcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            matcher.addURI("com.example.kotlincontentprovider", "kotlin_contact", CONTACT)
            matcher.addURI("com.example.kotlincontentprovider", "kotlin_contac", CONTACT_ID)
        }

    }


    override fun insert(p0: Uri?, p1: ContentValues?): Uri {
        initDatabase()
        val id = database.insert(TABLE_ENTERPRISE, null, p1)
        return ContentUris.withAppendedId(CONTENT_URI, id)
    }

    override fun query(uri: Uri?, columns: Array<out String>?, whereClause: String?, args: Array<out String>?, p4: String?): Cursor {

        initDatabase()
        var where = whereClause


        if (matcher.match(uri) == CONTACT_ID) {
            where = "id=${uri?.lastPathSegment}"
        }

        return database.query(TABLE_ENTERPRISE, columns, where, args, null, null, null)
    }

    override fun onCreate(): Boolean {
        return false
    }

    override fun update(p0: Uri?, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        return 0
    }

    override fun delete(p0: Uri?, p1: String?, p2: Array<out String>?): Int {
        return 0
    }

    override fun getType(p0: Uri?): String {
        return ""
    }

    //***** proper methods
    private fun initDatabase(): SQLiteDatabase {
        helper = DatabaseHelper(providerContext)
        database = helper.writableDatabase as SQLiteDatabase
        return helper.writableDatabase as SQLiteDatabase
    }

    //call this function first of start any process with the provider
    fun sendContext (context : Context) {
        providerContext = context
    }

}