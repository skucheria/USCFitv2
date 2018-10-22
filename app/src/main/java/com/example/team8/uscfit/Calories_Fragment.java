package com.example.team8.uscfit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.content.Context;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View.OnClickListener;


import java.util.HashMap;


public class Calories_Fragment extends Fragment {

    HashMap<String, Integer> activityToCaloriesBurned = new HashMap<String, Integer>();
    String[] physicalActivities = {"Basketball", "Swimming", "Running", "Biking", "Walking",
            "Soccer"};
    Integer[] metValues = {8, 8, 7, 7, 3, 7};
    public double bmiToPrint;
    public double calorieToPrint;
    TextView tv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        populateHashMap();
        View view = inflater.inflate(R.layout.calorie_layout, container, false);
        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText heightEditText = getView().findViewById(R.id.heightMeters);
                String heightText = heightEditText.getText().toString();
                double heightDouble = Double.parseDouble(heightText);
                EditText weightEditText = getView().findViewById(R.id.weightKilograms);
                String weightText = weightEditText.getText().toString();
                double weightDouble = Double.parseDouble(weightText);
                Spinner userInputSpinner = getView().findViewById(R.id.spinner);
                String spinnerInput = userInputSpinner.getSelectedItem().toString();
                EditText timeCompleteEditText = getView().findViewById(R.id.timeCompleteID);
                String timeCompleteText = timeCompleteEditText.getText().toString();
                double timeCompleteDouble = Double.parseDouble(timeCompleteText);
                bmiToPrint = BMICalculator(heightDouble, weightDouble);
                String bmiToPrintString = Double.toString(bmiToPrint);
                double valueFromHash = activityToCaloriesBurned.get(spinnerInput);
                calorieToPrint = valueFromHash * timeCompleteDouble;
                String calorieToPrintString = Double.toString(calorieToPrint);
                tv = getView().findViewById(R.id.textView2);
                if(tv == null){
                    System.out.println("TEXT VIEW IS NULL");
                }

                tv.setText("BMI: " + bmiToPrintString + "\n" + "Calories Burned: "
                        + calorieToPrintString);
            }
        });
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    private double BMICalculator(double personHeight, double personWeight) {
        double initialCalc = personWeight/personHeight;
        return initialCalc/personHeight;
    }

    private void populateHashMap() {
        for (int i = 0; i < 6; i++) {
            activityToCaloriesBurned.put(physicalActivities[i], metValues[i]);
        }
    }


    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }

    @Override
    public void onDetach(){
        super.onDetach();
    }
}

