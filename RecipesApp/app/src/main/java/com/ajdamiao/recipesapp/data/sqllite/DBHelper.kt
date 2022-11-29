package com.ajdamiao.recipesapp.data.sqllite

import DBContract.UserEntry.Companion.TABLE_NAME
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.ajdamiao.recipesapp.model.FavoriteRecipe

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun favoriteRecipe(recipe: FavoriteRecipe): Boolean {
        val db = writableDatabase
        val values = ContentValues()

        values.put(DBContract.UserEntry.RECIPE_ID, recipe.id)
        values.put(DBContract.UserEntry.RECIPE_NAME, recipe.title)
        values.put(DBContract.UserEntry.RECIPE_IMAGE, recipe.image)
        values.put(DBContract.UserEntry.RECIPE_DESCRIPTION, recipe.descriptions)

        println("AIUQIUUFAISDSAS:  " + values)

        val newRowId = db.insert(TABLE_NAME, null, values)

        newRowId.toInt().let {
            return it != -1
        }
    }

    @SuppressLint("Range")
    fun getFavoriteRecipes(): ArrayList<FavoriteRecipe> {
        val favoritesCoffees = ArrayList<FavoriteRecipe>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var name: String
        var description: String
        var image: String
        var id: String

        if (cursor!!.moveToFirst()) {
            while (!cursor.isAfterLast) {

                name = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.RECIPE_NAME))
                description = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.RECIPE_DESCRIPTION))
                image = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.RECIPE_IMAGE))
                id = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.RECIPE_ID))

                favoritesCoffees.add(FavoriteRecipe(id, name,description, image))
                cursor.moveToNext()
            }
        }
        return favoritesCoffees
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "recipeapp.db"

        private val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    DBContract.UserEntry.RECIPE_NAME + " TEXT," +
                    DBContract.UserEntry.RECIPE_DESCRIPTION + " TEXT," +
                    DBContract.UserEntry.RECIPE_IMAGE + " TEXT," +
                    DBContract.UserEntry.RECIPE_ID+ " TEXT) "

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}