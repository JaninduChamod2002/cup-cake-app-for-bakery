package com.example.cakebake;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Admin extends AppCompatActivity {

    ImageButton newUserbtn;
    ImageButton newIcecreambtn,newIceCatbtn,upDelbtn,viewbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);

        newUserbtn=findViewById(R.id.btnNewUser);
        newIcecreambtn=findViewById(R.id.btnIceCream);
        newIceCatbtn=findViewById(R.id.btnNewIceCat);
        upDelbtn=findViewById(R.id.btnUpDel);
        viewbtn = findViewById(R.id.btnview);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        newUserbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tonewUser=new Intent(Admin.this, cakebakeNewUser.class);
                startActivity(tonewUser);
            }
        });

        newIcecreambtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tonewIcecream=new Intent(Admin.this, addNewcakebake.class);
                startActivity(tonewIcecream);
            }
        });

        newIceCatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tonewIceCategory=new Intent(Admin.this, cakebakeCategory.class);
                startActivity(tonewIceCategory);
            }
        });

        upDelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toupdateDelete=new Intent(Admin.this, UpDelete.class);
                startActivity(toupdateDelete);
            }
        });

        viewbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent viewAll=new Intent(Admin.this, AllCakes.class);
                startActivity(viewAll);
            }
        });




    }
}