package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarEntry
import android.text.TextWatcher
import android.text.Editable
import android.view.View
import android.widget.*
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.utils.ColorTemplate
import java.lang.NumberFormatException
import java.text.DecimalFormat
import java.util.*

/**
 * BMI Calculator
 *
 * @author Rafał Sochacki s20047
 *
 * W implementacji wykorzystano dostępna bibliotekę do obsługi wykresów słupkowych:
 * https://github.com/PhilJay/MPAndroidChart
 */
class BmiCalculatorActivity : AppCompatActivity() {
    //BMI calculator section
    private var bmiView: TextView? = null
    private var weightView: TextView? = null
    private var heightView: TextView? = null
    private var weightEdit: EditText? = null
    private var heightEdit: EditText? = null
    private var goMainBtn: Button? = null
    private var weight = 0f
    private var height = 0f

    //Chart section
    private var lastBmiValue = ""
    private var total = 0f
    private var barChart: BarChart? = null
    private var xIndex = 0f

    //Sample bars to see in the app
    private val bars: ArrayList<BarEntry> = arrayListOf(
        BarEntry(xIndex++, 10.1f),
        BarEntry(xIndex++, 20.0f),
        BarEntry(xIndex++, 30.9f),
        BarEntry(xIndex++, 40.5f)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_calculator)

        //Bmi Calculator section
        weightView = findViewById(R.id.weightView)
        heightView = findViewById(R.id.heightView)
        bmiView = findViewById(R.id.calculatedBmiView)
        heightEdit = findViewById(R.id.heightEdit)
        weightEdit = findViewById(R.id.weightEdit)
        heightEdit?.run { addTextChangedListener(heightEditTextWatcher) }
        weightEdit?.run { addTextChangedListener(weightEditTextWatcher) }
        goMainBtn = findViewById(R.id.backToMainButton)

        //Chart section
        barChart = findViewById(R.id.barChart)
        val addToChartBtn = findViewById<Button>(R.id.addToChartBtn)
        updateChart()
        goMainBtn?.run {
            updateChart()
            setOnClickListener(View.OnClickListener { finish() })
        }
        addToChartBtn.setOnClickListener {
            if (total != 0f) {
                val formatter = Formatter()
                formatter.format("%.2f", total)
                lastBmiValue = formatter.toString()
                addDataToChart()
            }
        }
    }

    private val weightEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            try {
                weight = s.toString().toInt().toFloat()
                val userWeight = "$weight kg"
                weightView!!.text = userWeight
            } catch (e: NumberFormatException) {
                weightView!!.text = ""
                weight = 0f
            }
            calculateBMI()
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable) {}
    }
    private val heightEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            try {
                height = s.toString().toInt().toFloat()
                val userHeight = "$height cm"
                heightView!!.text = userHeight
            } catch (e: NumberFormatException) {
                heightView!!.text = ""
                height = 0f
            }
            calculateBMI()
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable) {}
    }

    fun calculateBMI() {
        total = weight / (height / 100 * height / 100)
        val df = DecimalFormat("#.##")
        val formatted = df.format(total.toDouble())
        if (total != 0f) {
            val formatter = Formatter()
            formatter.format("%.2f", total)
            lastBmiValue = formatter.toString()
        }
        bmiView!!.text = formatted
    }

    private fun updateChart() {
        val desc = Description()
        desc.text = ""
        barChart!!.description = desc
        val barDataSet = BarDataSet(bars, "")
        val barData = BarData(barDataSet)
        barChart!!.data = barData
        barDataSet.setColors(*ColorTemplate.JOYFUL_COLORS)
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 18f
        barChart!!.invalidate()
    }

    private fun addDataToChart() {
        if (lastBmiValue == "") {
            return
        }
        bars.add(BarEntry(xIndex, lastBmiValue.toFloat()))
        xIndex++
        updateChart()
    }
}