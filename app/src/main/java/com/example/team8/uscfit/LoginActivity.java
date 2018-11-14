package com.example.team8.uscfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;
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
                } else {
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


    public void sendMessage() {
        i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


    // Sign in method, adapted from Firebase tutorial.
    private void signIn(String email, String password) {
        if(email == null || email.isEmpty() || password == null || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Log In Not Successful. Enter valid email/password.", Toast.LENGTH_LONG).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login Was Not Successful", Toast.LENGTH_LONG).show();


                            } else {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();


                                sendMessage();

                            }
                        }
                    });
        }
    }

    private void createAccount(String email, String password) {
        if(email == null || email.isEmpty() || password == null || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Sign Up Not Successful. Enter valid email/password.", Toast.LENGTH_LONG).show();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                System.out.println("Created account NOT succesful");
                            } else {
                                System.out.println("Created user sucessfully");

                                User newUser = new User();

                                FirebaseDatabase.getInstance().getReference("users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(LoginActivity.this, "Registration Successul!", Toast.LENGTH_LONG).show();
                                            sendMessage();
                                        } else {
                                            Toast.makeText(LoginActivity.this, "Registration Failed!", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });


                            }
                        }
                    });
        }
    }






}
