package com.example.cakebake;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.database.Cursor;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    CakeBakeDB myDB;
    userCheck UCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        myDB=new CakeBakeDB(this);
        UCheck=new userCheck();

        EditText usernametxt,passwordtxt;
        Button loginbtn;
        TextView newUserlbl;

        usernametxt=findViewById(R.id.txtuserName);
        passwordtxt=findViewById(R.id.txtPassword);
        loginbtn=findViewById(R.id.btnLogin);
        newUserlbl=findViewById(R.id.lblNewUser);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    if(usernametxt.getText().toString().equals("admin"))
                    {
                        if(passwordtxt.getText().toString().equals("123"))
                        {
                            UCheck.uChk=1;
                            Intent toAdminform=new Intent(MainActivity.this,Admin.class);
                            startActivity(toAdminform);
                        }
                    }
                    else if(usernametxt.getText().toString()!=("admin@icekool.com"))
                    {
                        UCheck.uChk=0;
                        Cursor userFound=myDB.userCheckForLogin(usernametxt.getText().toString(),passwordtxt.getText().toString());
                        if(userFound.getCount()==0)
                        {
                            Toast.makeText(MainActivity.this, "No Such User!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else
                        {
                            while(userFound.moveToNext())
                            {
                                String userRole=userFound.getString(2);
                                //Toast.makeText(frmLogin.this, userRole, Toast.LENGTH_SHORT).show();
                                if(userRole.equals("Normal"))
                                {
                                    UCheck.uChk=0;
                                    Intent customerHome=new Intent(MainActivity.this,AllCakes.class);
                                    startActivity(customerHome);
                                }
                                else if(userRole.equals("Admin"))
                                {
                                    UCheck.uChk=1;
                                    Intent adminHome=new Intent(MainActivity.this,Admin.class);
                                    startActivity(adminHome);
                                }
                            }
                        }
                    }
                }
                catch(Exception ex)
                {
                    Toast.makeText(MainActivity.this, ex.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });
        newUserlbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UCheck.uChk=0;
                Intent toNewUser=new Intent(MainActivity.this, cakebakeNewUser.class);
                startActivity(toNewUser);
            }
        });
    }
}