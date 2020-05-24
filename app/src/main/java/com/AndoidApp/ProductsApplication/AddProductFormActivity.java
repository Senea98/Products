package com.AndoidApp.ProductsApplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddProductFormActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;
    private ImageView imageView;

    private Button addProduct, chooseImg, add;
    private EditText denumire, cantitate, pret, categorie;

    private StorageReference storageref;

    FirebaseDatabase frb;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_form);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addProduct = findViewById(R.id.AddProduct);
        chooseImg = findViewById(R.id.chooseImg);
        imageView = findViewById(R.id.ImgView);
        add = findViewById(R.id.add);

        storageref= FirebaseStorage.getInstance().getReference("uploads");
        frb = FirebaseDatabase.getInstance();
        dbref = frb.getReference("Prods");

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                    dbref= FirebaseDatabase.getInstance().getReference().child("Prods").child(dentxt);
                    dbref.setValue(obj);
                    Toast.makeText(AddProductFormActivity.this, "Adaugat cu succes", Toast.LENGTH_LONG).show();
                    Uploadfile(dentxt);

                }

            }
        });

        chooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenFileChooser();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uploadfile("file1");
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

    private String getExtention(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime =MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void Uploadfile(final String str){
        if(imageUri != null){
            Log.i("Location", str +"."+ getExtention(imageUri));
            Log.i("File", imageUri.toString());
            StorageReference fileref = storageref.child(str +"."+ getExtention(imageUri));

            fileref.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(AddProductFormActivity.this, "Incarcare reusita",Toast.LENGTH_LONG).show();
                            //dbref.child(str).child("imgUrl").setValue(taskSnapshot.getStorage().getDownloadUrl().toString());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddProductFormActivity.this, "Incarcare nereusita" + e,Toast.LENGTH_LONG).show();
                        }
                    });
        } else {
            Toast.makeText(this, "Alegeti o imagine",Toast.LENGTH_LONG).show();
        }
    }
}
