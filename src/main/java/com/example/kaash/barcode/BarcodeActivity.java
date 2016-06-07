package com.example.kaash.barcode;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BarcodeActivity extends AppCompatActivity {

    CameraSourcePreview mPreview;
    GraphicOverlay mGraphicOverlay;
    BarcodeDetector barcodeDetector;
    BarcodeTrackerFactory barcodeFactory;
    CameraSource mCameraSource;
    ImageView PicassoImage;
    static final int REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        setContentView(R.layout.activity_barcode);
        PicassoImage=(ImageView) findViewById(R.id.imageView);
      //  String imageUri = "https://upload.wikimedia.org/wikipedia/commons/6/6b/Icecat1-300x300.svg";
        Picasso.with(this)
                .load("http://24.media.tumblr.com/tumblr_m32onohwa01qzleu4o1_400.jpg")
                .placeholder(R.drawable.placeholder)   // optional
                .error(R.drawable.error)      // optional
                .resize(425,700)                        // optional
                .into(PicassoImage);
         mPreview = (CameraSourcePreview) findViewById(R.id.preview);
        mGraphicOverlay = (GraphicOverlay) findViewById(R.id.overlay);
         barcodeDetector = new BarcodeDetector.Builder(getApplicationContext()).build();
        barcodeFactory = new BarcodeTrackerFactory(mGraphicOverlay);
        barcodeDetector.setProcessor(new MultiProcessor.Builder<>(barcodeFactory).build());
        mCameraSource = new CameraSource.Builder(BarcodeActivity.this, barcodeDetector)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedPreviewSize(1024, 1024)
                .build();
      /*  barcodeFactory.setOnNewBarcodeListener(new OnNewBarcodeListener() {
            @Override
            public void onNewItem(Barcode item) {
               Intent intent=getIntent();
                intent.putExtra("barcode", item.displayValue);
                setResult(REQUEST_CODE, intent);
              finish();
            }
        });*/


    }
    private void startCameraSource() {
        try {
            mPreview.start(mCameraSource, mGraphicOverlay);//starting the preview
        } catch (IOException e) {
            mCameraSource.release();
            mCameraSource = null;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        startCameraSource(); //start
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPreview.stop(); //stop
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCameraSource.release(); //release the resources
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_barcode, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
