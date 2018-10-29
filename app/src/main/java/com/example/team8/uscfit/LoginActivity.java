package com.example.team8.uscfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.team8.uscfit.objects.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth;
    private EditText email;
    private EditText pass;
    private Intent i;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
//        mAuth.addAuthStateListener(mAuthListener);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // user is signed in
                    System.out.println("User is already signed in");
                    sendMessage();
                }
                else {
                    // user signed out
                    System.out.println("User is not signed in");
                }
            }
        };

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

//    @Override
//    // Code from Firebase tutorial for how to set up Authentication
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mAuth = FirebaseAuth.getInstance();
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    // user is signed in
//                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
//                }
//                else {
//                    // user signed out
//                    Log.d(TAG, "onAuthStateChanged:signed_out");
//                }
//            }
//        };
//
//    }


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

                            User newUser = new User();

//                            mDatabase =

                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(LoginActivity.this, "Registratino SUCCESS with fields", Toast.LENGTH_LONG).show();
                                        sendMessage();
                                    }
                                    else{
                                        Toast.makeText(LoginActivity.this, "Registratino FAILED with fields", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });


                        }
                    }
                });

    }






}
