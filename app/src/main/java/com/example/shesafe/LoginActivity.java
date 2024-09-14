package com.example.shesafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.logging.LoggingMXBean;

public class LoginActivity extends AppCompatActivity {

    Button btn_login2;
    EditText et_username,et_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login2=findViewById(R.id.btn_login2);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);

        btn_login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i=new Intent(LoginActivity.this,MainActivity.class);
//                startActivity(i);
//                finish();
                loginUser();
            }
        });
    }

    public void loginUser() {
        String userMail = et_username.getText().toString().trim();
        String userPassword = et_password.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Register_User");
        Query checkUserDatabase = reference.orderByChild("userMobileNo").equalTo(userMail);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        // Retrieve the password from the snapshot
                        String passwordFromDB = userSnapshot.child("userPassword").getValue(String.class);

                        // Use .equals() to compare strings
                        if (passwordFromDB.equals(userPassword)) {
                            et_username.setError(null);
                            et_password.setError(null);

                            // Retrieve other information from the snapshot
                            String userUserNameFromDB = userSnapshot.child("userName").getValue(String.class);

                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                           // intent.putExtra("userName", userUserNameFromDB);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "User Does Not Exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }
}