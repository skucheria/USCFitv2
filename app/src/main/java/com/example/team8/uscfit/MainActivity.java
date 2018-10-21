package com.example.team8.uscfit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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

public class MainActivity extends AppCompatActivity implements SensorEventListener, StepListener{

    private TextView mTextMessage;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;
    private TextView TvStep;

    private BottomNavigationView bottomMenu;
    private FragmentManager fm;
    private Fragment f;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomMenu = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        fm = getSupportFragmentManager();
        f = fm.findFragmentById(R.id.fragment_container);
        ft = fm.beginTransaction();
        if (f == null) {
            f = new Steps_Fragment();
            ft.add(R.id.fragment_container, f);
        }
        else {
            ft.replace(R.id.fragment_container, f);
        }
        ft.commit();
        bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // if steps button clicked, switch to steps fragment
                if(menuItem.getItemId() == R.id.nav_steps) {
                    ft = fm.beginTransaction();
                    f = new Steps_Fragment();
                    ft.replace(R.id.fragment_container, f);
                    ft.addToBackStack(null);
                    ft.commit();
                }
                else if(menuItem.getItemId() == R.id.nav_calories) {
                    ft = fm.beginTransaction();
                    f = new Calories_Fragment();
                    ft.replace(R.id.fragment_container, f);
                    ft.addToBackStack(null);
                    ft.commit();
                }
                else if(menuItem.getItemId() == R.id.nav_todo) {
                    ft = fm.beginTransaction();
                    f = new Todo_Fragment();
                    ft.replace(R.id.fragment_container, f);
                    ft.addToBackStack(null);
                    ft.commit();
                }
                return true;
            }
        });



        setupAccel();

    }

    public void setupAccel(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

        numSteps = 0;
        sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);

    }

//


    @Override
    public void step(long timeNs) {
        numSteps++;

        FragmentManager fragMan;
        Fragment frag;

        fragMan = getSupportFragmentManager();
        frag = fragMan.findFragmentById(R.id.fragment_container);

        frag = new Steps_Fragment();
        ((Steps_Fragment) frag).increaseSteps(numSteps);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.out.println("DETECTED ACCEL CHANGE");
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);

        }
    }



}
