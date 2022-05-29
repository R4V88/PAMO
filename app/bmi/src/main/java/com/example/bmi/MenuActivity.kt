package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.*

/**
 * Application main menu
 *
 * @author Rafał Sochacki s20047
 */
class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val goBmiBtn: Button
        val goCaloriesBtn: Button
        val goReceip: Button
        val goQuiz: Button
        goBmiBtn = findViewById(R.id.bmiCalculatorButton)
        goCaloriesBtn = findViewById(R.id.caloriesCalculator)
        goReceip = findViewById(R.id.getReceip)
        goQuiz = findViewById(R.id.goQuiz)
        goBmiBtn.setOnClickListener { launchBmiCalculator() }
        goCaloriesBtn.setOnClickListener { launchCaloriesCalculator() }
        goReceip.setOnClickListener { launchGetReceip() }
        goQuiz.setOnClickListener { launchQuiz() }
    }

    private fun launchBmiCalculator() {
        val intent = Intent(this, BmiCalculatorActivity::class.java)
        startActivity(intent)
    }

    private fun launchCaloriesCalculator() {
        val intent = Intent(this, CaloriesCalculatorActivity::class.java)
        startActivity(intent)
    }

    private fun launchGetReceip() {
        val intent = Intent(this, ReceipActivity::class.java)
        startActivity(intent)
    }

    private fun launchQuiz() {
        val intent = Intent(this, QuizActivity::class.java)
        startActivity(intent)
    }
}