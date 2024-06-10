package com.example.fe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fe.R;
import com.example.fe.api.ApiClient;
import com.example.fe.api.ApiService;
import com.example.fe.model.Account;
import com.example.fe.response.Response;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;

public class RegisterActivity extends AppCompatActivity {

    private EditText userEmail, userPassword, userName;
    private Button registerButton, togglePasswordVisibility;
    private RadioButton patient, doctor, male, female;
    private String usertype, gender;
    private TextView navigateLogin;
    private ApiService apiService;

    private boolean isPasswordVisible = false;

    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        apiService = ApiClient.getRetrofitInstance().create(ApiService.class);

        navigateLogin = findViewById(R.id.clickable_text);
        togglePasswordVisibility = findViewById(R.id.togglePasswordVisibility);
        userEmail = findViewById(R.id.email);
        userPassword = findViewById(R.id.password);
        userName = findViewById(R.id.username);
        patient = findViewById(R.id.radioButtonPatient);
        doctor = findViewById(R.id.radioButtonDoctor);
        male = findViewById(R.id.radioButtonMale);
        female = findViewById(R.id.radioButtonFemale);
        registerButton = findViewById(R.id.registerbutton);


        togglePasswordVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });

        navigateLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLogin();
            }
        });
        patient.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                usertype = "PATIENT";
            }
        });


        male.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                gender = "MALE";
            }
        });

        female.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                gender = "FEMALE";
            }
        });

        doctor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                usertype = "DOCTOR";
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userEmail.getText().toString();
                String password = userPassword.getText().toString();
                String username = userName.getText().toString();

                if (email.isEmpty() || password.isEmpty() || username.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please fill in username, email, and password", Toast.LENGTH_SHORT).show();
                } else {
                    RegisterUser(username, email, password, gender, usertype);
                }
            }
        });
    }

    private void RegisterUser(String username, String email, String password, String gender, String usertype) {
        Account account = new Account(username, email, password, gender, usertype);
        Call<Account> call = apiService.registerAccount(account);

        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, retrofit2.Response<Account> response) {
                if (response.isSuccessful()&&response.body() != null) {
                    Account account = response.body();
                    String userID = account.getUserID();
                    navigateToProfilePage(userID);
                    Toast.makeText(RegisterActivity.this, "Register Account Successful"+userID, Toast.LENGTH_SHORT).show();
                } else {
                    String errorMessage = "";
                    try {
                        if (response.errorBody() != null) {
                            errorMessage = response.errorBody().string();
                        } else {
                            errorMessage = "Unknown error";
                        }
                    } catch (IOException e) {
                        errorMessage = "Error reading error message: " + e.getMessage();
                    }

                    showErrorDialog("Register Account Faiiled ", errorMessage);
                    Log.e(TAG, "Register account failed with response code: " + response.code() + " and message: " + errorMessage);
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                showErrorDialog("Error", t.getMessage());
                Log.e(TAG, "Register request failed", t);
            }
        });
    }

    private void showErrorDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Hide password
            userPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            togglePasswordVisibility.setText("👁️‍🗨️");
        } else {
            // Show password
            userPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            togglePasswordVisibility.setText("👁️‍🗨️");
        }
        // Move cursor to the end of the text
        userPassword.setSelection(userPassword.getText().length());
        isPasswordVisible = !isPasswordVisible;
    }

    private void navigateToLogin() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void navigateToProfilePage(String userID) {
        Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
        intent.putExtra("userID", userID);
        startActivity(intent);
    }
}
