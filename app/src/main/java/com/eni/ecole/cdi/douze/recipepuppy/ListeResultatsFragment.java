package com.eni.ecole.cdi.douze.recipepuppy;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListeResultatsFragment extends Fragment {
    private OnClickRecetteListener mListener;
    private List<Recette> recettes;

    public ListeResultatsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnClickRecetteListener) {
            mListener = (OnClickRecetteListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnRechercheListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_liste_resultats, container, false);
        ListView listeResultats = v.findViewById(R.id.liste_resultats);
        listeResultats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view,
                                    int position,
                                    long l) {
                if (recettes != null) {
                    mListener.clickOnRecette(recettes.get(position));
                }
            }
        });
        return v;
    }

    public void setListe(List<Recette> recettes) {
        this.recettes = recettes;
        ListView listeResultats = this.getView()
                .findViewById(R.id.liste_resultats);
        RecetteAdapter adapter = new RecetteAdapter(
                this.getContext(),
                R.layout.ligne_recette,
                recettes);
        listeResultats.setAdapter(adapter);
    }

    public interface OnClickRecetteListener {
        void clickOnRecette(Recette r);
    }

}
