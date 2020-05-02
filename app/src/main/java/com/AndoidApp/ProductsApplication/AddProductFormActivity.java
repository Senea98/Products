package com.AndoidApp.ProductsApplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddProductFormActivity extends AppCompatActivity {

    private Button addProduct, chooseImg;
    private EditText denumire, cantitate, pret, categorie;

    FirebaseDatabase frb;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_form);

        addProduct = findViewById(R.id.AddProduct);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frb = FirebaseDatabase.getInstance();
                dbref = frb.getReference("Prods");

                denumire = findViewById(R.id.denumire);
                cantitate = findViewById(R.id.cantitate);
                pret=findViewById(R.id.pret);
                categorie=findViewById(R.id.categorie);

                String dentxt = denumire.getText().toString();
                String cantxt = cantitate.getText().toString();
                String pretxt = pret.getText().toString();
                String categtxt = categorie.getText().toString();

                if(dentxt.isEmpty())denumire.setError("Camp obligatoriu");
                else if(cantxt.isEmpty())cantitate.setError("Camp obligatoriu");
                else if(pretxt.isEmpty())pret.setError("Camp obligatoriu");
                else if(categtxt.isEmpty())categorie.setError("Camp obligatoriu");
                else{

                }
            }
        });

    }
}
