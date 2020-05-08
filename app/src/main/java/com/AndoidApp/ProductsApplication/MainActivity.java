package com.AndoidApp.ProductsApplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<Produs> produse = new ArrayList<>();

    FirebaseDatabase frb;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        dbref = FirebaseDatabase.getInstance().getReference().child("Prods");

//        ValueEventListener eventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    String denumire, cantitate, categorie, pret;
//                    denumire = ds.child("denumire").getValue().toString();
//                    cantitate = ds.child("cantitate").getValue().toString();
//                    categorie = ds.child("categorie").getValue().toString();
//                    pret = ds.child("pret").getValue().toString();
//
//                    Produs obj = new Produs(denumire, cantitate, pret, categorie);
//                    produse.add(obj);
//                }
//
//                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                adapter = new Adapter(MainActivity.this, produse);
//                recyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        };
//        dbref.addListenerForSingleValueEvent(eventListener);

        Produs obj1 = new Produs("Den1", "20", "20", "Fructe");
        Produs obj2 = new Produs("Den2", "20", "40", "Fructe");
        Produs obj3 = new Produs("Den3", "20", "20", "Legume");
        produse.add(obj1); produse.add(obj2);produse.add(obj3);produse.add(obj3);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new Adapter(MainActivity.this, produse);
        recyclerView.setAdapter(adapter);
    }
    public void openAddProduct(){
        Intent intent = new Intent(this, AddProductFormActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.start_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        openAddProduct();
        return super.onOptionsItemSelected(item);
    }
}
