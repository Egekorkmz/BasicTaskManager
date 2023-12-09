package com.mid.sukruegekorkmaz_hw2.db

import com.mid.sukruegekorkmaz_hw2.CustomRecyclerViewAdapter
import com.mid.sukruegekorkmaz_hw2.R
import kotlin.collections.Map

object TaskSystem {
    var taskList : MutableList<Task> = ArrayList<Task>()
    lateinit var taskView : TaskViewModel

    fun reloadData(){
        taskList = taskView.readAllData
    }

    fun addTask(){
        var temp = Task("Working", R.drawable.baseline_work_outline_24, CustomRecyclerViewAdapter.TYPE_NORMAL, 0, null)
        taskList.add(temp)
        taskView.addCustomer(temp)
    }

    fun addStudyTask(){
        var temp = Task("Study", R.drawable.baseline_work_outline_24, CustomRecyclerViewAdapter.TYPE_STUDY, 0, "new")
        taskList.add(temp)
        taskView.addCustomer(temp)
    }

    fun findTask(id:Int) : Int {
        var index = 0

        for(temp in taskList)
            if(temp.id == id)
                return index
            else
                index++

        return -1
    }

    fun updateData(old: Task, new: Task){
        //search for given task

        old.taskName = new.taskName
        old.imgId = new.imgId

        if(old.type == CustomRecyclerViewAdapter.TYPE_STUDY)
            old.subject = new.subject

        taskView.updateCustomer(old)
    }

    fun getImageID(index: Int = 5) : Int {
        when (index) {
            0 -> return R.drawable.email
            1 -> return R.drawable.go
            2 -> return R.drawable.pay
            3 -> return R.drawable.study
            else -> return R.drawable.baseline_work_outline_24
        }
    }

    fun deleteData(taskDel: Task){
        taskView.deleteCustomer(taskDel)
        taskList.remove(taskDel)
    }

    fun updateDuration(id: Int,time: Int){
        taskList[findTask(id)].duration += time
        taskView.updateCustomer(taskList[findTask(id)])
    }
}