package db

import android.content.res.Resources
import android.util.Log
import com.mid.sukruegekorkmaz_hw2.CustomRecyclerViewAdapter
import com.mid.sukruegekorkmaz_hw2.R
import kotlin.collections.Map

object TaskSystem {
    var taskList : ArrayList<Task> = ArrayList<Task>()
    var create : Int = 0

    fun createData(){
        taskList.add(NormalTask("Working", R.drawable.baseline_work_outline_24))
        taskList.add(StudyTask("Mobile Study", R.drawable.baseline_work_outline_24, "Gestures"))
        taskList.add(StudyTask("SQL Study", R.drawable.baseline_work_outline_24, "Queries"))
        taskList.add(NormalTask("Work Out", R.drawable.baseline_work_outline_24))
    }

    fun addTask(){
        var flag = true
        for(temp in taskList){
            if(temp.taskName == "Work"){
                flag = false
            }
        }

        if(flag){
            taskList.add(NormalTask("Work",R.drawable.baseline_work_outline_24))
        }else{
            taskList.add(NormalTask("Work$create",R.drawable.baseline_work_outline_24))
            create++
        }
    }

    fun updateData(old: Task, new: Task) : Boolean{
        var index = -1

        //search for given task
        for(temp in taskList){
            if(temp.taskName == old.taskName){
                index++
                break
            }
            index++
        }

        //update task with new task
        if(index != -1){
            val temp = taskList[index]

            temp.taskName = new.taskName
            temp.imgId = new.imgId

            if(temp.type == CustomRecyclerViewAdapter.TYPE_STUDY)
                (temp as StudyTask).subject = (new as StudyTask).subject

            return true
        }
        return false
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
        taskList.remove(taskDel)
    }
}