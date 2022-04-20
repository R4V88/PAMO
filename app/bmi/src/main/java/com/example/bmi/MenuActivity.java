package com.example.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Application main menu
 *
 * @author Rafał Sochacki s20047
 */
public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button goBmiBtn, goCaloriesBtn, goReceip, goQuiz;

        goBmiBtn = findViewById(R.id.bmiCalculatorButton);
        goCaloriesBtn = findViewById(R.id.caloriesCalculator);
        goReceip = findViewById(R.id.getReceip);
        goQuiz = findViewById(R.id.goQuiz);

        goBmiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchBmiCalculator();
            }
        });

        goCaloriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCaloriesCalculator();
            }
        });

        goReceip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchGetReceip();
            }
        });

        goQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchQuiz();
            }
        });
    }

    private void launchBmiCalculator() {
        Intent intent = new Intent(this, BmiCalculatorActivity.class);
        startActivity(intent);
    }

    private void launchCaloriesCalculator() {
        Intent intent = new Intent(this, CaloriesCalculatorActivity.class);
        startActivity(intent);
    }

    private void launchGetReceip() {
        Intent intent = new Intent(this, ReceipActivity.class);
        startActivity(intent);
    }

    private void launchQuiz() {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }
}