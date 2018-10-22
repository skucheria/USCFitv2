package com.example.team8.uscfit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
    private BottomNavigationView.OnNavigationItemSelectedListener navListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment  selectedFragment = null;
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();


            switch (item.getItemId()) {
                case R.id.nav_steps:
                    selectedFragment = new Steps_Fragment();

                    transaction.replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                case R.id.nav_calories:
                    selectedFragment = new Calories_Fragment();
//                    setContentView(R.layout.calorie_layout);
                    transaction.replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                case R.id.nav_todo:
                    selectedFragment = new Todo_Fragment();

                    transaction.replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
//                    mTextMessage.setText("Steps");
//                    setContentView(R.layout.todo_layout);


//                    return true;
            }
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

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
        navigation.setOnNavigationItemSelectedListener(navListener);
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
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);

        }
    }

}
