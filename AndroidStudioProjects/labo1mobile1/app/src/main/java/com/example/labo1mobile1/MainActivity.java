package com.example.labo1mobile1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private EditText etNo, etMarque, etAnnee, etPrix, etCouleur, etSearch;
    private Button btnSave, btnAge, btnGo, btnPrev, btnNext, btnModify, btnDelete;
    private RadioGroup rgSearchOptions;
    private RadioButton rbNum, rbMarque, rbAnnee, rbPrix;
    private TextView tvResult;

    private ArrayList<Car> cars = new ArrayList<>();
    private int currentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialise
        etNo = findViewById(R.id.et_no);
        etMarque = findViewById(R.id.et_marque);
        etAnnee = findViewById(R.id.et_annee);
        etPrix = findViewById(R.id.et_prix);
        etCouleur = findViewById(R.id.et_couleur);
        etSearch = findViewById(R.id.et_search);

        btnSave = findViewById(R.id.btn_save);
        btnAge = findViewById(R.id.btn_age);
        btnGo = findViewById(R.id.btn_go);
        btnPrev = findViewById(R.id.btn_prev);
        btnNext = findViewById(R.id.btn_next);
        btnModify = findViewById(R.id.btn_modify);
        btnDelete = findViewById(R.id.btn_delete);

        rgSearchOptions = findViewById(R.id.rg_search_options);
        rbNum = findViewById(R.id.rb_num);
        rbMarque = findViewById(R.id.rb_marque);
        rbAnnee = findViewById(R.id.rb_annee);
        rbPrix = findViewById(R.id.rb_prix);
        tvResult = findViewById(R.id.tv_result);

        // Set boutton ecouter
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCar();
            }
        });

        btnAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCarAge();
            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCar();
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateCars(false);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateCars(true);
            }
        });

        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCar();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCar();
            }
        });
    }

    private void saveCar() {
        int no = Integer.parseInt(etNo.getText().toString());
        String marque = etMarque.getText().toString();
        int annee = Integer.parseInt(etAnnee.getText().toString());
        double prix = Double.parseDouble(etPrix.getText().toString());
        String couleur = etCouleur.getText().toString();

        Car car = new Car(no, marque, annee, prix, couleur);
        cars.add(car);
        Toast.makeText(this, "Car saved!", Toast.LENGTH_SHORT).show();
    }

    private void showCarAge() {
        if (currentIndex >= 0 && currentIndex < cars.size()) {
            int age = cars.get(currentIndex).getAge();
            Toast.makeText(this, "Car age: " + age, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No car selected", Toast.LENGTH_SHORT).show();
        }
    }




    private void searchCar() {
        String query = etSearch.getText().toString();
        int selectedId = rgSearchOptions.getCheckedRadioButtonId();

        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            if ((selectedId == R.id.rb_num && String.valueOf(car.getNumber()).equals(query)) ||
                    (selectedId == R.id.rb_marque && car.getMarque().equalsIgnoreCase(query)) ||
                    (selectedId == R.id.rb_annee && String.valueOf(car.getAnnee()).equals(query)) ||
                    (selectedId == R.id.rb_prix && String.valueOf(car.getPrix()).equals(query))) {
                currentIndex = i;
                displayCar(car);
                return;
            }
        }

        tvResult.setText("No car found");
    }

    private void navigateCars(boolean next) {
        if (next) {
            if (currentIndex < cars.size() - 1) {
                currentIndex++;
            }
        } else {
            if (currentIndex > 0) {
                currentIndex--;
            }
        }

        if (currentIndex >= 0 && currentIndex < cars.size()) {
            Car car = cars.get(currentIndex);
            displayCar(car);

            //hadi
            etNo.setText(String.valueOf(car.getNumber()));
            etMarque.setText(car.getMarque());
            etAnnee.setText(String.valueOf(car.getAnnee()));
            etPrix.setText(String.valueOf(car.getPrix()));
            etCouleur.setText(car.getCouleur());
        }
    }

    private void displayCar(Car car) {
        tvResult.setText(String.format("No: %d\nMarque: %s\nAnnée: %d\nPrix: %.2f\nCouleur: %s",
                car.getNumber(), car.getMarque(), car.getAnnee(), car.getPrix(), car.getCouleur()));

    }

    private void modifyCar() {
        if (currentIndex >= 0 && currentIndex < cars.size()) {
            int no = Integer.parseInt(etNo.getText().toString());
            String marque = etMarque.getText().toString();
            int annee = Integer.parseInt(etAnnee.getText().toString());
            double prix = Double.parseDouble(etPrix.getText().toString());
            String couleur = etCouleur.getText().toString();

            // Met à jour les détails de la voiture
            Car car = cars.get(currentIndex);
            car.setNumber(no);
            car.setMarque(marque);
            car.setAnnee(annee);
            car.setPrix(prix);
            car.setCouleur(couleur);

            Toast.makeText(this, "Car modified!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No car selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteCar() {
        if (currentIndex >= 0 && currentIndex < cars.size()) {
            cars.remove(currentIndex);
            currentIndex = -1;
            // Efface le texte du resultat.
            tvResult.setText("");
            Toast.makeText(this, "Car deleted!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No car selected", Toast.LENGTH_SHORT).show();
        }
    }
}


