package com.example.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText etEmail, etPassword;
    final int MIN_PASSWORD_LENGTH = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        viewInitializations();


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

            Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show();
            // TODO call your API
            // Check this tutorial to call server api through Google Volley Library https://handyopinion.com
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void goToSignup(View v) {
        // Open your SignUp Activity if the user wants to signup

        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }
}