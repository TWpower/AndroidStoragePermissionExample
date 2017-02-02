package com.twlab_personal.androidstoragepermissionexample;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }


    Button button_show;
    TextView textView_permission;
    Activity thisActivity;

    public void initViews(){

        thisActivity = this;
        
        textView_permission = (TextView) findViewById(R.id.textView_permission);
        if(ContextCompat.checkSelfPermission(thisActivity,Manifest.permission.READ_EXTERNAL_STORAGE )
                != PackageManager.PERMISSION_GRANTED){
            textView_permission.setText("Permission Permitted");
        }
        
        button_show = (Button)findViewById(R.id.button_show);
        button_show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Here, thisActivity is the current activity
                if (ContextCompat.checkSelfPermission(thisActivity, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity
                            , Manifest.permission.READ_EXTERNAL_STORAGE)) {

                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.

                    } else {

                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(thisActivity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}
                                , MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                        // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                }
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {

            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    textView_permission.setText("Permission Permitted");

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


}
