package com.example.labo2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.EditText;
import android.content.Intent;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    private ListView filmListView;
    private FilmAdapter filmAdapter;
    private List<Film> films;
    private List<Film> filteredFilms;
    private EditText searchKey;
    private Button filterButton;
    private Button viewResultButton;

    // private Button increaseReputation1;
    //private Button increaseReputation3;
    //private Button decreaseReputation2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filmListView = findViewById(R.id.film_list_view);
        searchKey = findViewById(R.id.search_key);
        filterButton = findViewById(R.id.filter_button);
//        increaseReputation1 = findViewById(R.id.increase_reputation_1);
//        increaseReputation3 = findViewById(R.id.increase_reputation_3);
//        decreaseReputation2 = findViewById(R.id.decrease_reputation_2);
        viewResultButton = findViewById(R.id.view_result_button);

        films = new ArrayList<>();
        films.add(new Film(R.drawable.film3, "PADDINGTON", "Un jeune ours péruvien arrive à Londres à la recherche d'un foyer. Perdu et seul, il rencontre la famille Brown qui lui offre un refuge temporaire.", "Comédie, Famille", 55, "2014", "Londres"));
        films.add(new Film(R.drawable.film4, "UN REVE ALGERIEN", "L'histoire d'un jeune Algérien qui rêve de quitter son pays pour une vie meilleure en Europe, mais doit surmonter de nombreux obstacles.", "Drame", 88, "2024", "Alger"));
        films.add(new Film(R.drawable.film5, "RETOUR EN ALGERIE", "Un homme retourne en Algérie après avoir passé des années à l'étranger pour affronter son passé et renouer avec ses racines.", "Drame, Historique", 60, "2001", "Alger"));
        films.add(new Film(R.drawable.film8, "IT", "Un groupe d'enfants est terrorisé par un clown maléfique nommé Pennywise qui se nourrit de leurs peurs.", "Horreur, Thriller", 70, "2017", "Derry"));
        films.add(new Film(R.drawable.film7, "SMILE", "Une thérapeute commence à vivre des événements terrifiants après avoir été témoin d'un incident traumatisant impliquant un patient.", "Horreur, Mystère", 65, "2022", "Los Angeles"));
        films.add(new Film(R.drawable.film10, "THE COLOR OF MONEY", "Un joueur de billard professionnel enseigne les astuces du métier à un jeune protégé dans le monde compétitif des salles de billard.", "Drame, Sport", 75, "1986", "Chicago"));
        films.add(new Film(R.drawable.film12, "ATHENA", "Dans un avenir dystopique, un groupe de résistants lutte contre un régime oppressif pour restaurer la liberté.", "Science-Fiction, Action", 80, "2060", "Marseille"));
        films.add(new Film(R.drawable.film13, "THE FIGHTER", "La véritable histoire du boxeur Micky Ward et de son frère Dicky Eklund, qui lutte pour devenir champion du monde des poids welters.", "Biographie, Drame, Sport", 85, "2010", "Lowell"));

        //init adapt
        filmAdapter = new FilmAdapter(this, R.layout.film_item, films);
        // rakeb l adaptateur b  la list vue
        filmListView.setAdapter(filmAdapter);
        //configure les button
        configureButtons();

    }
    private void configureButtons() {
        viewResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResults();
            }
        });

//        increaseReputation1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                modifyReputation(1);
//            }
//        });
//
//        increaseReputation3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                modifyReputation(3);
//            }
//        });
//
//        decreaseReputation2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                modifyReputation(-2);
//            }
//        });

        // Configurer le bouton pour voir les résultats
//        viewResultButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showResults();
//            }
//        });



        // Configure le bouton pour trier les films par ordre alphabétique.

        Button sortAlphaButton = findViewById(R.id.sort_alpha_button);
        sortAlphaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortFilmsAlphabetically();
            }
        });


        // Configure le bouton pour trier les films par date de sortie.
        Button sortRecentButton = findViewById(R.id.sort_recent_button);
        sortRecentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortFilmsByDate();
            }
        });




        // Configure le bouton pour filtrer les films en fonction de la recherche.

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterFilms();
            }
        });
    }

//    private void showSortMenu(View view) {
//        PopupMenu popupMenu = new PopupMenu(this, view);
//        popupMenu.getMenuInflater().inflate(R.menu.sort_menu, popupMenu.getMenu());
//
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.sort_alphabetically:
//                        sortFilmsAlphabetically();
//                        return true;
//                    case R.id.sort_by_recent:
//                        sortFilmsByDate();
//                        return true;
//                    default:
//                        return false;
//                }
//            }
//        });
//
//        popupMenu.show();
//    }

    private void showResults() {
        // Trie les films par réputation décroissante.

        Collections.sort(films, new Comparator<Film>() {
            @Override
            public int compare(Film film1, Film film2) {
                return Integer.compare(film2.getReputation(), film1.getReputation()); // Tri du plus eleve au plus bas
            }
        });

        // Crée un Intent pour passer à une nouvelle activité pour afficher les résultats.

        Intent intent = new Intent(

                MainActivity.this, MainActivity2.class);
        intent.putExtra("films", new ArrayList<>(films));  // Passe la liste des films triés à la nouvelle activité.
        startActivity(intent);
    }
//    private void showResults() {
//        // Trier les films par réputation décroissante
//        Collections.sort(films, new Comparator<Film>() {
//            @Override
//            public int compare(Film film1, Film film2) {
//                return Integer.compare(film2.getReputation(), film1.getReputation()); // Tri du plus élevé au plus bas
//            }
////        });
//        FilmData.setFilms(films);
//        // Passer à une nouvelle activité pour afficher les résultats
//        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
//        startActivity(intent);
//    }

        // Passer à une nouvelle activité pour afficher les résultats
//        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
//        intent.putExtra("films", new ArrayList<>(films)); // Passer la liste des films
//        startActivity(intent);
//    }









    private void sortFilmsAlphabetically() {
        Collections.sort(films, new Comparator<Film>() {
            @Override
            public int compare(Film film1, Film film2) {
                return film1.getNom().compareToIgnoreCase(film2.getNom());
            }
        });

        filmAdapter.updateFilmsList(films);
    }

    private void sortFilmsByDate() {
        Collections.sort(films, new Comparator<Film>() {
            @Override
            public int compare(Film film1, Film film2) {
                return film2.getDateSortie().compareTo(film1.getDateSortie()); // Tri du plus récent au plus ancien
            }
        });
        filmAdapter.updateFilmsList(films);
    }


    private void filterFilms() {
        // Récupère la clé de recherche et initialise la liste des films filtrés.
            String query = searchKey.getText().toString().toLowerCase().trim();
            filteredFilms = new ArrayList<>();

        // Filtre les films dont le nom commence par la clé de recherche.
            for (Film film : films) {
                if (film.getNom().toLowerCase().startsWith(query)) {
                    filteredFilms.add(film);
                }
            }

        // Met à jour la liste des films affichés avec les films filtrés.

        filmAdapter.updateFilmsList(filteredFilms);
        }
    }
    //public void decreaseReputation(int amount) {
        //this.reputation -= amount;
   // }

