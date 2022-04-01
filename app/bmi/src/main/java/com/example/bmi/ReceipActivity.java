package com.example.bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

/**
 * Receip activity
 *
 * @author Rafał Sochacki s20047
 */
public class ReceipActivity extends AppCompatActivity {

    private TextView titleTextView;
    private ImageView dishImageView, recipeImageView;
    private Button main;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receip);

        titleTextView = findViewById(R.id.titleTextView);
        dishImageView = findViewById(R.id.dishImageView);
        recipeImageView = findViewById(R.id.recipeImageView);
        main = findViewById(R.id.backToMainMenu);

        generateView();

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void generateView() {
        final int upperbound = getResources().getInteger(R.integer.upperbound); //random from 0 to 1
        int recipe = random.nextInt(upperbound);
        switch (recipe) {
            case 0: {
                titleTextView.setText("Kebab");
                dishImageView.setImageResource(R.drawable.kebab);
                recipeImageView.setImageResource(R.drawable.przepis_kebab);
                Toast.makeText(getApplicationContext(),  "0", Toast.LENGTH_SHORT).show();
                break;
            } case 1: {
                titleTextView.setText("Burger");
                dishImageView.setImageResource(R.drawable.burger);
                recipeImageView.setImageResource(R.drawable.przepis_burger);
                Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}