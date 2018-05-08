package com.example.anas_ch.livetracking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SingUp extends AppCompatActivity{


    EditText userNameSignUp,passwordSignUp,phoneNumberSignUp;
    Button SignUpButton;
    String longitude,latitude;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    Users newUser = new Users();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("foo");

        userNameSignUp = (EditText)findViewById(R.id.userNameSignUp);
        passwordSignUp = (EditText)findViewById(R.id.passwordSignUp);
        phoneNumberSignUp = (EditText)findViewById(R.id.phoneNumberSignUp);
        SignUpButton = (Button)findViewById(R.id.SignUpButton2);
        longitude = "35.32";
        latitude = "78.23";

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newUser.setName(userNameSignUp.getText().toString());
                newUser.setPassword(passwordSignUp.getText().toString());
                newUser.setPhoneNumber(phoneNumberSignUp.getText().toString());
                newUser.setRequest("false");
                newUser.setLatitude(latitude);
                newUser.setLongitude(longitude);
                databaseReference.push().setValue(newUser);
                Toast.makeText(getApplicationContext(),"User Added Successfully",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


}
