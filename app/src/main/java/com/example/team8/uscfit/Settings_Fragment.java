package com.example.team8.uscfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Settings_Fragment extends Fragment {
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = user.getUid();
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    Button btnWeight, btnHeight, btnGender;
    EditText txtWeight, txtHeight, txtGender;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.settings_layout, container, false);
        Button btnWeight = view.findViewById(R.id.btn_weight);
        Button btnHeight = view.findViewById(R.id.btn_height);
        Button btnGender = view.findViewById(R.id.btn_gender);
        Button btnLogout = view.findViewById(R.id.btnLogout);

        btnWeight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText weightText = getView().findViewById(R.id.weightText);
                String weight = weightText.getText().toString();
                // save weight to DB
            }
        });

        btnHeight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText heightText = getView().findViewById(R.id.heightText);
                String height = heightText.getText().toString();
                // save height to DB
            }
        });
        // we don't really even use this for anything so...
        btnGender.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText genderText = getView().findViewById(R.id.genderText);
                String gender = genderText.getText().toString();
                // save gender to DB...I guess
            }
        });

        btnLogout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // logout and bring user back to login page
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent();
                intent.setClass(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }
}
