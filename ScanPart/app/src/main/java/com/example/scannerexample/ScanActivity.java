package com.example.scannerexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


// https://www.youtube.com/watch?v=wfucGSKngq4
public class ScanActivity extends AppCompatActivity implements View.OnClickListener {

    Button scanBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        scanBtn = findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        scanCode();
    }

    private void scanCode() {
        IntentIntegrator integrator = new IntentIntegrator (this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);  //
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");

        // change to front-camera
        integrator.setCameraId(1);

        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                Context context = getApplicationContext();
                CharSequence text = result.getContents();
                int duration = Toast.LENGTH_LONG;

                Toast.makeText(context, text, duration).show();
            }
            else {
                Toast.makeText(this, "No Results", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}