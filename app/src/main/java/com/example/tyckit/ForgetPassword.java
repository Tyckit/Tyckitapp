package com.example.tyckit;

public class ForgetPassword {
    import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;


import android.app.AlertDialog;

import android.app.ProgressDialog;

import android.content.DialogInterface;

import android.content.Intent;

import android.os.Bundle;

import android.text.InputType;

import android.text.TextUtils;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;

import android.widget.LinearLayout;

import android.widget.TextView;

import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.OnFailureListener;

import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;


    public class LoginActivity extends AppCompatActivity {


        private EditText memail;

        private EditText mpass;

        private FirebaseAuth mAuth;

        private Toolbar mtoolbar;

        private Button login;

        TextView forgetpass;

        public ProgressDialog loginprogress;



        @Override

        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_login);



            mtoolbar=(Toolbar)findViewById(R.id.login_toolbar);

            setSupportActionBar(mtoolbar);



            mAuth = FirebaseAuth.getInstance();

            getSupportActionBar().setTitle("Login");



            forgetpass=findViewById(R.id.forgetpass);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            loginprogress=new ProgressDialog(this);

            memail=(EditText)findViewById(R.id.logemail);

            mpass=(EditText)findViewById(R.id.logpass);



            // click on forget password text

            forgetpass.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {

                    showRecoverPasswordDialog();

                }

            });



            login=(Button)findViewById(R.id.logbut);

            login.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {

                    String email=memail.getText().toString();

                    String password =mpass.getText().toString();

                    if(!TextUtils.isEmpty(email)||!TextUtils.isEmpty(password)){

                        loginprogress.setTitle("Logging In");

                        loginprogress.setMessage("Please Wait ");

                        loginprogress.setCanceledOnTouchOutside(false);

                        loginprogress.show();

                        loginUser(email,password);

                    }

                }

            });

        }



        ProgressDialog loadingBar;



        private void showRecoverPasswordDialog() {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);

            builder.setTitle("Recover Password");

            LinearLayout linearLayout=new LinearLayout(this);

            final EditText emailet= new EditText(this);



            // write the email using which you registered

            emailet.setText("Email");

            emailet.setMinEms(16);

            emailet.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

            linearLayout.addView(emailet);

            linearLayout.setPadding(10,10,10,10);

            builder.setView(linearLayout);



            // Click on Recover and a email will be sent to your registered email id

            builder.setPositiveButton("Recover", new DialogInterface.OnClickListener() {

                @Override

                public void onClick(DialogInterface dialog, int which) {

                    String email=emailet.getText().toString().trim();

                    beginRecovery(email);

                }

            });



            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                @Override

                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();

                }

            });

            builder.create().show();

        }



        private void beginRecovery(String email) {

            loadingBar=new ProgressDialog(this);

            loadingBar.setMessage("Sending Email....");

            loadingBar.setCanceledOnTouchOutside(false);

            loadingBar.show();



            // calling sendPasswordResetEmail

            // open your email and write the new

            // password and then you can login

            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {

                @Override

                public void onComplete(@NonNull Task<Void> task) {

                    loadingBar.dismiss();

                    if(task.isSuccessful())

                    {

                        // if isSuccessful then done message will be shown

                        // and you can change the password

                        Toast.makeText(LoginActivity.this,"Done sent",Toast.LENGTH_LONG).show();

                    }

                    else {

                        Toast.makeText(LoginActivity.this,"Error Occurred",Toast.LENGTH_LONG).show();

                    }

                }

            }).addOnFailureListener(new OnFailureListener() {

                @Override

                public void onFailure(@NonNull Exception e) {

                    loadingBar.dismiss();

                    Toast.makeText(LoginActivity.this,"Error Failed",Toast.LENGTH_LONG).show();

                }

            });

        }

        public void loginUser(String email,String password){

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                @Override

                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        loginprogress.dismiss();

                        Intent mainIntent = new Intent(LoginActivity.this,MainActivity.class);

                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        startActivity(mainIntent);

                        finish();

                    } else {

                        loginprogress.hide();

                        Toast.makeText(LoginActivity.this,"Cannot Sign In..Please Try Again",Toast.LENGTH_LONG);

                    }

                }

            });

        }
    }
}
