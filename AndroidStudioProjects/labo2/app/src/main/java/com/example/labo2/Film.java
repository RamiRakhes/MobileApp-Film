package com.example.labo2;

import java.io.Serializable;

public class Film implements Serializable {
    // Attributs4 de la classe Film.
    private int photo;
    private String nom;
    private String description;
    private String genre;
    private int reputation;
    private String dateSortie;
    private String lieuSortie;

    // Constructeur de la classe Film.
    public Film(int photo, String nom, String description, String genre, int reputation, String dateSortie, String lieuSortie) {
        this.photo = photo;
        this.nom = nom;
        this.description = description;
        this.genre = genre;
        this.reputation = reputation;
        this.dateSortie = dateSortie;
        this.lieuSortie = lieuSortie;
    }
    // Méthodes getters pour accéder aux valeurs des attributs privés.
    public int getPhoto() {
        return photo;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public int getReputation() {
        return reputation;
    }

    public String getDateSortie() {
        return dateSortie;
    }

    public String getLieuSortie() {
        return lieuSortie;
    }
     // Méthodes setters pour modifier les valeurs des attributs privés.
    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public void setDateSortie(String dateSortie) {
        this.dateSortie = dateSortie;
    }

    public void setLieuSortie(String lieuSortie) {
        this.lieuSortie = lieuSortie;
    }

    // Méthode pour augmenter la réputation
    public void increaseReputation(int amount) {
        this.reputation += amount;
    }

    // Méthode pour diminuer la réputation
    public void decreaseReputation(int amount) {
        this.reputation -= amount;
    }

}