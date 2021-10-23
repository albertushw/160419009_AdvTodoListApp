package com.ubaya.a160419009_todoapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubaya.a160419009_todoapp.Util.MIGRATION_1_2

@Database(entities = arrayOf(Todo::class),version = 2)
abstract class TodoDatabase:RoomDatabase() {
    abstract fun todoDao():TodoDao

    //companion obj untuk memastikan hanya ada 1 object db yang tercipta
    companion object{
        @Volatile private var instance:TodoDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            TodoDatabase::class.java,
            "tododb"
        )
            .addMigrations(MIGRATION_1_2)
            .build()

        //fungsi yang terpanggil otomatis jika terpanggil abstract class room database
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
    }

}