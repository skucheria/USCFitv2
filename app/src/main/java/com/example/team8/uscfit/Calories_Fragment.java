package com.example.team8.uscfit;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.team8.uscfit.objects.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class Calories_Fragment extends Fragment {

    HashMap<String, Integer> activityToCaloriesBurned = new HashMap<String, Integer>();
    String[] physicalActivities = {"Basketball", "Swimming", "Running", "Biking", "Walking",
            "Soccer"};
    Integer[] metValues = {8, 8, 7, 7, 3, 7};
    public double bmiToPrint;
    public double calorieToPrint;
    TextView tv;

//    @Override
//    public void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//
//
//    }


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
                calorieToPrint = calculateCalories(valueFromHash,timeCompleteDouble);
                String calorieToPrintString = Double.toString(calorieToPrint);
                tv = getView().findViewById(R.id.textView2);
                tv.setText(setUpString(bmiToPrintString, calorieToPrintString));

            }
        });


        Query q = FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        q.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // for example: if you'res expecting your user's data as an object of the "User" class.
                        User user = dataSnapshot.getValue(User.class);
                        float bmi = user.getBmi();
                        float cals = user.getCalories();
                        tv = getView().findViewById(R.id.textView2);
                        tv.setText(setUpString(Float.toString(bmi), Float.toString(cals)));
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // read query is cancelled.
                    }
                });
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public double calculateCalories(double valueFromHash, double timeCompleteDouble) {
        return valueFromHash * timeCompleteDouble;
    }

    public String setUpString(String bmiToPrintString, String calorieToPrintString) {

        System.out.println("CALORIES BURNED: " + calorieToPrintString);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        mDatabase.child("bmi").setValue(Float.parseFloat(bmiToPrintString));
        mDatabase.child("calories").setValue(Float.parseFloat(calorieToPrintString));

        return "BMI: " + bmiToPrintString + "\n" + "Calories Burned: "
                + calorieToPrintString;

    }


    public double BMICalculator(double personHeight, double personWeight) {
        double initialCalc = personWeight/personHeight;
        return initialCalc/personHeight;
    }

    public void populateHashMap() {
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

