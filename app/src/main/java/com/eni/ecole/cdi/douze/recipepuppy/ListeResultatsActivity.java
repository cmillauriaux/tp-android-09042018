package com.eni.ecole.cdi.douze.recipepuppy;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListeResultatsActivity extends AppCompatActivity
        implements ListeResultatsFragment.OnClickRecetteListener,
        RechercheFragment.OnRechercheListener {
    private ListeResultatsFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_resultats);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fragment = (ListeResultatsFragment)
                getSupportFragmentManager().findFragmentById(R.id.liste_recherche);
        // Récupération du mot clef émis par l'écran précédent
        String motClef = getIntent().getStringExtra("motClef");

        SearchRecipe task = new SearchRecipe();
        task.execute(motClef);
    }

    @Override
    public void clickOnRecette(Recette r) {
        if (findViewById(R.id.layout_recette) != null) {
            RecetteFragment fragment =
                    (RecetteFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.recette);
            fragment.setRecette(r);
        } else {
            Intent intent = new Intent(ListeResultatsActivity.this,
                    RecetteActivity.class);
            intent.putExtra("titre", r.getTitre());
            intent.putExtra("lien", r.getLien());
            intent.putExtra("ingredients", r.getIngredients());
            intent.putExtra("image", r.getImage());
            startActivity(intent);
        }
    }

    @Override
    public void rechercher(String motClef) {
        SearchRecipe task = new SearchRecipe();
        task.execute(motClef);
    }

    public class SearchRecipe extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            String motClef = strings[0];

            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://www.recipepuppy.com/api/?q=" + motClef,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                List<Recette> recettes = new ArrayList<>();
                                JSONArray results = response.getJSONArray("results");
                                if (results != null) {
                                    for (int i = 0; i < results.length(); i++) {
                                        JSONObject r = results.getJSONObject(i);
                                        Recette recette = new Recette();
                                        recette.setTitre(r.getString("title"));
                                        recette.setLien(r.getString("href"));
                                        recette.setImage(r.getString("thumbnail"));
                                        recette.setIngredients(r.getString("ingredients"));
                                        recettes.add(recette);
                                    }
                                    fragment.setListe(recettes);
                                    if (recettes.size() > 0 &&
                                            findViewById(R.id.layout_recette) != null) {
                                        RecetteFragment fragment =
                                                (RecetteFragment) getSupportFragmentManager()
                                                        .findFragmentById(R.id.recette);
                                        fragment.setRecette(recettes.get(0));
                                    }
                                }
                            } catch (JSONException e) {
                                Toast.makeText(ListeResultatsActivity.this,
                                        "Impossible de lire la réponse du serveur",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ListeResultatsActivity.this,
                                    "Impossible de contacter le serveur",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
            );
            RequestQueue queue = Volley.newRequestQueue(ListeResultatsActivity.this);
            queue.add(request);

            return null;
        }
    }
}
