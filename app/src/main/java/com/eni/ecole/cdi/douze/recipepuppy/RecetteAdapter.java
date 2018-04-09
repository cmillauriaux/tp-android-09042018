package com.eni.ecole.cdi.douze.recipepuppy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrateur on 06/04/2018.
 */

public class RecetteAdapter extends ArrayAdapter<Recette> {
    private int layout;

    public RecetteAdapter(@NonNull Context context,
                          int resource,
                          @NonNull List<Recette> objects) {
        super(context, resource, objects);
        this.layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable
            View convertView, @NonNull
            ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)
                    getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.layout, parent, false);
        }

        TextView titreRecette = convertView.findViewById(R.id.titre_recette);
        Recette recette = this.getItem(position);
        titreRecette.setText(recette.getTitre());

        return convertView;
    }
}
