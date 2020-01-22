package com.smartbit.shopon;

import android.content.Intent;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SystemClock.sleep(3000);
        Intent LoginIntent = new Intent(SplashActivity.this, RegisterActivity.class);
        startActivity(LoginIntent);
        finish();
    }
}
