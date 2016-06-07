package com.example.kaash.barcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button button;
    static final int REQUEST_CODE=7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,BarcodeActivity.class);
                startActivityForResult(intent,REQUEST_CODE);

            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode ==REQUEST_CODE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user scanned a image.
                data.getExtr;

            }
        }
    }

}
