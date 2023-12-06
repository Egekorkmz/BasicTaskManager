package db

import android.os.Parcel
import android.os.Parcelable
import com.mid.sukruegekorkmaz_hw2.CustomRecyclerViewAdapter
import com.mid.sukruegekorkmaz_hw2.R
import java.sql.Timestamp
import java.time.Duration


open class Task(var id:Int, var taskName: String, var imgId: Int, var type: Int, var duration: Int = 0)

class NormalTask(id: Int, taskName : String, imgId : Int) : Task(id ,taskName, imgId, CustomRecyclerViewAdapter.TYPE_NORMAL){
    override fun toString() : String{
        return "Task Name: $taskName\n" +
                "ImageId: $imgId"
    }
}

class StudyTask(id: Int, taskName : String, imgId : Int, var subject: String) : Task(id, taskName, imgId, CustomRecyclerViewAdapter.TYPE_STUDY){
    override fun toString() : String{
        return "Task Name: $taskName\n" +
                "Subject: $subject\n" +
                "ImageId: $imgId"
    }
}



