package com.example.fe.activity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fe.R;
import com.example.fe.api.ApiClient;
import com.example.fe.api.ApiService;
import com.example.fe.model.Account;
import com.example.fe.response.Response;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;

public class ForgotPassword extends AppCompatActivity {
    private Button togglePasswordVisibility, togglePasswordVisibility2, changePassword;
    private EditText emailName, newPassword, confirmNewPassword;
    private boolean isPasswordVisible, isPasswordVisible2 = false;
    private ApiService apiService;

    private static final String TAG = "FORGOTPASSWORD";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        apiService = ApiClient.getRetrofitInstance().create(ApiService.class);

        togglePasswordVisibility = findViewById(R.id.togglePasswordVisibility);
        togglePasswordVisibility2 = findViewById(R.id.togglePasswordVisibility2);
        newPassword = findViewById(R.id.newpassword);
        confirmNewPassword = findViewById(R.id.confirmpassword);
        changePassword = findViewById(R.id.changebutton);
        emailName = findViewById(R.id.emailorname);

        togglePasswordVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });

        togglePasswordVisibility2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility2();
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailNames = emailName.getText().toString();
                String newPasswords = newPassword.getText().toString();
                String confirmNewPasswords = confirmNewPassword.getText().toString();

                if (emailNames.isEmpty() || newPasswords.isEmpty() || confirmNewPasswords.isEmpty()) {
                    Toast.makeText(ForgotPassword.this, "Please fill in the field", Toast.LENGTH_SHORT).show();
                } else {
                    if (newPasswords.equals(confirmNewPasswords)) {
                        ForgotPasswords(emailNames, newPasswords);
                    }
                    else {
                        Toast.makeText(ForgotPassword.this, "Your new password didn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Hide password
            newPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            togglePasswordVisibility.setText("👁️‍🗨️");
        } else {
            // Show password
            newPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            togglePasswordVisibility.setText("👁️‍🗨️");
        }
        // Move cursor to the end of the text
        newPassword.setSelection(newPassword.getText().length());
        isPasswordVisible = !isPasswordVisible;
    }

    private void togglePasswordVisibility2() {
        if (isPasswordVisible2) {
            // Hide password
            confirmNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            togglePasswordVisibility2.setText("👁️‍🗨️");
        } else {
            // Show password
            confirmNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            togglePasswordVisibility2.setText("👁️‍🗨️");
        }
        // Move cursor to the end of the text
        confirmNewPassword.setSelection(confirmNewPassword.getText().length());
        isPasswordVisible2 = !isPasswordVisible2;
    }

    private void ForgotPasswords(String emailName, String password) {
        Account account = new Account();
        account.forgotPass(emailName, password);
        Call<Response> call = apiService.forgotPasswordAccount(account);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()&&response.body() != null) {
                        Toast.makeText(ForgotPassword.this, "Change Password Successful", Toast.LENGTH_SHORT).show();
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

}
