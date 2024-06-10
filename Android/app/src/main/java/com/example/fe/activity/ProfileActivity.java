package com.example.fe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fe.R;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "PROFILEACTIVITY";
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("userID")) {
            userID = intent.getStringExtra("userID");
            Log.d(TAG, "Received userID: " + userID);

            // Use the userID to get the user information
            getUser(userID);
        } else {
            Log.e(TAG, "No userID found in Intent");
        }
    }

    private void getUser(String userID) {
        Toast.makeText(ProfileActivity.this, "userID" + userID, Toast.LENGTH_SHORT).show();
    }

    }
