package com.example.cakebake;


import android.os.Bundle;
import android.view.View;
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

public class UpDelete extends AppCompatActivity {

    CakeBakeDB myDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_up_delete);

        Spinner Icenamespn;
        EditText IceupIDtxt,Iceupfalvourtxt,IceupCattxt,iceupqtytxt;
        Button Icedelbtn,IceUpbtn;

        IceupIDtxt=findViewById(R.id.txtcakeUpID);
        Iceupfalvourtxt=findViewById(R.id.txtcakeupfalvour);
        IceupCattxt=findViewById(R.id.txtcakeupCat);
        iceupqtytxt=findViewById(R.id.txtcakeupqty);

        Icenamespn=findViewById(R.id.spncakeName);

        Icedelbtn=findViewById(R.id.btnDelete);
        IceUpbtn=findViewById(R.id.btnUpdate);

        myDB=new CakeBakeDB(this);

        //########################  SET Catagory IDs IN TO SPINNER  ###########################
        ArrayList<String> allIceNames = myDB.getcupCakeNames();
        ArrayAdapter<String> IDApaptor = new ArrayAdapter<String>(UpDelete.this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, allIceNames);
        Icenamespn.setAdapter(IDApaptor);
        //######################  END OF PUTTING VALUES TO SPINNER  ######################



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Icedelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer delInfo=myDB.DelatecupCake(Icenamespn.getSelectedItem().toString());
                if(delInfo>0)
                {
                    Toast.makeText(UpDelete.this, "Selected icecream Deleted!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        IceUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate=myDB.upadatecakeInfo(Iceupfalvourtxt.getText().toString(),IceupCattxt.getText().toString(),iceupqtytxt.getText().toString(),iceupqtytxt.getText().toString());
                if(isUpdate)
                {
                    Toast.makeText(UpDelete.this, "Ice cram Info Updated!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(UpDelete.this, "Problem in Info Update", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}