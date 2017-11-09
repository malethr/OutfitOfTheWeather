package com.epicodus.ootw.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.ootw.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.goButton) Button mGoButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Typeface amaticBold= Typeface.createFromAsset(getAssets(), "fonts/Amatic-Bold.ttf");
        mAppNameTextView.setTypeface(amaticBold);
        mGoButton.setTypeface(amaticBold);

        mGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == mGoButton){
                    Intent intent = new Intent(MainActivity.this, WeatherInfoActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

}
