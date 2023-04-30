package com.sudo248.firebasapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthCredential;

public class RegisterActivity extends AppCompatActivity {

    EditText edtUserName, edtPass;
    Button btnRegister;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUserName = findViewById(R.id.edtUserName);
        edtPass = findViewById(R.id.edtPass);
        btnRegister = findViewById(R.id.btnRegister);

        auth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(view -> {
            String email = edtUserName.getText().toString();
            String pass = edtPass.getText().toString();
//            auth.signInWithCredential();
            auth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}