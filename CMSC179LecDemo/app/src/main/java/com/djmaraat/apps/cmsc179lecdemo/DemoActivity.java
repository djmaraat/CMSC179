package com.djmaraat.apps.cmsc179lecdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

public class DemoActivity extends AppCompatActivity {

    static final int PICK_CONTACT_REQUEST = 0;
    final String DEBUG_TAG = "LOGTEST";

    private GestureDetectorCompat mDetector;

    // TODO saving persistence demo
//    static final int DAY_VIEW_MODE = 0;
//    static final int WEEK_VIEW_MODE = 1;
//
//    private SharedPreferences mPrefs;
//    private int mCurViewMode;
    // end saving persistence demo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        // TODO saving persistence demo
//        mPrefs = getPreferences(MODE_PRIVATE);
//        mCurViewMode = mPrefs.getInt("view_mode", DAY_VIEW_MODE);
//        Log.d(DEBUG_TAG, " LOG: " + mCurViewMode);
        // end saving persistence demo
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (PICK_CONTACT_REQUEST) :
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor cursor = getContentResolver().query(contactData, null, null, null, ContactsContract.Contacts.DISPLAY_NAME);
                    if (cursor != null && cursor.moveToFirst()) {
                        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        // Fetch other Contact details as you want to use
                        String number = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                        Toast.makeText(DemoActivity.this, "Contact item: " + name + " With phone num? " + number, Toast.LENGTH_SHORT).show();

                        cursor.close();
                    }
                }
                break;
        }
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG,"onDown: " + event.toString());
            startContactActivity();
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
//            Log.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());
//            Toast.makeText(DemoActivity.this, "onFling: " + event1.toString()+event2.toString(), Toast.LENGTH_SHORT).show();
//            Intent main_intent = new Intent(getApplicationContext(), DemoLec06Activity.class);
//            startActivity(main_intent);
            return true;
        }

    }

    void startContactActivity() {
        startActivityForResult(
                new Intent(Intent.ACTION_PICK,
                        ContactsContract.Contacts.CONTENT_URI),
                PICK_CONTACT_REQUEST);
    }


    @Override
    protected void onPause() {
        super.onPause();

        // TODO saving persistence demo
//        SharedPreferences.Editor ed = mPrefs.edit();
//        ed.putInt("view_mode", WEEK_VIEW_MODE);
//        ed.commit();
        // saving persistence demo
    }

    @Override
    protected void onResume() {
        super.onResume();
        // TODO saving persistence demo
//        mPrefs = getPreferences(MODE_PRIVATE);
//        mCurViewMode = mPrefs.getInt("view_mode", DAY_VIEW_MODE);
//        Log.d(DEBUG_TAG, " LOG: on resume -  " + mCurViewMode);
        // end saving persistence demo

    }
}
