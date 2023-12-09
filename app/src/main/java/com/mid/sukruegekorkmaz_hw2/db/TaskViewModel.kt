package com.mid.sukruegekorkmaz_hw2.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
it provides data to the UI and survive configuration changes. It acts as a communication center between repository and the UI
 */
class TaskViewModel(application:Application):AndroidViewModel(application) {
    val readAllData: MutableList<Task>
    private val repository: TaskRepository
    init {
        val customerDAO= TaskRoomDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(customerDAO)
        readAllData = repository.readAlldata
    }
    fun addCustomer(task:Task){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.insertCustomer(task)
        }
    }
    fun addCustomers(task: List<Task>){
        viewModelScope.launch(Dispatchers.IO) { // that code will be run in background thread, coroutine scope
            task.forEach{
                repository.insertCustomer(it)
            }
        }
    }
    fun deleteCustomer(task:Task){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.deleteTask(task)
        }
    }
    fun deleteAllCustomer(){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.deleteAllCustomers()
        }
    }
    fun updateCustomer(task:Task){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.updateTask(task)
        }
    }
    fun searchTask(name:String):Task{
            return repository.getTaskByName(name)
    }
}