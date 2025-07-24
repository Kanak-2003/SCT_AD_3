package com.example.sct_ad_3;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView timerText;
    private Button startButton, pauseButton, resetButton;

    private Handler handler = new Handler();
    private long startTime = 0L;
    private long timeInMillis = 0L;
    private long updateTime = 0L;
    private long timeSwapBuff = 0L;

    private boolean isRunning = false;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            timeInMillis = System.currentTimeMillis() - startTime;
            updateTime = timeSwapBuff + timeInMillis;

            int secs = (int) (updateTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int millis = (int) (updateTime % 1000);

            timerText.setText(String.format("%02d:%02d:%03d", mins, secs, millis));
            handler.postDelayed(this, 10);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        timerText = findViewById(R.id.timerText);
        startButton = findViewById(R.id.startButton);
        pauseButton = findViewById(R.id.pauseButton);
        resetButton = findViewById(R.id.resetButton);

        startButton.setOnClickListener(v -> {
            if (!isRunning) {
                startTime = System.currentTimeMillis();
                handler.postDelayed(runnable, 0);
                isRunning = true;
            }
        });

        pauseButton.setOnClickListener(v -> {
            if (isRunning) {
                timeSwapBuff += timeInMillis;
                handler.removeCallbacks(runnable);
                isRunning = false;
            }
        });

        resetButton.setOnClickListener(v -> {
            handler.removeCallbacks(runnable);
            startTime = 0L;
            timeInMillis = 0L;
            timeSwapBuff = 0L;
            updateTime = 0L;
            isRunning = false;
            timerText.setText("00:00:000");
        });
    }
}