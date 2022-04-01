package com.example.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Application startup activity
 *
 * @author Rafał Sochacki s20047
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton startBtn = findViewById(R.id.startButton);

        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        startBtn.setAnimation(animFadeIn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMenu();
            }
        });
    }

    private void launchMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}