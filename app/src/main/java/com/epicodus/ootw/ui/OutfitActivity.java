package com.epicodus.ootw.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.ootw.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OutfitActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.of1TextView) TextView mOf1TextView;
    @Bind(R.id.of2TextView) TextView mOf2TextView;
    @Bind(R.id.of3TextView) TextView mOf3TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outfit);

        ButterKnife.bind(this);

        mOf1TextView.setOnClickListener(this);
        mOf2TextView.setOnClickListener(this);
        mOf3TextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mOf1TextView) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://oldnavy.gap.com/browse/product.do?cid=1095768&pcid=1038317&vid=1&pid=134106002"));
            startActivity(webIntent);
        }

        if (v == mOf2TextView) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://oldnavy.gap.com/browse/product.do?cid=1095768&pcid=1038317&vid=1&pid=820905002"));
            startActivity(webIntent);
        }

        if (v == mOf3TextView) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://oldnavy.gap.com/browse/product.do?cid=1095768&pcid=1038317&vid=1&pid=774604012"));
            startActivity(webIntent);
        }

    }
}
