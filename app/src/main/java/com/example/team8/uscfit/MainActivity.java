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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;


//import com.google.firebase.FirebaseException;
//import com.google.firebase.auth.ActionCodeSettings;
//import com.google.firebase.auth.AuthCredential;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.EmailAuthProvider;
//import com.google.firebase.auth.FacebookAuthProvider;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuthSettings;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.auth.GithubAuthProvider;
//import com.google.firebase.auth.GoogleAuthProvider;
//import com.google.firebase.auth.PhoneAuthCredential;
//import com.google.firebase.auth.PhoneAuthProvider;
//import com.google.firebase.auth.PlayGamesAuthProvider;
//import com.google.firebase.auth.SignInMethodQueryResult;
//import com.google.firebase.auth.UserInfo;
//import com.google.firebase.auth.UserProfileChangeRequest;

import android.content.DialogInterface.OnClickListener;
import com.example.team8.uscfit.pedometer.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private TextView mTextMessage;
//    private StepDetector simpleStepDetector;
//    private SensorManager sensorManager;
//    private Sensor accel;
//    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;
    private ArrayList<String> todoItems;

//    private TextView TvStep;

    private BottomNavigationView bottomMenu;
    private FragmentManager fm;
    private Fragment f;
    private FragmentTransaction ft;
    private Bundle b2;
    private Intent myIntent;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        numSteps = 0;
        todoItems = new ArrayList<String>();
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
    }

    public void updateSteps(int num){
        this.numSteps = num;
    }

    public int sendSteps(){
        return this.numSteps;
    }

    public void updateToDo(ArrayList<String> items){
        this.todoItems = items;
    }

    public ArrayList<String> sendToDo(){
        return this.todoItems;
    }
}

