package com.ubaya.a160419009_todoapp.Util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ubaya.a160419009_todoapp.model.TodoDatabase
import kotlin.contracts.Returns

val DB_NAME = "tododb"

fun buildDB(context: Context):TodoDatabase{
    val db = Room.databaseBuilder(context, TodoDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2)
        .addMigrations(MIGRATION_2_3)
        .addMigrations(MIGRATION_3_4)
        .build()
    return db
}

val MIGRATION_1_2 = object: Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 NOT NULL")
    }
}

val MIGRATION_2_3 = object : Migration(2,3){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN is_done INTEGER DEFAULT 0 NOT NULL")
    }
}

val MIGRATION_3_4 = object : Migration(3,4){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN is_done INTEGER DEFAULT 0 NOT NULL")
    }
}