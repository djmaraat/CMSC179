package com.djmaraat.apps.cmsc179lecdemo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class DemoLec06Activity extends AppCompatActivity {

    EditText editTextNote;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the user interface layout for this Activity
        // The layout file is defined in the project res/layout/activity_demo_lec06.xml file
        setContentView(R.layout.activity_demo_lec06);

        sharedPreferences = getPreferences(MODE_PRIVATE);
        editTextNote = (EditText) findViewById(R.id.editTextItemField);

        if (!sharedPreferences.getString("new_note", "").isEmpty()) {
            editTextNote.setText(sharedPreferences.getString("new_note", ""));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editorPref = sharedPreferences.edit();
        editorPref.putString("new_note", editTextNote.getText().toString());
//        editorPref.putString("new_note", "THE APP WAS PAUSED");
        editorPref.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!sharedPreferences.getString("new_note", "").isEmpty()) {
            editTextNote.setText(sharedPreferences.getString("new_note", ""));
        }
    }
}
