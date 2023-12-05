package db

import com.mid.sukruegekorkmaz_hw2.CustomRecyclerViewAdapter
import com.mid.sukruegekorkmaz_hw2.R
import java.sql.Timestamp


open class Task(taskName: String, imgId: Int, type:Int) {
    var taskName: String = taskName
    var imgId: Int = imgId
    var type: Int = type
}
class NormalTask(taskName : String, imgId : Int) : Task(taskName, imgId, CustomRecyclerViewAdapter.TYPE_NORMAL){
    override fun toString() : String{
        return "Task Name: $taskName\n" +
                "ImageId: $imgId"
    }
}

class StudyTask(taskName : String, imgId : Int, var subject: String) : Task(taskName, imgId, CustomRecyclerViewAdapter.TYPE_STUDY){

    override fun toString() : String{
        return "Task Name: $taskName\n" +
                "Subject: $subject\n" +
                "ImageId: $imgId"
    }
}



