package com.example.bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;
import java.util.Objects;

/**
 * Calories calculator with Benedict-Harris formula
 *
 * @author Rafał Sochacki s20047
 */
public class CaloriesCalculatorActivity extends AppCompatActivity {

    private Button female, male, calculate, main;
    private TextInputLayout inputHeightLayout, inputWeightLayout, inputAgeLayout;
    private TextView result;
    private float weight, height, calories;
    private int age;
    private String gender;

    private final DecimalFormat df = new DecimalFormat("#.##");
    private String formatted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_calculator);

        female = findViewById(R.id.chooseFemale);
        male = findViewById(R.id.chooseMale);

        calculate = findViewById(R.id.calculateCalories);
        main = findViewById(R.id.returnToMainMenu);

        inputHeightLayout = findViewById(R.id.inputHeightLayout);
        inputWeightLayout = findViewById(R.id.inputWeightLayout);
        inputAgeLayout = findViewById(R.id.inputAgeLayout);

        result = findViewById(R.id.showResult);

        main = findViewById(R.id.returnToMainMenu);

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = "female";
            }
        });

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = "male";
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getResult();
            }
        });

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getResult() {
        height = Float.parseFloat(Objects.requireNonNull(inputHeightLayout.getEditText()).getText().toString().trim());
        weight = Float.parseFloat(Objects.requireNonNull(inputWeightLayout.getEditText()).getText().toString().trim());
        age = Integer.parseInt(Objects.requireNonNull(inputAgeLayout.getEditText()).getText().toString().trim());

        if(gender != null) {
            switch (gender) {
                case ("male"): {
                    calories = 66.47f + (13.7f * weight) + (5.0f * height) - (6.76f * age);
                    formatted = df.format(calories);
                    result.setText(formatted);
                    break;
                }
                case ("female"): {
                    calories = 655.1f + (9.567f * weight) + (1.85f * height) - (4.68f * age);
                    formatted = df.format(calories);
                    result.setText(formatted);
                    break;
                }
            }
        } else
            Toast.makeText(getApplicationContext(), "Choose gender!", Toast.LENGTH_SHORT).show();
    }
}