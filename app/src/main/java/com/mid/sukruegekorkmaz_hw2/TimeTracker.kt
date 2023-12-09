package com.mid.sukruegekorkmaz_hw2

import android.os.Bundle
import android.os.SystemClock
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mid.sukruegekorkmaz_hw2.databinding.ActivityTimeTrackerBinding
import com.mid.sukruegekorkmaz_hw2.db.TaskSystem

class TimeTracker : AppCompatActivity() {
    private lateinit var binding: ActivityTimeTrackerBinding
    private lateinit var gestureDetector : GestureDetector
    private var timerSecond : Int = 0
    private var isWorking = false
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


        gestureDetector = GestureDetector(this, DoubleTapGestureListener())

        binding.btnExit.setOnClickListener {
            if(isWorking){
                timerSecond = ((SystemClock.elapsedRealtime() - binding.chTimer.base)/1000).toInt()
            }
            TaskSystem.updateDuration(id,timerSecond)
            finish()
        }

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // Pass the touch event to the GestureDetector
        gestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    inner class DoubleTapGestureListener : GestureDetector.SimpleOnGestureListener() {

        var isFirst = true
        var lastStop : Long = 0
        override fun onDoubleTap(e: MotionEvent): Boolean {
            // Handle the double-tap event

            if(isFirst){
                binding.chTimer.start()
                isWorking = true
                Toast.makeText(this@TimeTracker,"Chronometer started.", Toast.LENGTH_LONG).show()
            }

            if (!isWorking ) {
                binding.chTimer.base = binding.chTimer.base + SystemClock.elapsedRealtime() - lastStop
                binding.chTimer.start()
                isWorking = true
                Toast.makeText(this@TimeTracker,"Chronometer started.", Toast.LENGTH_LONG).show()
            } else if(!isFirst) {
                lastStop = SystemClock.elapsedRealtime();
                binding.chTimer.stop()
                isWorking = false
                Toast.makeText(this@TimeTracker,"Chronometer stopped.", Toast.LENGTH_LONG).show()
            }

            timerSecond = ((SystemClock.elapsedRealtime() - binding.chTimer.base)/1000).toInt()

            isFirst = false

            return true
        }
    }
}
