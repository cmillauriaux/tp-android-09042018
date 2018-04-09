package com.eni.ecole.cdi.douze.recipepuppy;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class RechercheFragment extends Fragment {

    private OnRechercheListener mListener;
    private Button rechercher;
    private EditText motClef;

    public RechercheFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recherche, container, false);

        this.rechercher = v.findViewById(R.id.bouton_rechercher);
        this.motClef = v.findViewById(R.id.saisie_recherche);

        this.rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (motClef.getText().toString().isEmpty()) {
                    motClef.setError("Veuillez saisir un mot clef");
                } else {
                    mListener.rechercher(motClef.getText().toString());
                }
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRechercheListener) {
            mListener = (OnRechercheListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnRechercheListener");
        }
    }

    public interface OnRechercheListener {
        void rechercher(String motClef);
    }

}
