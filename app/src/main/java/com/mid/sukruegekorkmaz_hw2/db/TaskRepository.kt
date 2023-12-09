package com.mid.sukruegekorkmaz_hw2.db

import androidx.lifecycle.LiveData
import com.mid.sukruegekorkmaz_hw2.db.Task
import com.mid.sukruegekorkmaz_hw2.db.TaskDao
import kotlinx.coroutines.flow.Flow


/*
Used to access multiple data sources. It is used to seperate code and the architecture
 */
class TaskRepository(private val taskDao: TaskDao) {
    val readAlldata: MutableList<Task> = taskDao.getAllTasks()

    fun insertCustomer(task:Task){
        taskDao.insertTask(task)
    }
    fun insertTasks(customers:ArrayList<Task>){
        taskDao.insertAllTask(customers)
    }

    fun updateTask(task:Task){
        taskDao.updateTask(task)
    }

    fun deleteTask(task:Task){
        taskDao.deleteTask(task)
    }

    fun deleteAllCustomers(){
        taskDao.deleteAllTasks()
    }

    fun getAllTasks(): MutableList<Task> {
        return taskDao.getAllTasks()
    }

    fun getTaskById(id:Int):Task{
        return taskDao.getTaskById(id)
    }

    fun getTaskByName(name:String):Task{
        return taskDao.getTasksByName(name)
    }
}