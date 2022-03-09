package com.example.registerandlogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        final EditText userName = findViewById(R.id.etNewName);
        final EditText email = findViewById(R.id.etNewEmail);
        final EditText password = findViewById(R.id.etNewPassword);
        Button btnRegister = findViewById(R.id.btnNewRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newUser = userName.getText().toString();
                String newPassword = password.getText().toString();
                String newEmail = email.getText().toString();

                if(newUser.isEmpty() || newPassword.isEmpty() || newEmail.isEmpty())
                    Toast.makeText(getApplicationContext(),"Please enter all the values",Toast.LENGTH_SHORT).show();
                else
                {
                    SharedPreferences preferences =  getSharedPreferences("MYPREFS", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(newUser + newPassword + "data", newUser + "-" + newEmail);
                    editor.apply();

                    Intent loginScreen = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(loginScreen);
                }
            }
        });
    }
}
