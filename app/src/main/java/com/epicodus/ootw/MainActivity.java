package com.epicodus.ootw;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mAppNameTextView;
    private Button mStartButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);
        mStartButton = (Button) findViewById(R.id.goButton);
        Typeface amaticBold= Typeface.createFromAsset(getAssets(), "fonts/Amatic-Bold.ttf");
        mAppNameTextView.setTypeface(amaticBold);
        mStartButton.setTypeface(amaticBold);
    }
}
