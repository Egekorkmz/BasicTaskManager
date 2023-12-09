package com.mid.sukruegekorkmaz_hw2

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mid.sukruegekorkmaz_hw2.databinding.ActivityMainBinding
import com.mid.sukruegekorkmaz_hw2.databinding.DialogBinding
import com.mid.sukruegekorkmaz_hw2.db.Task
import com.mid.sukruegekorkmaz_hw2.db.TaskRepository
import com.mid.sukruegekorkmaz_hw2.db.TaskRoomDatabase
import com.mid.sukruegekorkmaz_hw2.db.TaskSystem
import com.mid.sukruegekorkmaz_hw2.db.TaskViewModel

class MainActivity : AppCompatActivity(), CustomRecyclerViewAdapter.RecyclerAdapterInterface {
    lateinit var layoutManager: LinearLayoutManager
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TaskSystem.taskView = ViewModelProvider(this).get(TaskViewModel::class.java)

        //binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        //remove status and action bar
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()

        TaskSystem.reloadData()

        layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvTaskList.layoutManager = layoutManager

        reloadRecycler()

        binding.btnAddTask.setOnClickListener {
            TaskSystem.addTask()

            reloadRecycler()
        }
        binding.btnAddStudyTask.setOnClickListener {
            TaskSystem.addStudyTask()

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
                    newTask = Task(
                        dialogbinding.edtNewTaskName.text.toString(),
                        TaskSystem.getImageID(dialogbinding.spPhotos.selectedItemPosition),
                        CustomRecyclerViewAdapter.TYPE_NORMAL,
                        0,
                        null)
                }
                else{
                    newTask = Task(
                        dialogbinding.edtNewTaskName.text.toString(),
                        TaskSystem.getImageID(dialogbinding.spPhotos.selectedItemPosition),
                        CustomRecyclerViewAdapter.TYPE_NORMAL,
                        0,
                        dialogbinding.edtNewSubject.text.toString())
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

        dialogbinding.btnDialogClose.setOnClickListener {
            reloadRecycler()
            dialog.dismiss()
        }
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