package com.example.fe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class LoginActivity extends AppCompatActivity {

    private EditText userEmail, userPassword;
    private TextView navigateregister, forgotPassword;
    private Button loginButton;
    private ApiService apiService;

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("AAAAAAA");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        apiService = ApiClient.getRetrofitInstance().create(ApiService.class);

        forgotPassword = findViewById(R.id.forgot_password);
        navigateregister = findViewById(R.id.clickable_text);
        userEmail = findViewById(R.id.email);
        userPassword = findViewById(R.id.password);
        loginButton = findViewById(R.id.buttonlogin);



        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateForgotPassword();
            }
        });
        navigateregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToRegister();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userEmail.getText().toString();
                String password = userPassword.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please fill in both email and password", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(email, password);
                }
            }
        });


    }


    private void loginUser(String email, String password) {
        Account account = new Account(email, password);
        Call<Response> call = apiService.loginAccount(account);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()&&response.body() != null) {
                    Response loginResponse = response.body();
                    //So the checking is based on message that already configured in backend
                    if (loginResponse.getMessage().equals("Success To Login Your Account!")){
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        navigateToProfilePage();
                    }
                    else {
                      showErrorDialog("Login Failed", loginResponse.getMessage());
                    }
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

                    showErrorDialog("Login Failed ", errorMessage);
                    Log.e(TAG, "Login failed with response code: " + response.code() + " and message: " + errorMessage);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                showErrorDialog("Error", t.getMessage());
                Log.e(TAG, "Login request failed", t);
            }
        });
    }

    private void showErrorDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage("A"+message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    private void navigateToRegister() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void navigateForgotPassword() {
        Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
        startActivity(intent);
    }

    private void navigateToProfilePage() {
        Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
}
