package com.example.intel.testapplicationhackthon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Authority_HomePage extends AppCompatActivity {

    TextView txtClass1;
    TextView txtClass2;
    TextView txtClass3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authority__home_page);
        setTitle("Home Page");

        txtClass1 = (TextView)findViewById(R.id.txtClass1);
        txtClass2 = (TextView)findViewById(R.id.txtClass2);
        txtClass3 = (TextView)findViewById(R.id.txtClass3);

    }
}
