package com.example.intel.testapplicationhackthon;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginAuthority extends AppCompatActivity {

    private Button btnLoginAutho;
    private EditText txtAuthorityName;
    private EditText txtAuthorityPassword;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_authority);

        btnLoginAutho = (Button) findViewById(R.id.btnLogin);
        txtAuthorityName = (EditText) findViewById(R.id.txtAuthorityName);
        txtAuthorityPassword = (EditText) findViewById(R.id.txtAuthorityPassword);

        setTitle(" Login Authority");

        btnLoginAutho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alert;

                flag = validation(txtAuthorityName.getText().toString(), txtAuthorityPassword.getText().toString());
                if (flag == false) {
                    alert = dialogBoxCreate();
                    alert.show();
                } else {

                    Intent intent = new Intent(LoginAuthority.this, Authority_HomePage.class);
                    startActivity(intent);
                }
            }
        });

    }

    //validation for username and password
    public boolean validation(String userId, String password) {

        String userId1 = "Manager";
        String password1 = "Manager";
        if ((userId.equals(userId1)) && (password.equals(password1))) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }


    //create dialog box
    public AlertDialog dialogBoxCreate() {
        AlertDialog.Builder a_builder = new AlertDialog.Builder(LoginAuthority.this);

        a_builder.setMessage("Inavalid Username and Password !!" + "\n" + "Please enter again").setNegativeButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }

        });
        AlertDialog alert = a_builder.create();
        return alert;
    }
}
