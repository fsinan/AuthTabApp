package org.chromium.authtabapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AppLinkActivity extends AppCompatActivity {
    private static final String TAG = "AppLinkActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String message = "Redirect wasn't captured by the Auth Tab.";
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Log.i(TAG, message);
        finish();
    }
}
