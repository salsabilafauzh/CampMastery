package com.example.CampMastery.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.CampMastery.R;
import com.example.CampMastery.db.DbHelper_User;

public class LoginActivity extends AppCompatActivity {


    DbHelper_User dbUser;
    EditText edtEmail, edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbUser = new DbHelper_User(this);

        edtEmail = findViewById(R.id.edt_email_login);
        edtPassword = findViewById(R.id.edt_password_login);
        btnLogin = findViewById(R.id.btn_login);


        btnLogin.setOnClickListener(v -> {
            //validate input data
            if (edtEmail.getText().toString().isEmpty()) {
                edtEmail.setError("field must have a value");
            } else if (edtPassword.getText().toString().isEmpty()) {
                edtPassword.setError("field must have a value");
            }
            String email = edtEmail.getText().toString();
            //find data in database

            try {
                // Kode Anda di sini
                String data = dbUser.getUserByEmail(email);
                if (data.length() == 0) {
                    Toast.makeText(LoginActivity.this, "Data tidak ditemukan.", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e("MyApp", "Terjadi kesalahan: " + e.getMessage());
                e.printStackTrace();
            }



        });
    }


}