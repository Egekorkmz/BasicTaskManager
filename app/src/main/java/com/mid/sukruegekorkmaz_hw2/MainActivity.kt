package com.mid.sukruegekorkmaz_hw2

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mid.sukruegekorkmaz_hw2.databinding.ActivityMainBinding
import com.mid.sukruegekorkmaz_hw2.databinding.DialogBinding
import db.NormalTask
import db.StudyTask
import db.Task
import db.TaskSystem

class MainActivity : AppCompatActivity(), CustomRecyclerViewAdapter.RecyclerAdapterInterface {
    lateinit var layoutManager: LinearLayoutManager
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //remove status and action bar
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()

        TaskSystem.createData()

        layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvTaskList.layoutManager = layoutManager

        reloadRecycler()

        binding.btnAddTask.setOnClickListener {
            TaskSystem.addTask()

            reloadRecycler()
        }

    }

    fun displayDialog(taskSelected: Task) {
        val dialog = Dialog(this)
        val dialogbinding = DialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogbinding.root)

        if(taskSelected.type == CustomRecyclerViewAdapter.TYPE_STUDY)
            dialogbinding.edtNewSubject.visibility = View.VISIBLE

        //dialogbinding.imgDialogSocial.setImageResource(taskSelected.imgId)

        dialogbinding.btnUpdate.setOnClickListener{
            if(dialogbinding.edtNewTaskName.text.isNotEmpty()){
                lateinit var newTask: Task

                if(taskSelected.type == CustomRecyclerViewAdapter.TYPE_NORMAL) {
                    newTask = NormalTask(
                        taskSelected.id,
                        dialogbinding.edtNewTaskName.text.toString(),
                        TaskSystem.getImageID(dialogbinding.spPhotos.selectedItemPosition)
                    )
                }
                else{
                    newTask = StudyTask(
                        taskSelected.id,
                        dialogbinding.edtNewTaskName.text.toString(),
                        TaskSystem.getImageID(dialogbinding.spPhotos.selectedItemPosition),
                        dialogbinding.edtNewSubject.text.toString()
                    )
                }


                TaskSystem.updateData(taskSelected, newTask)
                reloadRecycler()
                dialog.dismiss()
            }
            else{
                Toast.makeText(this,"Please enter task name", Toast.LENGTH_LONG).show()
            }

        }

        dialogbinding.btnGoToTimer.setOnClickListener {
            var intent: Intent? = null
            intent = Intent(this@MainActivity, TimeTracker::class.java)
            intent.putExtra("id", taskSelected.id)

            startActivity(intent)
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