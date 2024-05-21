package com.example.cakebake;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class cakebakeCategory extends AppCompatActivity {

    CakeBakeDB myDB;
    EditText cakeCATIDtxt,categoryNametxt;
    Button addCategorybtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frmice_category);

        myDB=new CakeBakeDB(this);

        cakeCATIDtxt=findViewById(R.id.txtcakeCATID);
        categoryNametxt=findViewById(R.id.txtcakeCat);
        addCategorybtn=findViewById(R.id.btnAddCategory);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addCategorybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isInsert=myDB.addnewCupCakeCategory(cakeCATIDtxt.getText().toString(),categoryNametxt.getText().toString());

                if(isInsert)
                {
                    Toast.makeText(cakebakeCategory.this, "New CupCake Category Added", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(cakebakeCategory.this, "Error in New CupCake Category Add", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}