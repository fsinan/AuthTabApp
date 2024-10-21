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
        String message = "Received intent for Otter \uD83E\uDDA6.";
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Log.i(TAG, message);
        finish();
    }
}
