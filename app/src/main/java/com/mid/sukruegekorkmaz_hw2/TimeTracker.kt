package com.mid.sukruegekorkmaz_hw2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.mid.sukruegekorkmaz_hw2.databinding.ActivityTimeTrackerBinding
import db.TaskSystem

class TimeTracker : AppCompatActivity() {
    private lateinit var binding: ActivityTimeTrackerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_tracker)

        //binding
        binding = ActivityTimeTrackerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //remove status and action bar
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()


        val id = intent.getIntExtra("id", -1)
        if(id != -1)
            binding.tvTest.text = TaskSystem.taskList.get(TaskSystem.findTask(id)).taskName
        else
            binding.tvTest.text = "OHH Loise I am Cumming"

        var isWorking = false
        binding.button.setOnClickListener{

            if (!isWorking) {
                binding.cMeter.start()
                binding.cMeter.
                isWorking = true
            } else {
                binding.cMeter.stop()
                isWorking = false
            }

            binding.button.setText(if (isWorking) "Stop" else "Start")


        }
    }
}