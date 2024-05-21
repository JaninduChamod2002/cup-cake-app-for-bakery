package com.example.cakebake;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class cakebakeNewUser extends AppCompatActivity {
    CakeBakeDB myDB;
    userCheck UCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_new_user);

        myDB=new CakeBakeDB(this);
        UCheck=new userCheck();

        EditText userInfoNametxt,userInfoAddresstxt,userInfocontacttxt,userLogintxt,userPasstxt;
        RadioButton adminchk,normalUserChk;
        Button createbtn;

        userInfoNametxt=findViewById(R.id.txtuserInfoName);
        userInfoAddresstxt=findViewById(R.id.txtuserInfoAddress);
        userInfocontacttxt=findViewById(R.id.txtuserInfoContact);
        userLogintxt=findViewById(R.id.txtuserLogin);
        userPasstxt=findViewById(R.id.txtuserPass);
        adminchk=findViewById(R.id.rbAdmin);
        normalUserChk=findViewById(R.id.rbNormal);
        createbtn=findViewById(R.id.btnCreate);

        if(UCheck.uChk==1)
        {
            adminchk.setVisibility(View.VISIBLE);
            normalUserChk.setVisibility(View.VISIBLE);
        }
        else
        {
            adminchk.setVisibility(View.INVISIBLE);
            normalUserChk.setVisibility(View.INVISIBLE);
            UCheck.uChk=1;
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uRole="";

                if(adminchk.isChecked()==true)
                {
                        uRole="Admin";
                }
                else if (normalUserChk.isChecked()==true)
                {
                    uRole="Normal";
                }
                Toast.makeText(cakebakeNewUser.this,userInfoNametxt.getText().toString()+userInfoAddresstxt.getText().toString()+userInfocontacttxt.getText().toString()+userLogintxt.getText().toString()+userPasstxt.getText().toString()+uRole, Toast.LENGTH_SHORT).show();

               boolean isInsert=myDB.newUserInfo(userInfoNametxt.getText().toString(),userInfoAddresstxt.getText().toString(),userInfocontacttxt.getText().toString(),userLogintxt.getText().toString(),userPasstxt.getText().toString(),uRole);

                if(isInsert)
                {
                    Toast.makeText(cakebakeNewUser.this, "New User Information Update", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(cakebakeNewUser.this, "Error in New User Add", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}