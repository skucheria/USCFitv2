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
    private Bundle b2;
    private Intent myIntent;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        numSteps=0;
//        setupAccel();
//        myIntent = new Intent(MainActivity.this, Steps_Fragment.class);
//        myIntent.putExtra("step", numSteps);
//        startActivity(myIntent);

        setContentView(R.layout.activity_main);
        bottomMenu = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        fm = getSupportFragmentManager();
        f = fm.findFragmentById(R.id.fragment_container);
        ft = fm.beginTransaction();
        if (f == null) {
            f = new Steps_Fragment();
//            b2 = new Bundle();
//            b2.putInt("step", 0);
//            f.setArguments(b2);
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
//                    savedInstanceState.putInt("step", numSteps);
//                    f.setArguments(savedInstanceState);
//                    ft.add(R.id.fragment_container, f);
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



    }

    public void setupAccel(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

        numSteps = 0;
        sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);

    }


    @Override
    public void step(long timeNs) {
        numSteps++;
//        sendBroadcast(new Intent("action_location_updated"));

//        myIntent.putExtra("step", numSteps);
//        startActivity(myIntent);

//        sendData();

//        Steps_Fragment stepFrag = new Steps_Fragment();
//        b2 = new Bundle();
//        b2.putInt("step", numSteps);
//        stepFrag.setArguments(b2);
//        ft.add(R.id.fragment_container, stepFrag);
//        ft.commit();
//


//        Steps_Fragment fragment_obj = (Steps_Fragment) getSupportFragmentManager().
//                findFragmentById(R.id.fragment_container);
//
//        fragment_obj.increaseSteps(numSteps);
    }

//    public void sendData(){
//        Bundle b = new Bundle();
//        b.putInt("step", numSteps);
//
//        Steps_Fragment stepFrag = new Steps_Fragment();
//        stepFrag.setArguments(b);
//
//        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, stepFrag).commit();
//
//
//    }

    public int getSteps(){
        return this.numSteps;
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
