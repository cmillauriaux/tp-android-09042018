package com.eni.ecole.cdi.douze.recipepuppy;

/**
 * Created by Administrateur on 06/04/2018.
 */

public class Recette {
    private String titre;
    private String lien;
    private String ingredients;
    private String image;

    public Recette() {
    }

    public Recette(String titre, String lien, String ingredients, String image) {
        this.titre = titre;
        this.lien = lien;
        this.ingredients = ingredients;
        this.image = image;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
