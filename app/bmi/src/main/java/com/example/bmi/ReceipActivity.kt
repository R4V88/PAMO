package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.util.*

/**
 * Receip activity
 *
 * @author Rafał Sochacki s20047
 */
class ReceipActivity : AppCompatActivity() {
    private var titleTextView: TextView? = null
    private var dishImageView: ImageView? = null
    private var recipeImageView: ImageView? = null
    private var main: Button? = null
    private val random = Random()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receip)
        titleTextView = findViewById(R.id.titleTextView)
        dishImageView = findViewById(R.id.dishImageView)
        recipeImageView = findViewById(R.id.recipeImageView)
        main = findViewById(R.id.backToMainMenu)
        generateView()
        main.setOnClickListener(View.OnClickListener { finish() })
    }

    fun generateView() {
        val upperbound = resources.getInteger(R.integer.upperbound) //random from 0 to 1
        val recipe = random.nextInt(upperbound)
        when (recipe) {
            0 -> {
                titleTextView!!.text = "Kebab"
                dishImageView!!.setImageResource(R.drawable.kebab)
                recipeImageView!!.setImageResource(R.drawable.przepis_kebab)
                Toast.makeText(applicationContext, "0", Toast.LENGTH_SHORT).show()
            }
            1 -> {
                titleTextView!!.text = "Burger"
                dishImageView!!.setImageResource(R.drawable.burger)
                recipeImageView!!.setImageResource(R.drawable.przepis_burger)
                Toast.makeText(applicationContext, "1", Toast.LENGTH_SHORT).show()
            }
        }
    }
}