package com.app.todolist_loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.todolist_loginregister.Utils.DatabaseHandlerLoginRegister;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private TextView registerButton;
    EditText logUserName, logUserPassword;
    Button logButton;
    DatabaseHandlerLoginRegister db;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_PASS = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        registerButton = findViewById(R.id.RegisterClickText);
        logUserName = (EditText)findViewById(R.id.UsernameLogin);
        logUserPassword = (EditText)findViewById(R.id.PasswordLogin);
        logButton = (Button)findViewById(R.id.LoginButton);
        db = new DatabaseHandlerLoginRegister(this);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);
        if (name != null){
            Intent intent = new Intent (LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = logUserName.getText().toString().trim();
                String password = logUserPassword.getText().toString().trim();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME, logUserName.getText().toString());
                editor.putString(KEY_PASS,logUserPassword.getText().toString());
                editor.apply();


                Boolean res = db.checkUser(username,password);
                if(res == true){
                    Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else {
                    Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}