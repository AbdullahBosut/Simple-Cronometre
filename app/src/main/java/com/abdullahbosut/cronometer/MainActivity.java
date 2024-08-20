package com.abdullahbosut.cronometer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.abdullahbosut.cronometer.databinding.ActivityMainBinding;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    Runnable runnable;
    Handler handler;
    int counter;
    int minutes = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        counter = 0;
        binding.buttonRestart.setEnabled(false);
        binding.buttonStop.setEnabled(false);




    }

    public void startbutton(View v){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                stringfor();
                counter++;
                handler.postDelayed(runnable, 1000);
            }
        };
        handler.post(runnable);
        binding.buttonStart.setEnabled(false);
        binding.buttonRestart.setEnabled(true);
        binding.buttonStop.setEnabled(true);
        //mChronometer.setBase(SystemClock.elapsedRealtime());

    }
    public void stopbutton(View v){
        handler.removeCallbacks(runnable);
        stringfor();
        binding.buttonStart.setEnabled(true);

    }
    public void restartbutton(View v){
        handler.removeCallbacks(runnable);
        counter = 0;
        minutes = 0;
        stringfor();
        //binding.texttimer.setText("Time: " + counter);

        binding.buttonStart.setEnabled(true);

    }

    private void stringfor(){
        int seconds = counter;
        if (seconds > 58) {
            counter = 0;
            minutes++;
        }
        @SuppressLint("DefaultLocale")
        String str = String.format("%02d:%02d", minutes, seconds);
        binding.texttimer.setText(str);
    }













}
