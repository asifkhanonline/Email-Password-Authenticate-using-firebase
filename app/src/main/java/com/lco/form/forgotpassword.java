package com.lco.form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {
    Button button;
    EditText editText;
    TextView textView;
    private FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        button=findViewById(R.id.btn4);
        editText=findViewById(R.id.text1);
        textView=findViewById(R.id.edit1);

        mauth=FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Useremail=button.getText().toString();
                if(TextUtils.isEmpty(Useremail)){
                    Toast.makeText(forgotpassword.this,"Please enter your valid email.",Toast.LENGTH_SHORT).show();
                }else{
                    mauth.sendPasswordResetEmail(Useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(forgotpassword.this,"Please check your email",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(forgotpassword.this,login.class));
                            }else{
                                String message=task.getException().getMessage();
                                Toast.makeText(forgotpassword.this,"Error Occour:"+message,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });




    }
}