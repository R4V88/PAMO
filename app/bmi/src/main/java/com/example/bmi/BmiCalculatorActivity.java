package com.example.bmi;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

/**
 * BMI Calculator
 *
 * @author Rafał Sochacki s20047
 */
public class BmiCalculatorActivity extends AppCompatActivity {

    private TextView bmiView, weightView, heightView;
    private EditText weightEdit, heightEdit;
    private Button goMainBtn;
    private float weight = 0;
    private float height = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        weightView = findViewById(R.id.weightView);
        heightView = findViewById(R.id.heightView);
        bmiView = findViewById(R.id.calculatedBmiView);

        heightEdit = findViewById(R.id.heightEdit);
        heightEdit.addTextChangedListener(heightEditTextWatcher);

        weightEdit = findViewById(R.id.weightEdit);
        weightEdit.addTextChangedListener(weightEditTextWatcher);

        goMainBtn = findViewById(R.id.backToMainButton);

        goMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private final TextWatcher weightEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                weight = Integer.parseInt(s.toString());
                String userWeight = weight + " kg";
                weightView.setText(userWeight);
            } catch (NumberFormatException e) {
                weightView.setText("");
                weight = 0;
            }

            calculateBMI();
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private final TextWatcher heightEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                height = Integer.parseInt(s.toString());
                String userHeight = height + " cm";
                heightView.setText(userHeight);
            } catch (NumberFormatException e) {
                heightView.setText("");
                height = 0;
            }

            calculateBMI();
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public void calculateBMI() {
        float bmi = weight / (height / 100 * height / 100);
        DecimalFormat df = new DecimalFormat("#.##");
        String formatted = df.format(bmi);

        bmiView.setText(formatted);
    }
}