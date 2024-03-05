package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername,edEmail,edPassword,edConfirmPassword;
    TextView tv;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername=findViewById(R.id.editTextRegUsername);
        edEmail=findViewById(R.id.editTextRegEmail);
        edPassword=findViewById(R.id.editTextRegPassword);
        edConfirmPassword=findViewById(R.id.editTextRegConfirmPassword);
        tv=findViewById(R.id.textViewExitingUser);
        btn=findViewById(R.id.buttonRegister);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edUsername.getText().toString();
                String email=edEmail.getText().toString();
                String password=edPassword.getText().toString();
                String confirmPassword=edConfirmPassword.getText().toString();

                Database db=new Database(getApplicationContext(),"healthcare",null,1);

                if(username.length()==0 || email.length()==0 || password.length()==0 || confirmPassword.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill All details",Toast.LENGTH_SHORT).show();
                } else {
                    if (password.compareTo(confirmPassword)==0) {
                        if(isValid(password)){
                            db.register(username,email,password);
                            Toast.makeText(getApplicationContext(),"Record Inserted",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

                        }else{
                            Toast.makeText(getApplicationContext(),"Password must contain at least 8 characters,having letter,digit and special symbol ",Toast.LENGTH_SHORT).show();

                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"Password and Confirm password didn't match",Toast.LENGTH_SHORT).show();
                    }
                }

            }


        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }

    public static boolean isValid(String passwordhere){
        int f1=0,f2=0,f3=0;

        if(passwordhere.length() < 8){
            return false;
        }else{
            for (int p=0;p<passwordhere.length();p++){
                char c=passwordhere.charAt(p);
                if(Character.isLetter(c)){
                    f1=1;
                } else if (Character.isDigit(c)) {
                    f2=1;
                } else if (c>=33 && c<=46 || c==64) {
                    f3=1;
                }
            }
            if(f1==1&&f2==1&&f3==1){
                return true;
            }
            return false;
            
        }
    }
}