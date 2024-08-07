package com.example.labo2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private ListView resultListView;
    private FilmAdapter filmAdapter;
    private List<Film> films;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        resultListView = findViewById(R.id.result_list_view);
        backButton = findViewById(R.id.back_button);

        Intent intent = getIntent();// Récupère l'intention (Intent) qui a démarré cette activité.
        // Récupère la liste des films passée via l'intent et la convertit en une liste de Film.
        films = (List<Film>) intent.getSerializableExtra("films");
        // Appelle la méthode showResults pour trier les films par réputation et les afficher dans la ListView.
        showResults();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // nwaliw l activite lawla
            }
        });
    }

    private void showResults() {
        // Trie la liste des films par réputation en ordre décroissant.

        Collections.sort(films, new Comparator<Film>() {
            @Override
            public int compare(Film film1, Film film2) {
                return Integer.compare(film2.getReputation(), film1.getReputation());
            }
        });

        // Initialise l'adaptateur avec le contexte, le layout pour les éléments de la liste et la liste des films triés.

        filmAdapter = new FilmAdapter(this, R.layout.film_item, films);
        resultListView.setAdapter(filmAdapter);
    }
}
