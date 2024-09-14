package com.example.shesafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shesafe.FirebaseExtraClasses.RegisterUserHelperClass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    Button btn_signup2;

    EditText et_sig_name,et_sig_email,et_sig_mobile_no,et_sig_password;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_signup2=findViewById(R.id.btn_signup2);
        et_sig_email=findViewById(R.id.et_sig_email);
        et_sig_name=findViewById(R.id.et_sig_name);
        et_sig_mobile_no = findViewById(R.id.et_sig_mobile_no);
        et_sig_password = findViewById(R.id.et_sig_pass);

        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor=preferences.edit();

        btn_signup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=et_sig_name.getText().toString();
                String email=et_sig_email.getText().toString();
                editor.putString("name",name).commit();
                editor.putString("email",email).commit();

                if (et_sig_name.getText().toString().isEmpty()){
                    et_sig_name.setError(("Please Enter Name"));
                } else if (et_sig_password.getText().toString().isEmpty()) {
                    et_sig_password.setError("Please Enter Password");
                } else if (et_sig_mobile_no.getText().toString().isEmpty()) {
                    et_sig_mobile_no.setError("Please Enter Mobile No");
                } else if (et_sig_email.getText().toString().isEmpty()) {
                    et_sig_email.setError("Please Enter Email");
                }else {

                    registerUser();
                }
                Intent i=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void registerUser() {

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Register_User");

        String userName = et_sig_name.getText().toString();
        String userPassword = et_sig_password.getText().toString();
        String userMobileNo = et_sig_mobile_no.getText().toString();
        String userEmailId = et_sig_email.getText().toString();

        RegisterUserHelperClass registerUserHelperClass = new RegisterUserHelperClass(userName,userMobileNo,userPassword,userEmailId);
        reference.child(userMobileNo).setValue(registerUserHelperClass);
        Toast.makeText(this, "Register Successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}