package com.example.labo2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import java.util.List;


public class FilmAdapter extends BaseAdapter {

    private Context context;
    private int resource;
    private List<Film> films;
    private LayoutInflater inflater;

    // Constructeur pour initialiser l'adaptateur
    public FilmAdapter(Context context, int resource, List<Film> films) {
        this.context = context;
        this.resource = resource;
        this.films = films;
        this.inflater = LayoutInflater.from(context);
    }
    // Retourne le nombre total d'éléments dans la liste de films.
    @Override
    public int getCount() {
        return films.size();
    }

    // Retourne l'objet Film à la position spécifiée dans la liste.
    @Override
    public Object getItem(int position) {
        return films.get(position);
    }

    // Retourne l'identifiant de l'élément à la position spécifiée
    @Override
    public long getItemId(int position) {
        return position;
   }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(resource, parent, false);
        }

        // Récupère les références aux vues individuelles dans le layout de l'élément.
        ImageView filmPhoto = convertView.findViewById(R.id.film_photo);
        TextView filmNom = convertView.findViewById(R.id.film_nom);
        TextView filmDescription = convertView.findViewById(R.id.film_description);
        TextView filmGenre = convertView.findViewById(R.id.film_genre);
        TextView filmReputation = convertView.findViewById(R.id.film_reputation);
        TextView filmDateSortie = convertView.findViewById(R.id.film_date_sortie);
        TextView filmLieuSortie = convertView.findViewById(R.id.film_lieu_sortie);
        Button increaseReputation1 = convertView.findViewById(R.id.increase_reputation_1);
        Button increaseReputation3 = convertView.findViewById(R.id.increase_reputation_3);
        Button decreaseReputation2 = convertView.findViewById(R.id.decrease_reputation_2);

         final Film currentFilm = films.get(position);

        // Configure les vues avec les données du film actuel.

        filmPhoto.setImageResource(currentFilm.getPhoto());
        filmNom.setText(currentFilm.getNom());
        filmDescription.setText(currentFilm.getDescription());
        filmGenre.setText(currentFilm.getGenre());
        filmReputation.setText(String.valueOf(currentFilm.getReputation()));
        filmDateSortie.setText(currentFilm.getDateSortie());
        filmLieuSortie.setText(currentFilm.getLieuSortie());

        // Configure le comportement des boutons pour ajuster la réputation du film.
        increaseReputation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentFilm.increaseReputation(1);
                notifyDataSetChanged();
            }
        });

        increaseReputation3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentFilm.increaseReputation(3);
                notifyDataSetChanged();
            }
        });

        decreaseReputation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentFilm.decreaseReputation(2);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    // Méthode pour mettre à jour la liste des films dans l'adaptateur et rafraîchir la vue.

    public void updateFilmsList(List<Film> films) {
        this.films = films;//mise a jour ta3 la liste
        notifyDataSetChanged();// notif l'adaptateur beli changement
    }
}
