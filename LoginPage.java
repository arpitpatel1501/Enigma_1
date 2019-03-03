package com.example.intel.testapplicationhackthon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginPage extends AppCompatActivity {

    private Button btnLoginParent;
    private Button btnLoginAthority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        btnLoginParent = (Button)findViewById(R.id.btnLoginParent);
        btnLoginParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginParent();
            }
        });

        btnLoginAthority = (Button)findViewById(R.id.btnLoginAuthority);
        btnLoginAthority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginAthority();
            }
        });

        setTitle("Login");
    }



    @Override
    public void onBackPressed() {
        finish();
    }

    public void openLoginParent(){
        Intent intent = new Intent(LoginPage.this,LoginParent.class);
        startActivity(intent);
    };


    public void openLoginAthority(){
        Intent intent = new Intent(LoginPage.this , LoginAuthority.class);
        startActivity(intent);

    };
}
