package com.example.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView txtView1;
    private Button btnStart,btnStop,btnReset;
    private long startTime = 0L;
    private Handler handler = new Handler();
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView1 = findViewById(R.id.txtView1);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnReset = findViewById(R.id.btnReset);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStart.setBackgroundResource(R.drawable.gradient_pressed);
                startTime = System.currentTimeMillis();
                handler.postDelayed(runnable, 0);
                btnStart.setEnabled(false);
                btnStop.setEnabled(true);
                btnStop.setBackgroundResource(R.drawable.gradientbtn);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStop.setBackgroundResource(R.drawable.gradient_pressed);
                handler.removeCallbacks(runnable);
                btnStart.setEnabled(true);
                btnStop.setEnabled(false);
                btnStart.setBackgroundResource(R.drawable.gradientbtn);

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStop.setBackgroundResource(R.drawable.gradientbtn);
                handler.removeCallbacks(runnable);
                btnStart.setEnabled(true);
                btnStop.setEnabled(false);
                txtView1.setText("00:00:00.000");
            }
        });

        runnable = new Runnable() {

            public void run() {
                long milli=System.currentTimeMillis()-startTime;
                int millisec=(int)(milli%1000);
                int sec=(int)(milli/1000)%60;
                int min=(int)(milli/1000)/60;
                int hrs=min/60;
                min=min%60;
                txtView1.setText(String.format("%02d:%02d:%02d.%03d",hrs,min,sec,milli));
                handler.postDelayed(this, 10);
            }
        };
    }
}
