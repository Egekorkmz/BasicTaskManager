package db

import android.content.res.Resources
import android.util.Log
import com.mid.sukruegekorkmaz_hw2.CustomRecyclerViewAdapter
import com.mid.sukruegekorkmaz_hw2.R
import kotlin.collections.Map

object TaskSystem {
    var taskList : ArrayList<Task> = ArrayList<Task>()
    var idCounter : Int = 0

    fun createData(){
        taskList.add(NormalTask(idCounter, "Working", R.drawable.baseline_work_outline_24))
        idCounter++
        taskList.add(StudyTask(idCounter, "Mobile Study", R.drawable.baseline_work_outline_24, "Gestures"))
        idCounter++
        taskList.add(StudyTask(idCounter, "SQL Study", R.drawable.baseline_work_outline_24, "Queries"))
        idCounter++
        taskList.add(NormalTask(idCounter, "Work Out", R.drawable.baseline_work_outline_24))
        idCounter++
    }

    fun addTask(){
        taskList.add(NormalTask(idCounter, "Work", R.drawable.baseline_work_outline_24))
        idCounter++
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

    fun updateData(old: Task, new: Task) : Boolean{
        //search for given task
        val find = findTask(old.id)

        if (find != -1) {
            //update task with new task
            if(find != -1){
                val temp = taskList[find]

                temp.taskName = new.taskName
                temp.imgId = new.imgId

                if(temp.type == CustomRecyclerViewAdapter.TYPE_STUDY)
                    (temp as StudyTask).subject = (new as StudyTask).subject

                return true
            }
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