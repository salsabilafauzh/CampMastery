package com.example.CampMastery.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.CampMastery.R;
import com.example.CampMastery.db.DbHelper_User;

public class RegisterActivity extends AppCompatActivity {
    DbHelper_User dbUser;
    EditText edtEmail, edtPassword, edtUsername;
    Button btnRegister;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbUser = new DbHelper_User(this);

        edtEmail = findViewById(R.id.edt_email_register);
        edtUsername = findViewById(R.id.edt_username_register);
        edtPassword  =findViewById(R.id.edt_password_register);

        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(v->{
            if (edtEmail.getText().toString().isEmpty()) {
                edtEmail.setError("field must have a value");
            } else if (edtPassword.getText().toString().isEmpty()) {
                edtPassword.setError("field must have a value");
            }else if(edtUsername.getText().toString().isEmpty()){
                edtUsername.setError("field must have a value");
            }else{
                dbUser.addNewUser(edtUsername.getText().toString(),edtEmail.getText().toString(),edtPassword.getText().toString());
                edtPassword.setText("");
                edtUsername.setText("");
                edtEmail.setText("");
                Intent moveToLogin = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(moveToLogin);
                finish();
            }
        });
    }





}