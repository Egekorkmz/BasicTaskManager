package com.mid.sukruegekorkmaz_hw2

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mid.sukruegekorkmaz_hw2.databinding.DialogBinding
import db.NormalTask
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

        for(temp in TaskSystem.taskList){
            Log.d("tag",temp.toString())
        }


        layoutManager = LinearLayoutManager(this)
        layoutManager!!.orientation = LinearLayoutManager.VERTICAL
        findViewById<RecyclerView>(R.id.rv_TaskList).setLayoutManager(layoutManager)

        reloadRecycler()
    }

    fun displayDialog(taskSelected: Task) {
        val dialog = Dialog(this)
        val dialogbinding = DialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogbinding.root)

        //dialogbinding.imgDialogSocial.setImageResource(taskSelected.imgId)

        dialogbinding.btnUpdate.setOnClickListener{
            if(dialogbinding.edtTaskName.text.isNotEmpty()){
                TaskSystem.updateData(taskSelected,NormalTask(dialogbinding.edtTaskName.text.toString(), R.drawable.baseline_work_outline_24,CustomRecyclerViewAdapter.TYPE_NORMAL))

                reloadRecycler()
                dialog.dismiss()
            }
            else{
                Toast.makeText(this,"Please enter task name", Toast.LENGTH_LONG).show()
            }

        }
        dialogbinding.btnDialogClose.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    override fun displayItem(sc: Task) {
        displayDialog(sc)
    }

    private fun reloadRecycler(){
        //Fill the RecyclerView
        val adapter = CustomRecyclerViewAdapter(this, TaskSystem.taskList)
        findViewById<RecyclerView>(R.id.rv_TaskList).adapter = adapter
    }
}