package com.mid.sukruegekorkmaz_hw2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import db.Task
import db.TaskSystem

class MainActivity : AppCompatActivity(), CustomRecyclerViewAdapter.RecyclerAdapterInterface {
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //remove status and action bar
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()


        TaskSystem.createData()

        for(temp in TaskSystem.TaskList){
            Log.d("tag",temp.toString())
        }


        layoutManager = LinearLayoutManager(this)
        layoutManager!!.orientation = LinearLayoutManager.VERTICAL
        findViewById<RecyclerView>(R.id.rv_TaskList).setLayoutManager(layoutManager)

        //Fill the RecyclerView
        val adapter = CustomRecyclerViewAdapter(this, TaskSystem.TaskList)
        findViewById<RecyclerView>(R.id.rv_TaskList).setAdapter(adapter)

    }

    override fun displayItem(sc: Task) {

    }
}