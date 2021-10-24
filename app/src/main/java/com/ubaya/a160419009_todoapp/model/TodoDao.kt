package com.ubaya.a160419009_todoapp.model

import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) //KALO ADA CONFLICT MISAL PK NYA SDH ADA SBLM NYA NANTI PRIMARY KEY NYA DI REPLACE
    suspend fun insertAll(vararg todo: Todo) //vararg ini buat parameternya bisa lebih dari 1

    @Query("SELECT * FROM todo ORDER BY priority DESC")
    suspend fun selectAllTodo():List<Todo>

    @Query("SELECT * FROM todo WHERE uuid = :id")
    suspend fun selectTodo(id:Int):Todo

    @Query("UPDATE todo SET title= :title, notes = :notes, priority= :priority WHERE uuid=:uuid")
    suspend fun update(title:String, notes:String, priority:Int, uuid:Int)

    @Query("UPDATE todo SET is_done= :is_done WHERE uuid=:uuid")
    suspend fun updateIsDone(is_done:Int, uuid:Int)

    @Query("SELECT * FROM todo WHERE is_done=0 ORDER BY priority DESC")
    suspend fun selectAllIsDone():List<Todo>

    @Delete
    suspend fun deleteToDo(todo: Todo)
}