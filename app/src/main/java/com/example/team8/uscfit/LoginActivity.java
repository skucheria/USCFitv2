package com.example.team8.uscfit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.hardware.*;
import android.app.Activity;
import android.content.Intent;
import android.widget.*;
import android.view.*;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GithubAuthProvider;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PlayGamesAuthProvider;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;

import android.content.DialogInterface.OnClickListener;
import com.example.team8.uscfit.pedometer.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth;
    private EditText email;
    private EditText pass;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();


        email = findViewById(R.id.editText);
        pass = findViewById(R.id.editText2);


        Button login = findViewById(R.id.button2);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                signIn(email.getText().toString(), pass.getText().toString());
            }
        });

        Button signup = findViewById(R.id.button3);
        signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createAccount(email.getText().toString(), pass.getText().toString());
            }
        });
    }


    public void sendMessage() {
        i = new Intent(this, MainActivity.class);
        startActivity(i);
    }



    // Sign in method, adapted from Firebase tutorial.
    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(!task.isSuccessful()) {
                            System.out.println("LOGIN IS NOT SUCESSFUL");

                        }
                        else {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            sendMessage();

                        }
                    }
                });
    }

    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // if sign in fails, display message.
                        // if sign in succeeds, state listener is notified and logic to handle signed in user is handled in listener
                        if(!task.isSuccessful()) {
                            System.out.println("Created account NOT succesful");
                        }
                        else {
                            System.out.println("Created user sucessfully");
                            sendMessage();

                        }
                    }
                });

    }




}
