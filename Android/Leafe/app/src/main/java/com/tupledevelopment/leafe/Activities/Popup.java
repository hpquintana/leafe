package com.tupledevelopment.leafe.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.tupledevelopment.leafe.R;

public class Popup extends AppCompatActivity implements View.OnClickListener{

    private Button confirmButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        confirmButton = (Button) findViewById(R.id.confirmButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        confirmButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        getWindow().setLayout((int) (width*.8), (int)(height*.6));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.confirmButton:

                break;
            case R.id.cancelButton:
                finish();
                break;
        }
    }
}
