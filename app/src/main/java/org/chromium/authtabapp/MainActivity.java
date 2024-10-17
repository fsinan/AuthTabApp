package org.chromium.authtabapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.auth.AuthTabIntent;
import androidx.browser.auth.ExperimentalAuthTab;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.color.DynamicColors;

@OptIn(markerClass = ExperimentalAuthTab.class)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TEST_HOME = "https://fsinan.github.io/auth_tab";
    private static final String TEST_HOST_OWNED = "fsinan.github.io";
    private static final String TEST_HOST_OWNED_2 = "jinsukkim.github.io";
    private static final String TEST_HOST_UNOWNED = "www.chromium.org";
    private static final String TAG = "MainActivity";
    private Button mCustomSchemeCat;
    private Button mCustomSchemeDog;
    private Button mHttpsTurtle;
    private Button mHttpsOtter;
    private Button mHttpsOwl;

    private final ActivityResultLauncher<Intent> mLauncher =
            AuthTabIntent.registerActivityResultLauncher(this, this::handleAuthResult);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DynamicColors.applyToActivityIfAvailable(this);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mCustomSchemeCat = findViewById(R.id.custom_scheme_cat);
        mCustomSchemeCat.setOnClickListener(this);
        mCustomSchemeDog = findViewById(R.id.custom_scheme_dog);
        mCustomSchemeDog.setOnClickListener(this);
        mHttpsTurtle = findViewById(R.id.https_turtle);
        mHttpsTurtle.setOnClickListener(this);
        mHttpsOtter = findViewById(R.id.https_otter);
        mHttpsOtter.setOnClickListener(this);
        mHttpsOwl = findViewById(R.id.https_owl);
        mHttpsOwl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        AuthTabIntent intent = new AuthTabIntent.Builder().build();
        if (id == R.id.custom_scheme_cat) {
            intent.launch(mLauncher, Uri.parse(TEST_HOME), "catscheme");
        } else if (id == R.id.custom_scheme_dog) {
            intent.launch(mLauncher, Uri.parse(TEST_HOME), "dogscheme");
        } else if (id == R.id.https_turtle) {
            intent.launch(mLauncher, Uri.parse(TEST_HOME), TEST_HOST_OWNED_2, "/login-no.html");
        } else if (id == R.id.https_otter) {
            intent.launch(mLauncher, Uri.parse(TEST_HOME), TEST_HOST_OWNED, "/otter.html");
        } else if (id == R.id.https_owl) {
            intent.launch(mLauncher, Uri.parse(TEST_HOME), TEST_HOST_UNOWNED, "/chromium-projects");
        }
    }

    private void handleAuthResult(AuthTabIntent.AuthResult result) {
        @SuppressLint("SwitchIntDef") String message = switch (result.resultCode) {
            case AuthTabIntent.RESULT_OK -> "Received auth result.";
            case AuthTabIntent.RESULT_CANCELED -> "AuthTab canceled.";
            case AuthTabIntent.RESULT_VERIFICATION_FAILED -> "AuthTab verification failed.";
            case AuthTabIntent.RESULT_VERIFICATION_TIMED_OUT -> "AuthTab verification timed out.";
            default -> "AuthTab unknown result.";
        };
        if (result.resultCode == AuthTabIntent.RESULT_OK) {
            message += " Uri: " + result.resultUri;
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Log.i(TAG, message);
    }
}
