package com.example.team8.uscfit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.hardware.*;
import android.app.Activity;
import android.content.Intent;
import android.widget.*;
import android.view.*;

import android.content.DialogInterface.OnClickListener;
import com.example.team8.uscfit.pedometer.*;

import java.util.HashMap;

public class MainActivity extends FragmentActivity implements SensorEventListener, StepListener {

    private TextView mTextMessage;

    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;
    private TextView TvStep;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    setContentView(R.layout.todo_layout);
                    return true;
                case R.id.navigation_dashboard:

//                    setContentView(R.layout.calorie_layout);

                    return true;
                case R.id.navigation_notifications:
//                    mTextMessage.setText("Steps");
//                    setContentView(R.layout.steps_layout);


                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);


         TvStep = (TextView) findViewById(R.id.tv_steps);

        numSteps = 0;
        sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);

        TvStep.setText(TEXT_NUM_STEPS + numSteps);



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        TvStep.setText(TEXT_NUM_STEPS + numSteps);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//            System.out.println("DETECTED ACCEL CHANGE");

//            float x,y,z;
//            x = event.values[0];
//            y = event.values[1];
//            z = event.values[2];
//
//            System.out.println("X acceleration value: " + x);
//            System.out.println("Y acceleration value: " + y);
//            System.out.println("Z acceleration value: " + z);



//            if(x > .450 || x < -.450){ //consider this a step
//                this.step(0);
//            }


            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);

        }
    }

}
