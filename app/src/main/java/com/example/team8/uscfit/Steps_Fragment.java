package com.example.team8.uscfit;

import android.media.tv.TvInputService;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

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
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.widget.*;
import android.view.*;

import android.content.DialogInterface.OnClickListener;
import com.example.team8.uscfit.pedometer.*;


public class Steps_Fragment extends Fragment {
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;
    private TextView TvStep;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.steps_layout, container, false);

        numSteps = 0;

        TvStep = (TextView) view.findViewById(R.id.tv_steps);
        TvStep.setText(TEXT_NUM_STEPS + numSteps);



        return view;
    }


    public void increaseSteps(int steps){
        TvStep.setText(TEXT_NUM_STEPS + numSteps);

    }

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("action_location_updated".equals(intent.getAction())) {
                System.out.println("Received an intent message!");
            }
        }
    };
//    IntentFilter filter = new IntentFilter("action_location_updated");
//    registerReceiver(mReceiver, filter);


}
