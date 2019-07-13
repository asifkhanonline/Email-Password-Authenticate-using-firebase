package com.lco.form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    public EditText emailId,password;
    Button btnlogin,signup,forgot;
    FirebaseAuth mFirebaseAuth;
    //private FirebaseAuth.AuthStateListener mAuthStatelistener;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFirebaseAuth=FirebaseAuth.getInstance();
        emailId=findViewById(R.id.editText2);
        password=findViewById(R.id.editText3);
        btnlogin=findViewById(R.id.button2);
        signup=findViewById(R.id.button3);
        forgot=findViewById(R.id.button4);
        pd=new ProgressDialog(this);
        if(mFirebaseAuth.getCurrentUser()!=null){
            Intent i=new Intent(login.this,home.class);
            startActivity(i);

        }
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(emailId.getText().toString()!=null){
                    if(password.getText().toString().length()>=7){
                        String mail=emailId.getText().toString();
                        String pass=password.getText().toString();
                        login(mail,pass);
                        pd.setMessage("loading...");
                        pd.show();

                    }
                    else {
                        Toast.makeText(login.this,"enter password",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(login.this,"enter email",Toast.LENGTH_SHORT).show();

                }
            }
        });

//        mAuthStatelistener=new FirebaseAuth.AuthStateListener() {
//
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser mFirebaseUser=mFirebaseAuth.getCurrentUser();
//                if(mFirebaseUser !=null){
//                    finish();
//                    Toast.makeText(login.this,"You Are logged in",Toast.LENGTH_SHORT).show();
//                    Intent i=new Intent(login.this,home.class);
//                    startActivity(i);
//
//                }
//                else{
//                    Toast.makeText(login.this,"Please Login",Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        };
//
//btnlogin.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        String email=emailId.getText().toString();
//        String pwd=password.getText().toString();
//        if(email.isEmpty()){
//            emailId.setError("Please Enter email id");
//            emailId.requestFocus();
//        }
//        else if(pwd.isEmpty()){
//            password.setError("Please Enter password");
//            password.requestFocus();
//
//        }
//        else if(email.isEmpty()&&pwd.isEmpty()){
//            Toast.makeText(login.this,"Feilds are empty",Toast.LENGTH_SHORT).show();
//        }
//        else if(!(email.isEmpty()&&pwd.isEmpty())){
//            pd.setMessage("Please WAIT.........");
//            pd.show();
//
//            mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if (!task.isSuccessful()) {
//                        Toast.makeText(login.this, "login error,please login afgain", Toast.LENGTH_SHORT).show();
//
//                        Intent intToHome = new Intent(login.this, home.class);
//                        startActivity(intToHome);
//                        pd.cancel();
//
//                        finish();
//                    }
//
//                }
//            });
//        }
//        else{
//
//            Toast.makeText(login.this,"Error Ocurred!!",Toast.LENGTH_SHORT).show();
//
//   pd.cancel();
//        }
//    }
//
//});
//
//
    signup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intSignUp=new Intent(login.this,MainActivity.class);
            startActivity(intSignUp);
        }
    });

//    }
//    protected void onstart(){
//        super.onStart();
//        mFirebaseAuth.addAuthStateListener(mAuthStatelistener);

    }
    public void login(String email,String password){
        mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();

                    Intent i=new Intent(login.this,home.class);
                    startActivity(i);
                    pd.dismiss();
                }
                else{
                    Toast.makeText(login.this,"log failed",Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            }
        });
    }
}
