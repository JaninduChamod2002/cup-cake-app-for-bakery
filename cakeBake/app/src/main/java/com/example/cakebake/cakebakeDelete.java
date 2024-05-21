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

public class cakebakeDelete extends AppCompatActivity {

    CakeBakeDB myDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frmup_del);

        Spinner Icenamespn;
        EditText IceupIDtxt,Iceupfalvourtxt,IceupCattxt,iceupqtytxt;
        Button Icedelbtn,IceUpbtn;

        IceupIDtxt=findViewById(R.id.txtIceUpID);
        Iceupfalvourtxt=findViewById(R.id.txtIceupfalvour);
        IceupCattxt=findViewById(R.id.txtIceupCat);
        iceupqtytxt=findViewById(R.id.txticeupqty);

        Icenamespn=findViewById(R.id.spnIceName);

        Icedelbtn=findViewById(R.id.btnIceDelete);
        IceUpbtn=findViewById(R.id.btnIceUpdate);

        myDB=new CakeBakeDB(this);

        //########################  SET Catagory IDs IN TO SPINNER  ###########################
        ArrayList<String> allIceNames = myDB.getcupCakeNames();
        ArrayAdapter<String> IDApaptor = new ArrayAdapter<String>(cakebakeDelete.this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, allIceNames);
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
                    Toast.makeText(cakebakeDelete.this, "Selected icecream Deleted!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        IceUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate=myDB.upadatecakeInfo(Iceupfalvourtxt.getText().toString(),IceupCattxt.getText().toString(),iceupqtytxt.getText().toString(),iceupqtytxt.getText().toString());
                if(isUpdate)
                {
                    Toast.makeText(cakebakeDelete.this, "Ice cram Info Updated!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(cakebakeDelete.this, "Problem in Info Update", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}