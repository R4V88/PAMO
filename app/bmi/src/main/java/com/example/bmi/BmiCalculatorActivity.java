package com.example.bmi;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;

/**
 * BMI Calculator
 *
 * @author Rafał Sochacki s20047
 *
 * W implementacji wykorzystano dostępna bibliotekę do obsługi wykresów słupkowych:
 * https://github.com/PhilJay/MPAndroidChart
 */

public class BmiCalculatorActivity extends AppCompatActivity {

    //BMI calculator section
    private TextView bmiView, weightView, heightView;
    private EditText weightEdit, heightEdit;
    private Button goMainBtn;
    private float weight = 0;
    private float height = 0;

    //Chart section
    private String lastBmiValue ="";
    private float total;
    private BarChart barChart;

    private int xIndex = 0;

    //Sample bars to see in the app
    private final ArrayList<BarEntry> bars = new ArrayList<>(Arrays.asList(
            new BarEntry(xIndex++, 10.1f),
            new BarEntry(xIndex++, 20.0f),
            new BarEntry(xIndex++, 30.9f),
            new BarEntry(xIndex++, 40.5f)
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        //Bmi Calculator section
        weightView = findViewById(R.id.weightView);
        heightView = findViewById(R.id.heightView);
        bmiView = findViewById(R.id.calculatedBmiView);
        heightEdit = findViewById(R.id.heightEdit);
        weightEdit = findViewById(R.id.weightEdit);

        heightEdit.addTextChangedListener(heightEditTextWatcher);
        weightEdit.addTextChangedListener(weightEditTextWatcher);

        goMainBtn = findViewById(R.id.backToMainButton);

        //Chart section
        barChart = findViewById(R.id.barChart);
        Button addToChartBtn = findViewById(R.id.addToChartBtn);

        updateChart();

        goMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addToChartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (total != 0) {
                    Formatter formatter = new Formatter();
                    formatter.format("%.2f", total);
                    lastBmiValue = formatter.toString();
                    addDataToChart();
                }
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
        total = weight / (height / 100 * height / 100);
        DecimalFormat df = new DecimalFormat("#.##");
        String formatted = df.format(total);

        if(total != 0) {
            Formatter formatter = new Formatter();
            formatter.format("%.2f", total);
            lastBmiValue = formatter.toString();
        }
        bmiView.setText(formatted);
    }

    private void updateChart(){
        Description desc = new Description();
        desc.setText("");
        barChart.setDescription(desc);

        BarDataSet barDataSet = new BarDataSet(bars, "");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);

        barDataSet.setValueTextSize(18f);
        barChart.invalidate();
    }

    private void addDataToChart(){
        if (lastBmiValue.equals("")){
            return;
        }

        bars.add(new BarEntry(xIndex, Float.parseFloat(lastBmiValue)));
        xIndex++;
        updateChart();
    }
}