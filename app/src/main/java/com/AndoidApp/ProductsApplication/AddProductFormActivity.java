package com.AndoidApp.ProductsApplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class AddProductFormActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;
    private ImageView imageView;

    private Button addProduct, chooseImg;
    private EditText denumire, cantitate, pret, categorie;

    FirebaseDatabase frb;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_form);

        addProduct = findViewById(R.id.AddProduct);
        chooseImg = findViewById(R.id.chooseImg);
        imageView = findViewById(R.id.ImgView);



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
                    Produs obj = new Produs(dentxt, cantxt, pretxt, categtxt);
                    dbref= FirebaseDatabase.getInstance().getReference().child("Prods");
                    dbref.push().setValue(obj);
                }

            }
        });

        chooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenFileChooser();
            }
        });
    }
    private void OpenFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();

            Picasso.with(this).load(imageUri).into(imageView);
        }
    }
}
