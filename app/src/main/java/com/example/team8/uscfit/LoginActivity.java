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


        Button b = findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                createAccount(email.getText().toString(), pass.getText().toString());

//                signIn(email.getText().toString(), pass.getText().toString());
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
                        System.out.println("IN LOGIN FUNCTION");


//                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        if(!task.isSuccessful()) {
//                            Log.w(TAG, "signInWithEmail:failed", task.getException());
//                            errorTextView.setText(task.getException().getMessage());
                            System.out.println("LOGIN IS NOT SUCESSFUL");
                            System.out.println("LOGIN IS NOT SUCESSFUL");
                            System.out.println("LOGIN IS NOT SUCESSFUL");
                            System.out.println("LOGIN IS NOT SUCESSFUL");
                            System.out.println("LOGIN IS NOT SUCESSFUL");
                            System.out.println("LOGIN IS NOT SUCESSFUL");
                            System.out.println("LOGIN IS NOT SUCESSFUL");

                        }
                        else {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if(user.isEmailVerified()) {
                                // If the user pressed back to get back to login, we want to clear singleton data.
//                                RelevantUserSingleton.getInstance(this).clear();

                                // Create intent to go to main Home Activity of the app.
//                                i = new Intent(this, MainActivity.class);
//                                startActivity(i);
                                System.out.println("LOGIN SUCESSFUL");
                                System.out.println("LOGIN SUCESSFUL");
                                System.out.println("LOGIN SUCESSFUL");
                                System.out.println("LOGIN SUCESSFUL");
                                System.out.println("LOGIN SUCESSFUL");
                                System.out.println("LOGIN SUCESSFUL");
                                System.out.println("LOGIN SUCESSFUL");


                                sendMessage();

                            }
                            else {
//                                errorTextView.setText("Your email isn't verified yet, so we just resent you a verification email!");
                                user.sendEmailVerification()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
//                                                    Log.d(TAG, "Email sent.");
                                                }
                                            }
                                        });
                            }
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
//                            errorTextView.setText(task.getException().getMessage());
                        }
                        else {
//                            sendEmail();
//
                            System.out.println("Created user sucessfully");
                            sendMessage();

//                            errorTextView.setTextColor(Color.parseColor("#228B22"));
//                            errorTextView.setText("Awesome, check your email to verify your account!");

                            // Set up this new user in our Firebase database.
//                            FirebaseDatabase database = FirebaseDatabase.getInstance();
//                            DatabaseReference myRef = database.getReference();
//                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                            User newUser = new User(name.getText().toString().trim(), user.getEmail(), user.getUid(), 0, 0);
//                            myRef.child("users").child(user.getUid()).setValue(newUser);
                        }
                    }
                });

    }




}

//
//    public void createSignInIntent() {
//        // [START auth_fui_create_intent]
//        // Choose authentication providers
//        List<AuthUI.IdpConfig> providers = Arrays.asList(
//                new AuthUI.IdpConfig.EmailBuilder().build(),
//                new AuthUI.IdpConfig.PhoneBuilder().build(),
//                new AuthUI.IdpConfig.GoogleBuilder().build(),
//                new AuthUI.IdpConfig.FacebookBuilder().build(),
//                new AuthUI.IdpConfig.TwitterBuilder().build());
//
//        // Create and launch sign-in intent
//        startActivityForResult(
//                AuthUI.getInstance()
//                        .createSignInIntentBuilder()
//                        .setAvailableProviders(providers)
//                        .build(),
//                RC_SIGN_IN);
//        // [END auth_fui_create_intent]
//    }
//
//    // [START auth_fui_result]
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == RC_SIGN_IN) {
//            IdpResponse response = IdpResponse.fromResultIntent(data);
//
//            if (resultCode == RESULT_OK) {
//                // Successfully signed in
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                // ...
//            } else {
//                // Sign in failed. If response is null the user canceled the
//                // sign-in flow using the back button. Otherwise check
//                // response.getError().getErrorCode() and handle the error.
//                // ...
//            }
//        }
//    }
//    // [END auth_fui_result]
//
//    public void signOut() {
//        // [START auth_fui_signout]
//        AuthUI.getInstance()
//                .signOut(this)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    public void onComplete(@NonNull Task<Void> task) {
//                        // ...
//                    }
//                });
//        // [END auth_fui_signout]
//    }
//
//    public void delete() {
//        // [START auth_fui_delete]
//        AuthUI.getInstance()
//                .delete(this)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        // ...
//                    }
//                });
//        // [END auth_fui_delete]
//    }
//
//    public void themeAndLogo() {
//        List<AuthUI.IdpConfig> providers = Collections.emptyList();
//
//        // [START auth_fui_theme_logo]
//        startActivityForResult(
//                AuthUI.getInstance()
//                        .createSignInIntentBuilder()
//                        .setAvailableProviders(providers)
//                        .setLogo(R.drawable.ic_format_list_bulleted_black_24dp)      // Set logo drawable
//                        .build(),
//                RC_SIGN_IN);
//        // [END auth_fui_theme_logo]
//    }
//
//    public void privacyAndTerms() {
//        List<AuthUI.IdpConfig> providers = Collections.emptyList();
//        // [START auth_fui_pp_tos]
//        startActivityForResult(
//                AuthUI.getInstance()
//                        .createSignInIntentBuilder()
//                        .setAvailableProviders(providers)
//                        .setTosAndPrivacyPolicyUrls(
//                                "https://example.com/terms.html",
//                                "https://example.com/privacy.html")
//                        .build(),
//                RC_SIGN_IN);
//        // [END auth_fui_pp_tos]
//    }
//
