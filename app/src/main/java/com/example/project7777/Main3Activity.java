package com.example.project7777;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Main3Activity extends AppCompatActivity {
    Context context=this;

    String shoresh;
    String time;
    List<VerbRow> verbs = MainActivity.verbs;
    long counter = 0;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        shoresh = getIntent().getStringExtra("shoresh");
        time = getIntent().getStringExtra("time");

<<<<<<< Updated upstream
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        verbs = databaseAccess.getVerbs();
        databaseAccess.close();
=======
//        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
//        databaseAccess.open();
//        verbs = databaseAccess.getVerbs();
//        databaseAccess.close();
>>>>>>> Stashed changes


        List<String> results = new ArrayList<>();

        for (int i = 0; i < verbs.size(); i++) {
            VerbRow verb = verbs.get(i);
            counter++;
            // Log.d("rrr_l", verb.toString()+" , "+counter);
            //   results.add(verb.toString()+" , "+counter);
            if (verb.toString().equals(shoresh) && verb.getTense().equals(time)) {
                Log.d("rrr_l", verb.getLitso());
                Log.d("rrr_ch", verb.getChislo());
                results.add(verb.vocalized_inflection + "  " + verb.getMesto());
            }
        }

        Log.d("rrr_counter", counter + "");
        // получаем экземпляр элемента ListView
        ListView listView = findViewById(R.id.listView);


        // используем адаптер данных
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, results);
        listView.setAdapter(adapter);

        Log.d("qaz", String.valueOf(results));

        String listString = "";
        for (String s : results) {
            listString += s + " ";
        }

        String textToBeTranslated = "אוֹהֵב";
        String languagePair = "he-ru";
        Translate(textToBeTranslated, languagePair);
    }

    void Translate(String textToBeTranslated, String languagePair) {
        TranslatorBackgroundTask translatorBackgroundTask = new TranslatorBackgroundTask(context);
        String translationResult = String.valueOf(translatorBackgroundTask.execute(textToBeTranslated, languagePair)); // Returns the translated text as a String
        Log.d("Translation Result", translationResult); // Logs the result in Android Monitor
    }
}

