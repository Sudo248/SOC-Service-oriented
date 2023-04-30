package com.sudo248.firebasapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserName, edtPass;
    Button btnLogin, btnReset, btnRegister;

    FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUserName = findViewById(R.id.edtUserName);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnReset = findViewById(R.id.btnReset);
        btnRegister = findViewById(R.id.btnRegister);

        auth = FirebaseAuth.getInstance();

//        auth.signInWithCredential();

        btnLogin.setOnClickListener(view -> {
            String email = edtUserName.getText().toString();
            String pass = edtPass.getText().toString();

            auth.signInWithEmailAndPassword(email, pass).addOnSuccessListener(authResult -> {
                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }).addOnFailureListener(e -> Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show());
        });

        btnReset.setOnClickListener(view -> {
            String email = edtUserName.getText().toString();
            String pass = edtPass.getText().toString();
            auth.sendPasswordResetEmail(email)
                    .addOnSuccessListener(unused -> Toast.makeText(LoginActivity.this, "Check email", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        btnRegister.setOnClickListener(view -> {
            Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(i);
        });
    }
}