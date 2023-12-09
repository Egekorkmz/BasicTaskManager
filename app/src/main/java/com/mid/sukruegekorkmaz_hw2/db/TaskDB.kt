package com.mid.sukruegekorkmaz_hw2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mid.sukruegekorkmaz_hw2.util.Constants

@Database(
    entities = [Task::class],
    version = 1
)
abstract class TaskRoomDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object{
        @Volatile  //it makes that instance to visible to other threads
        private var INSTANCE:TaskRoomDatabase?=null

        fun getDatabase(context: Context):TaskRoomDatabase{
            val tempInstance = INSTANCE
            if(tempInstance !=null){
                return  tempInstance
            }
            /*
            everthing in this block protected from concurrent execution by multiple threads.In this block database instance is created
            same database instance will be used. If many instance are used, it will be so expensive
             */
            synchronized(this){
                val  instance = Room.databaseBuilder(context.applicationContext, TaskRoomDatabase::class.java, Constants.DATABASENAME).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }

    }
}