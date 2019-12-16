package com.example.project7777;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    public void accessViewFromThread() {
        nxtB.setText("Next");
        nxtB.setClickable(true);
    }

    @SuppressLint("HandlerLeak")
    final Handler myHandler = new Handler() {
        public void handleMessage(Message s) {
            accessViewFromThread();

        }

    };
public class MainActivity extends AppCompatActivity {

    private EditText tense;

    public static ArrayList<VerbRow> verbs;


    Button nxtB;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nxtB = findViewById(R.id.btnActTwo);

//added a new thread for loading database (lambda)
        new Thread(() -> {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(MainActivity.this);
            databaseAccess.open();
            verbs = databaseAccess.getVerbs();
            databaseAccess.close();
            Log.d("rrr", "db OK");
            myHandler.sendEmptyMessage(0);
        }).start();
// Создаем обьект, и отслеживание нажатий, жестов



        tense = findViewById(R.id.editTextTime);
    }

    public void nextActivity(View view) {

        Intent intent = new Intent(this, Main2Activity.class);

        intent.putExtra("name", tense.getText().toString());
        startActivity(intent);
    }
}

