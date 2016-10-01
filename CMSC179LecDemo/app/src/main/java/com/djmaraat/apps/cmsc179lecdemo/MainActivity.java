package com.djmaraat.apps.cmsc179lecdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextTodo;
    ListView listViewTodo;
    ArrayList<String> arrayListTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo); // using to do list sample layout

        // get references to UI widgets -- EditText, ListView
        editTextTodo = (EditText) findViewById(R.id.todoEditText);
        listViewTodo = (ListView) findViewById(R.id.todoListView);

        // call generate UI function
        generateUIAndData();
    }

    void generateUIAndData() {
        // Create the Array List of to do items
        arrayListTodo = new ArrayList<>();
        arrayListTodo.add("First List Item");
        arrayListTodo.add("Second List Item");

        //Create the Array Adapter to bind the array to the List View
        final ArrayAdapter arrayAdapterTodo = new ArrayAdapter(this, R.layout.list_simple_item, R.id.list_item, arrayListTodo);

        // Bind the Array Adapter to the List View
        listViewTodo.setAdapter(arrayAdapterTodo);

        // Add an onKeyListener to the EditText that listens for either a “D-pad center button”
        // click or the Enter key being pressed.
        editTextTodo.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER) ||
                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        arrayListTodo.add(0, editTextTodo.getText().toString());
                        arrayAdapterTodo.notifyDataSetChanged();
                        editTextTodo.setText("");
                        return true;
                    }
                return false;
            } });


    }
}