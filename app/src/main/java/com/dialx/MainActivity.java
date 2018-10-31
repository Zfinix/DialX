package com.dialx;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText input;
    private String string, number;

    private int REQUEST_PHONE_CALL = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        input = findViewById( R.id.input );
        disableSoftInputFromAppearing( input );
        input.setSelection( input.getText().length() );
        findClicks();
    }

    private void findClicks() {

        findViewById( R.id.one ).setOnClickListener( arg0 -> {
            input.setSelection( input.getText().length() );

            input.setText( input.getText() + "1" );

            arg0.playSoundEffect( SoundEffectConstants.CLICK );
            input.setSelection( input.getText().length() );

        } );

        findViewById( R.id.two ).setOnClickListener( arg0 -> {
            input.setSelection( input.getText().length() );
            input.setText( input.getText() + "2" );
            arg0.playSoundEffect( SoundEffectConstants.CLICK );
            input.setSelection( input.getText().length() );

        } );

        findViewById( R.id.three ).setOnClickListener( arg0 -> {
            input.setSelection( input.getText().length() );
            input.setText( input.getText() + "3" );
            arg0.playSoundEffect( SoundEffectConstants.CLICK );
            input.setSelection( input.getText().length() );

        } );

        findViewById( R.id.four ).setOnClickListener( arg0 -> {
            input.setSelection( input.getText().length() );
            input.setText( input.getText() + "4" );
            arg0.playSoundEffect( SoundEffectConstants.CLICK );
            input.setSelection( input.getText().length() );

        } );

        findViewById( R.id.five ).setOnClickListener( arg0 -> {
            input.setSelection( input.getText().length() );
            input.setText( input.getText() + "5" );
            arg0.playSoundEffect( SoundEffectConstants.CLICK );
            input.setSelection( input.getText().length() );

        } );

        findViewById( R.id.six ).setOnClickListener( arg0 -> {
            input.setSelection( input.getText().length() );
            input.setText( input.getText() + "6" );
            arg0.playSoundEffect( SoundEffectConstants.CLICK );
            input.setSelection( input.getText().length() );

        } );

        findViewById( R.id.seven ).setOnClickListener( arg0 -> {
            input.setSelection( input.getText().length() );
            input.setText( input.getText() + "7" );
            arg0.playSoundEffect( SoundEffectConstants.CLICK );
            input.setSelection( input.getText().length() );

        } );

        findViewById( R.id.eight ).setOnClickListener( arg0 -> {
            input.setSelection( input.getText().length() );
            input.setText( input.getText() + "8" );
            arg0.playSoundEffect( SoundEffectConstants.CLICK );
            input.setSelection( input.getText().length() );

        } );

        findViewById( R.id.nine ).setOnClickListener( arg0 -> {
            input.setSelection( input.getText().length() );
            input.setText( input.getText() + "9" );
            arg0.playSoundEffect( SoundEffectConstants.CLICK );
            input.setSelection( input.getText().length() );

        } );

        findViewById( R.id.zero ).setOnClickListener( arg0 -> {
            input.setSelection( input.getText().length() );
            input.setText( input.getText() + "0" );
            arg0.playSoundEffect( SoundEffectConstants.CLICK );
            input.setSelection( input.getText().length() );

        } );

        findViewById( R.id.star ).setOnClickListener( arg0 -> {
            input.setSelection( input.getText().length() );
            input.setText( input.getText() + "*" );
            arg0.playSoundEffect( SoundEffectConstants.CLICK );
            input.setSelection( input.getText().length() );

        } );

        findViewById( R.id.hash ).setOnClickListener( arg0 -> {
            input.setSelection( input.getText().length() );
            input.setText( input.getText() + "#" );
            arg0.playSoundEffect( SoundEffectConstants.CLICK );
            input.setSelection( input.getText().length() );

        } );

        findViewById( R.id.dial ).setOnClickListener( arg0 -> {
            arg0.playSoundEffect( SoundEffectConstants.CLICK );
            callNumber();
            input.setSelection( input.getText().length() );

        } );

        findViewById( R.id.del ).setOnClickListener( arg0 -> {


            int length = input.getText().length();
            if (length > 0) {
                input.getText().delete( length - 1, length );
            }
        } );


        findViewById( R.id.del ).setOnLongClickListener( arg0 -> {



                input.setText("");

            return true;
        } );
    }

    private String getPhoneNumber() {
        String ussd = input.getText().toString().trim();
        String hash = input.getText().toString().substring( 1 );

        if (input.getText().toString().contains("#")) {
            if (input.getText().toString().substring( 0, 1 ).equals( "#" )) {
                hash = hash.substring( 0, hash.length() - 1 );
                ussd = Uri.encode("#") + hash + Uri.encode("#");
            }else {

                hash = hash.substring( 0, hash.length() - 1 );
                ussd = Uri.encode( "*" ) + hash + Uri.encode( "#" );
            }
            Log.d( "DIAL", ussd);
        }
        return  ussd;

    }

    private void callNumber() {
        Intent call = new Intent( Intent.ACTION_CALL );
        call.setData( Uri.parse( "tel:" + getPhoneNumber() ) );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(
                Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{
                            Manifest.permission.CALL_PHONE},
                    REQUEST_PHONE_CALL );
        } else {
            startActivity( call );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
        switch (requestCode) {
            case 1000:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent call = new Intent( Intent.ACTION_CALL );
                    call.setData( Uri.parse( "tel:" + getPhoneNumber() ) );
                    if (ActivityCompat.checkSelfPermission( this, Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity( call );
                } else {
                    Toast.makeText(MainActivity.this, "Permission is denied", Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }


            public static void disableSoftInputFromAppearing(EditText editText) {
        editText.setRawInputType( InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);
    }

}
