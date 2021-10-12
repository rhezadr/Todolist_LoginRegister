package com.app.todolist_loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.todolist_loginregister.Utils.DatabaseHandlerLoginRegister;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private TextView loginButton;
    EditText regUserName;
    EditText regUserPassword;
    Button regButton;
    DatabaseHandlerLoginRegister dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Objects.requireNonNull(getSupportActionBar()).hide();

        dbhelper = new DatabaseHandlerLoginRegister(this);

        regUserName = (EditText) findViewById(R.id.UsernameRegister);
        regUserPassword = (EditText) findViewById(R.id.PasswordRegister);
        regButton = (Button) findViewById(R.id.RegisterButton);
        loginButton = findViewById(R.id.LoginClickText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(intent);
            }
        });

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = regUserName.getText().toString().trim();
                String password = regUserPassword.getText().toString().trim();

                ContentValues values = new ContentValues();


                if (password.equals("") || username.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Username or Password cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    values.put(DatabaseHandlerLoginRegister.row_username, username);
                    values.put(DatabaseHandlerLoginRegister.row_password, password);
                    dbhelper.insertData(values);

                    Toast.makeText(RegisterActivity.this, "Register successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
