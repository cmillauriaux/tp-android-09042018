package com.eni.ecole.cdi.douze.recipepuppy;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecetteFragment extends Fragment {
    private Recette recette;


    public RecetteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recette, container, false);
    }

    public void setRecette(Recette r) {
        this.recette = r;
        TextView titre = getView().findViewById(R.id.titre);
        TextView ingredients = getView().findViewById(R.id.ingredients);
        titre.setText(this.recette.getTitre());
        ingredients.setText(this.recette.getIngredients());
    }

}
