package com.mid.sukruegekorkmaz_hw2.db

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mid.sukruegekorkmaz_hw2.CustomRecyclerViewAdapter
import com.mid.sukruegekorkmaz_hw2.R
import com.mid.sukruegekorkmaz_hw2.util.Constants
import java.sql.Timestamp
import java.time.Duration

@Entity(tableName = Constants.TABLENAME)
data class Task(var taskName: String,
                var imgId: Int,
                var type: Int,
                var duration: Int = 0,
                var subject: String?){

    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
    override fun toString() : String{
        return "Task Name: $taskName\n" +
                "Subject: $subject\n" +
                "ImageId: $imgId"
    }

}




