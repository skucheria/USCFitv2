package com.example.team8.uscfit;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.team8.uscfit.objects.User;
import com.example.team8.uscfit.pedometer.StepDetector;
import com.example.team8.uscfit.pedometer.StepListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class Steps_Fragment extends Fragment implements SensorEventListener, StepListener{
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;
    private TextView TvStep;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
//        this.numSteps = ((MainActivity) getActivity()).sendSteps();


        Query q = FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());


        q.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // for example: if you'res expecting your user's data as an object of the "User" class.
                        User user = dataSnapshot.getValue(User.class);
                        System.out.println("USER IS NULL? " + user);
                        System.out.println("STEPS FROM DATABASE:" + user.getSteps());
                        numSteps = user.getSteps();
                        TvStep.setText(TEXT_NUM_STEPS + numSteps);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // read query is cancelled.
                    }
                });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.steps_layout, container, false);


//        numSteps = 0;
        TvStep = (TextView) view.findViewById(R.id.tv_steps);
        TvStep.setText(TEXT_NUM_STEPS + numSteps );

        setupAccel();

        return view;
    }

    public void setupAccel(){
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
//        sensorManager = (SensorManager)=getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

        sensorManager.registerListener(Steps_Fragment.this, accel, SensorManager.SENSOR_DELAY_FASTEST);

    }


    @Override
    public void step(long timeNs) {
        numSteps++;
        TvStep.setText(TEXT_NUM_STEPS + numSteps);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        mDatabase.child("steps").setValue(numSteps);




        ((MainActivity) getActivity()).updateSteps(numSteps);


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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("steps", numSteps);
        System.out.println("ACTUALLY SAVING THE STATE");
    }
}
