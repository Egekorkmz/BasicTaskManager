package db

import android.util.Log
import com.mid.sukruegekorkmaz_hw2.CustomRecyclerViewAdapter
import com.mid.sukruegekorkmaz_hw2.R

object TaskSystem {
    var taskList : ArrayList<Task> = ArrayList<Task>()


    fun createData(){
        var temp : Task

        temp = NormalTask("Working", R.drawable.baseline_work_outline_24, CustomRecyclerViewAdapter.TYPE_NORMAL)
        taskList.add(temp)
        temp = StudyTask("Mobile Study", R.drawable.baseline_work_outline_24, "Gestures", CustomRecyclerViewAdapter.TYPE_STUDY)
        taskList.add(temp)
        temp = StudyTask("SQL Study", R.drawable.baseline_work_outline_24, "Queries", CustomRecyclerViewAdapter.TYPE_STUDY)
        taskList.add(temp)
        temp = NormalTask("Work Out", R.drawable.baseline_work_outline_24, CustomRecyclerViewAdapter.TYPE_NORMAL)
        taskList.add(temp)

    }

    fun addTask(){

    }

    fun updateData(old: Task, new: Task) : Boolean{
        var index = -1

        for(temp in taskList){
            if(temp.taskName == old.taskName){
                index++
                break
            }
            index++
        }

        if(index != -1){
            var temp = taskList.get(index)
            temp.taskName = new.taskName
            temp.imgId = new.imgId

            return true
        }
        return false
    }
}