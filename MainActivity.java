package com.example.hci_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonOnClick (View v) {
        Intent math = new Intent(MainActivity.this, Math_Activity.class);
        startActivity(math);
    }

    public void buttonClick (View v) {

        Intent color = new Intent(MainActivity.this, Colors_Activity.class);
        startActivity(color);
    }

    public void onClick (View v) {

        Intent word = new Intent(MainActivity.this, Words_Activity.class);
        startActivity(word);
    }


}
