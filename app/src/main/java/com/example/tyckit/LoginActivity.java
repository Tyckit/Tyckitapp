package com.example.tyckit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    TextView forgot_pwd;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        forgot_pwd = findViewById(R.id.forget_pwd);

        if (mAuth.getCurrentUser() != null) {
            finish();
            return;
        }

        TextView btnLogin = findViewById(R.id.next_button);
        btnLogin.setOnClickListener(view -> authenticateUser());
        forgot_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetMail = new EditText((v.getContext()));
                AlertDialog.Builder passwordReset = new AlertDialog.Builder(v.getContext());
                passwordReset.setTitle("Reset Your Password");
                passwordReset.setMessage("Enter email to receive the Reset link");
                passwordReset.setView(resetMail);

                passwordReset.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            String mail = resetMail.getText().toString();
                            mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    //Toast.makeText("this","Reset mail has been sent");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    //Toast.makeText("this","Reset mail is not sent");
                                }
                            });
                    }
                });
                passwordReset.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                passwordReset.create().show();
            }
        });

        /*/
        TextView tvSwitchToRegister = findViewById(R.id.tvSwitchToRegister);
        tvSwitchToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToRegister();
            }
        });
        /*/
    }

    private void authenticateUser() {
        EditText etLoginEmail = findViewById(R.id.user_id);
        EditText etLoginPassword = findViewById(R.id.password);

        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        showMainActivity();
                    } else {
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.animation_left, R.anim.animation_out_right);
    }


    private void showMainActivity() {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
        finish();
    }

    /*/
    private void switchToRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
     /*/
}
