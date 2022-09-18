package com.example.clapapp

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.Button as Button

class MainActivity : AppCompatActivity() {
    private lateinit var seekBar: SeekBar
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var runnable: Runnable
    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        seekBar = findViewById(R.id.sbClapping)
        handler = Handler(Looper.getMainLooper())
       val playButton = findViewById<FloatingActionButton>(R.id.fabPlay)
          playButton.setOnClickListener {
             if (mediaPlayer==null){
                 mediaPlayer = MediaPlayer.create(this,R.raw.applauding)
             }
              mediaPlayer?.start()
          }
        val pauseButton = findViewById<FloatingActionButton>(R.id.fabPause)
          pauseButton.setOnClickListener {
              mediaPlayer?.pause()
          }
        val stopButton = findViewById<FloatingActionButton>(R.id.fabStop)
           stopButton.setOnClickListener {
             mediaPlayer?.stop()
               mediaPlayer?.reset()
               mediaPlayer?.release()
               mediaPlayer = null
           }
    }
    private fun initalizeSeeekBar() {
       seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
           override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

           }

           override fun onStartTrackingTouch(p0: SeekBar?) {

           }

           override fun onStopTrackingTouch(p0: SeekBar?) {

           }

       })
        seekBar.max = mediaPlayer!!.duration
        runnable = Runnable {
          seekBar.progress = mediaPlayer!!.currentPosition
        }
    }
}