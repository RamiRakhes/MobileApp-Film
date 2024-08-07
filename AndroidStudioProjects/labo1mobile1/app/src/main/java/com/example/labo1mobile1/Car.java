package com.example.labo1mobile1;

import java.util.Calendar;

public class Car {
    private int number;
    private String marque;
    private int annee;
    private double prix;
    private String couleur;

    public Car(int number, String marque, int annee, double prix, String couleur) {

        this.number = number;
        this.marque = marque;
        this.annee = annee;
        this.prix = prix;
        this.couleur = couleur;
    }

    // Getter Setter
    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public String getMarque() { return marque; }
    public void setMarque(String marque) { this.marque = marque; }

    public int getAnnee() { return annee; }
    public void setAnnee(int annee) { this.annee = annee; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public String getCouleur() { return couleur; }
    public void setCouleur(String couleur) { this.couleur = couleur; }

    public int getAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - this.annee;
    }
}

