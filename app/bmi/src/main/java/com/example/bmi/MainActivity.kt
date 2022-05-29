package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.content.Intent
import android.view.animation.AnimationUtils

/**
 * Application startup activity
 *
 * @author Rafał Sochacki s20047
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startBtn = findViewById<ImageButton>(R.id.startButton)
        val animFadeIn = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)
        startBtn.animation = animFadeIn
        startBtn.setOnClickListener { launchMenu() }
    }

    private fun launchMenu() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }
}