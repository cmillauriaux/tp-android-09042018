package com.eni.ecole.cdi.douze.recipepuppy;

import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RecetteActivity extends AppCompatActivity {
    private Recette recette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recette);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.recette = new Recette();
        this.recette.setTitre(getIntent().getStringExtra("titre"));
        this.recette.setLien(getIntent().getStringExtra("lien"));
        this.recette.setIngredients(getIntent().getStringExtra("ingredients"));
        this.recette.setImage(getIntent().getStringExtra("image"));

        RecetteFragment fragment =
                (RecetteFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.recette);
        fragment.setRecette(this.recette);

        // Si on est en portrait, rediriger vers l'écran précédent
        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
        }
    }
}
