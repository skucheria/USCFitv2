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

import com.example.team8.uscfit.objects.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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

        fillField();

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
                FirebaseDatabase.getInstance().getReference("users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("weight").setValue(Integer.parseInt(weight));

            }
        });

        btnHeight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText heightText = getView().findViewById(R.id.heightText);
                String height = heightText.getText().toString();
                // save height to DB
                FirebaseDatabase.getInstance().getReference("users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("height").setValue(Integer.parseInt(height));
            }
        });
        // we don't really even use this for anything so...
        btnGender.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText genderText = getView().findViewById(R.id.genderText);
                String gender = genderText.getText().toString();
                int g = 0;
                if (gender.equalsIgnoreCase("male")) g = 1;
                // save gender to DB...I guess
                FirebaseDatabase.getInstance().getReference("users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("gender").setValue(g);
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

    public void fillField(){
        Query initial= mRootRef.child("users");

        initial.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snap : dataSnapshot.getChildren()){

                    if(snap.getKey().equals(uid)){
                        User u = snap.getValue(User.class);


                        System.out.println("USER HAS ANYTHING?  " + user.getUid());

                        EditText weightText = getView().findViewById(R.id.weightText);
                        weightText.setText(Integer.toString(u.getWeight()));

                        EditText height = getView().findViewById(R.id.heightText);
                        height.setText(Integer.toString(u.getHeight()));

                        EditText gender = getView().findViewById(R.id.genderText);
                        if(u.getGender() == 1){
                            gender.setText("Male");
                        }
                        else{
                            gender.setText("Female");
                        }

                    }


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
