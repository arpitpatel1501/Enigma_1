package com.example.intel.testapplicationhackthon;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.example.intel.testapplicationhackthon.GetData.resultdata;

public class LoginParent extends AppCompatActivity {

    private Button btnLogin;
    private EditText txtUserId;
    private EditText txtPassword;
    private TextView txtWelcome;
    private TextView txtData;
    boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_parent);
        setTitle("Login");

        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtUserId = (EditText)findViewById(R.id.txtUserId);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
     //   txtData = (EditText)findViewById(R.id.txtData);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alert;

                flag=validation(txtUserId.getText().toString() , txtPassword.getText().toString());
                if(flag==false){
                    alert = dialogBoxCreate();
                    alert.show();
                }
                else{

                    Intent intent = new Intent(LoginParent.this,HomePage.class);
                    startActivity(intent);
                }
            }
        });

    }

    //validation for username and password
    public boolean validation(String userId , String password){

            String userId1 = "AU1741079";
            String password1 = "Vaishwi@123";
            if((userId.equals(userId1))&& (password.equals(password1))){
                flag = true;
            }
            else{
                flag = false;
            }
        return flag;
    }


    //create dialog box
    public AlertDialog dialogBoxCreate()
    {
        AlertDialog.Builder a_builder = new AlertDialog.Builder(LoginParent.this);

        a_builder.setMessage("Inavalid Username and Password !!"+"\n"+"Please enter again").setNegativeButton("Yes" , new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }

        });
        AlertDialog alert = a_builder.create();
        return alert;
    }

}


