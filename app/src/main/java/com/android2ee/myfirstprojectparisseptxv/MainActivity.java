package com.android2ee.myfirstprojectparisseptxv;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    /***********************************************************
     * Constant
     **********************************************************/
    /*
    * The tag for the log
    **/
    public static final String TAG = "MainActivity";
    public static final String KEY_MESSAGE = "MESSAGE";
    public static final String KEY_RESULT = "RESULT";

    /***********************************************************
     * Manage Life Cycle
     **********************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"onCreate called");
        //build the view
        setContentView(R.layout.activity_main);

    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"onStart called");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");
    }



    /***********************************************************
     * Manage Menu
     **********************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
