package com.mid.sukruegekorkmaz_hw2.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mid.sukruegekorkmaz_hw2.util.Constants

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("DELETE FROM ${Constants.TABLENAME}")
    fun deleteAllTasks()

    @Query("SELECT * FROM ${Constants.TABLENAME} ORDER BY id DESC")
    fun getAllTasks():MutableList<Task>

    @Query("SELECT * FROM ${Constants.TABLENAME} WHERE id =:id")
    fun getTaskById(id:Int): Task

    @Query("SELECT * FROM ${Constants.TABLENAME} WHERE taskName LIKE :name")
    fun getTasksByName(name:String): Task

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTask(tasks: ArrayList<Task>){
        tasks.forEach{
            insertTask(it)
        }
    }
}