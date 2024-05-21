package com.example.cakebake;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;


public class addNewcakebake extends AppCompatActivity {
    CakeBakeDB myDB;
    EditText nametxt,flavourtxt,categorytxt,qtytxt,pricetxt;
    Spinner cakeIDspn;
    Button addnewcupCakebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frmnew_icecream);

        cakeIDspn=findViewById(R.id.spncakeCAT);
        nametxt=findViewById(R.id.txtcakeName);
        flavourtxt=findViewById(R.id.txtcakeflavour);
        categorytxt=findViewById(R.id.txtcakeCategory);
        qtytxt=findViewById(R.id.txtcakeQTY);
        pricetxt=findViewById(R.id.txtcakeprice);

        addnewcupCakebtn=findViewById(R.id.btnAddCupcake);

        myDB=new CakeBakeDB(this);

        //########################  SET Catagory IDs IN TO SPINNER  ###########################
        ArrayList<String> allIDs = myDB.getCatagory();
        ArrayAdapter<String> IDApaptor = new ArrayAdapter<String>(addNewcakebake.this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, allIDs);
        cakeIDspn.setAdapter(IDApaptor);
        //######################  END OF PUTTING VALUES TO SPINNER  ######################


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addnewcupCakebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCupcakeInsert=myDB.addnewCupCake(cakeIDspn.getSelectedItem().toString(),nametxt.getText().toString(),flavourtxt.getText().toString(),categorytxt.getText().toString(),qtytxt.getText().toString(),pricetxt.getText().toString());

                if(isCupcakeInsert)
                {
                    Toast.makeText(addNewcakebake.this, "New CupCake Update", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(addNewcakebake.this, "Error in New CupCake Add", Toast.LENGTH_SHORT).show();
                }
            }
        });


        cakeIDspn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("Range")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    String selectedCategory = cakeIDspn.getSelectedItem().toString().trim();
                    Cursor CATrecive = myDB.GetCATName(selectedCategory);

                    if (CATrecive != null && CATrecive.moveToFirst()) {
                        categorytxt.setText(CATrecive.getString(CATrecive.getColumnIndex("cupCakecategory")));
                    } else {
                        // Handle case when Cursor is empty
                        Toast.makeText(addNewcakebake.this, "No data found for selected category", Toast.LENGTH_SHORT).show();
                    }

                    // Close the Cursor after use
                    if (CATrecive != null) {
                        CATrecive.close();
                    }
                } catch(Exception ex) {
                    // Handle any other exceptions
                    Toast.makeText(addNewcakebake.this, "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Handle case when nothing is selected (optional)
            }
        });


    }
}