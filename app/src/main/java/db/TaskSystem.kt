package db

import com.mid.sukruegekorkmaz_hw2.CustomRecyclerViewAdapter
import com.mid.sukruegekorkmaz_hw2.R

object TaskSystem {
    var TaskList : ArrayList<Task> = ArrayList<Task>()


    fun createData(){
        var temp : Task

        temp = NormalTask("Working", R.drawable.baseline_work_outline_24, CustomRecyclerViewAdapter.TYPE_NORMAL)
        TaskList.add(temp)
        temp = StudyTask("Mobile Study", R.drawable.baseline_work_outline_24, "Gestures", CustomRecyclerViewAdapter.TYPE_STUDY)
        TaskList.add(temp)
        temp = StudyTask("SQL Study", R.drawable.baseline_work_outline_24, "Queries", CustomRecyclerViewAdapter.TYPE_STUDY)
        TaskList.add(temp)
        temp = NormalTask("Work Out", R.drawable.baseline_work_outline_24, CustomRecyclerViewAdapter.TYPE_NORMAL)
        TaskList.add(temp)

    }
}