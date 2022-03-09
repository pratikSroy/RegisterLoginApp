package com.example.registerandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etName = findViewById(R.id.etName);
        final EditText etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = etName.getText().toString();
                String password =  etPassword.getText().toString();

                if(user.isEmpty() || password.isEmpty())
                    Toast.makeText(getApplicationContext(),"Please enter all the values",Toast.LENGTH_SHORT).show();
                else
                {
                    SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
                    String userDetails = preferences.getString(user + password + "data","Incorrect");

                    if(userDetails.contentEquals("Incorrect"))
                        Toast.makeText(getApplicationContext(),"Please enter correct credentials",Toast.LENGTH_SHORT).show();
                    else {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("display", userDetails);
                        editor.apply();

                        Intent displayScreen = new Intent(MainActivity.this, DisplayActivity.class);
                        startActivity(displayScreen);
                    }
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerScreen = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registerScreen);
            }
        });



    }
}