package com.example.CampMastery.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.CampMastery.R;
import com.example.CampMastery.db.DbHelper_User;

public class LoginActivity extends AppCompatActivity {


    DbHelper_User dbUser;
    EditText edtEmail, edtPassword;
    Button btnLogin;
    TextView linkCreateAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbUser = new DbHelper_User(this);
        edtEmail = findViewById(R.id.edt_email_login);
        edtPassword = findViewById(R.id.edt_password_login);
        linkCreateAcc = findViewById(R.id.create_acc);
        btnLogin = findViewById(R.id.btn_login);


        btnLogin.setOnClickListener(v -> {
            //validate input data
            if (edtEmail.getText().toString().isEmpty()) {
                edtEmail.setError("field must have a value");
            } else if (edtPassword.getText().toString().isEmpty()) {
                edtPassword.setError("field must have a value");
            } else {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                try {
                    Cursor dataUser = dbUser.getUser(email, password);

                    if (dataUser.getCount() == 0) {
                        Toast.makeText(LoginActivity.this, "Data tidak valid. cek data anda.", Toast.LENGTH_SHORT).show();
                    }else {
                        Intent moveToDashboard = new Intent(LoginActivity.this, MainActivity.class);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                        startActivity(moveToDashboard);
                        finish();
                    }

                } catch (Exception e) {
                    Log.e("MyApp", "Terjadi kesalahan: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });

        linkCreateAcc.setOnClickListener(v -> {
            Intent moveToRegister = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(moveToRegister);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
            finish();
        });
    }


}