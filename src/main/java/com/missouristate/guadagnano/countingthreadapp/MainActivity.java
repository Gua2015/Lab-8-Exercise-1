package com.missouristate.guadagnano.countingthreadapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.annotation.SuppressLint;

public class MainActivity extends AppCompatActivity {
    // UI ELEMENTS: BUTTONS WILL TOGGLE IN VISIBILITY
    private TextView timeDisplay;
    private Button startBtn;
    private Button stopBtn;

    // TIME ELEMENTS
    private Timer timer;
    private long timeInMilliseconds = 0L;

    // THE HANDLER FOR THE THREAD ELEMENT
    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Activates the activity in the layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TASK 1: CREATE REFERENCES TO UI COMPONENTS
        timeDisplay = findViewById(R.id.textView1);
        startBtn = findViewById(R.id.button);
        stopBtn =  findViewById(R.id.button2);

        // TASK 2: INSTANTIATE THE OBJECT THAT MODELS THE STOPWATCH TIME
        timer = new Timer();

        //TASK 3: INSTANTIATE A HANDLER TO RUN ON THE UI THREAD
        mHandler = new Handler();
    }
    public void onStart(View view) {
        //SET THE START TIME AND CALL THE CUSTOM HANDLER
        timer.setStartTime(SystemClock.uptimeMillis());
        mHandler.postDelayed(updateTimerRunnable, 20);
    }

    private Runnable updateTimerRunnable = new Runnable() {
        @SuppressLint({"SetTextI18n", "DefaultLocale"})
        public void run() {
            // TASK 1: COMPUTE THE TIME DIFFERENCE
            timeInMilliseconds = SystemClock.uptimeMillis() - timer.getStartTime();
            timer.setTimeUpdate(timer.getStoredTime() + timeInMilliseconds);
            int time = (int) (timer.getTimeUpdate() / 1000);

            // TASK 2: COMPUTE MINUTES, SECONDS, AND MILLISECONDS
            int seconds = time % 60;

            // TASK 3: DISPLAY THE TIME IN THE TV
            timeDisplay.setText(String.format("%2d", seconds));

            // TASK 4: SPECIFY NO TIME LAPSE BETWEEN POSTING
            mHandler.postDelayed(this, 0);
        }
    };

    public void onStop(View view) {
        //UPDATE THE TIME SWAP VALUE AND CALL THE HANDLER
        timer.addStoredTime(timeInMilliseconds);
        mHandler.removeCallbacks(updateTimerRunnable);
    }
}
