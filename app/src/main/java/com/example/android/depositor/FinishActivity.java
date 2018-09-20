package com.example.android.depositor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        Intent intent = new Intent(FinishActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
