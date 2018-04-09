package com.eni.ecole.cdi.douze.recipepuppy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements RechercheFragment.OnRechercheListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void rechercher(String motClef) {
        Intent intent = new Intent(MainActivity.this, ListeResultatsActivity.class);
        intent.putExtra("motClef", motClef);
        startActivity(intent);
    }
}
