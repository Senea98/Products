package com.AndoidApp.ProductsApplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    private Button btn, showBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Open add_product_form activity
        btn= (Button) findViewById(R.id.addItem);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openAddProduct();
            }
        });
//
        showBtn= (Button) findViewById(R.id.showItems);
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }
    public void openAddProduct(){
        Intent intent = new Intent(this, AddProductFormActivity.class);
        startActivity(intent);
    }
    public void openDialog(){
        OpenedDialog NumeAndStuff = new OpenedDialog("Products", "Aici ar trebui sa fie afisate produsele (desi nu prea are sens, dar na...:))" +
                "Ah da, si numele: Marian Simion");
        NumeAndStuff.show(getSupportFragmentManager(),"Aka dialog box");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.start_menu, menu);
        return true;
    }
}
