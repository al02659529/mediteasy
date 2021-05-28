package com.example.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText etEmail, etPassword;
    final int MIN_PASSWORD_LENGTH = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        viewInitializations();

        try {
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
            Amplify.Auth.fetchAuthSession(
                    result -> Log.i("AmplifyQuickstart", result.toString()),
                    error -> Log.e("AmplifyQuickstart", error.toString())
            );
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

    }


    void viewInitializations() {
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);

        // To show back button in actionbar
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Checking if the input in form is valid
    boolean validateInput() {

        if (etEmail.getText().toString().equals("")) {
            etEmail.setError("Please Enter Email");
            return false;
        }
        if (etPassword.getText().toString().equals("")) {
            etPassword.setError("Please Enter Password");
            return false;
        }

        // checking the proper email format
        if (!isEmailValid(etEmail.getText().toString())) {
            etEmail.setError("Please Enter Valid Email");
            return false;
        }

        // checking minimum password Length
        if (etPassword.getText().length() < MIN_PASSWORD_LENGTH) {
            etPassword.setError("Password Length must be more than " + MIN_PASSWORD_LENGTH + "characters");
            return false;
        }

        return true;
    }

    boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Hook Click Event

    public void performLogin (View v) {
        if (validateInput()) {

            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();


            // TODO call your API
            try {
                Amplify.Auth.signIn(
                        email,
                        password,
                        result -> {
                            Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete");
                            if (result.isSignInComplete()){
                                Intent intent = new Intent(this, MainActivity.class);
                                startActivity(intent);
                            }
                        },
                        error -> {
                            Log.e("AuthQuickstart", error.toString());
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast toast = Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            });
                        }
                );
            } catch (Exception e) {
                Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show();

            }

            // Check this tutorial to call server api through Google Volley Library https://handyopinion.com

        }
    }

    public void goToSignup(View v) {
        // Open your SignUp Activity if the user wants to signup

        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }
}