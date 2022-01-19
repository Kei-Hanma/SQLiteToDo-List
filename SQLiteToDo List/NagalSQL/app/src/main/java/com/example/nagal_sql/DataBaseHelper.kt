package com.example.nagal_sql

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import java.io.IOException
class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, dataBaseName, null, dataBaseVersion) {
    private val Table = "ToDoList"
    companion object {
        const val dataBaseName = "ToDoDatabase"
        const val dataBaseVersion = 1
    }
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            db?.execSQL("create table $Table(id INTEGER PRIMARY KEY, ToDo text,Time text          )")
        } catch (e: SQLiteException) {
            try {
                throw IOException(e)
            } catch (e1: IOException) {
                e1.printStackTrace()
            }
        }
    }
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $Table")
        onCreate(db)
    }
    fun addData(s: String?, s1: String?): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("ToDo", s)
        contentValues.put("Time", s1)
        db.insert(Table, null, contentValues)
        return true
    }
    @SuppressLint("Range")
    fun getAllList(): Collection<String> {
        val db: SQLiteDatabase = this.readableDatabase
        val arrayList = ArrayList<String>()
        val res: Cursor = db.rawQuery("select * from $Table", null)

        while (res.moveToNext()) {
            arrayList.add(res.getString(res.getColumnIndex("ToDo")));

        }
        return arrayList
    }
}