package com.example.anas_ch.livetracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SignIn extends AppCompatActivity {

    List<Users> list = null;

    Button SignUpButton;
    EditText userNameSignIn,passwordSignIn;
    Button SignInButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference databaseReference = database.getReference().child("foo");
            databaseReference.addValueEventListener(new MyHandler());
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Something Went wrong/nCheck your connection",Toast.LENGTH_SHORT).show();
        }

        userNameSignIn = (EditText)findViewById(R.id.userNameSignIn);
        passwordSignIn = (EditText)findViewById(R.id.passwordSignIn);
        SignUpButton = (Button)findViewById(R.id.SignUpButton);
        SignInButton = (Button)findViewById(R.id.SignInButton);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SingUp.class);
                startActivity(i);
            }
        });

        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = false;
                String name= userNameSignIn.getText().toString();
                String password = passwordSignIn.getText().toString();
                String id;
                try {
                    for (Users value : list) {
                        if (value.getName().equals(name) && value.getPassword().equals(password)) {
                            userNameSignIn.setText("");
                            passwordSignIn.setText("");
                            id = value.getId();
                            Intent i = new Intent(getApplicationContext(), Decider.class);
                            i.putExtra("id", id);
                            i.putExtra("name", name);
                            i.putExtra("password", password);
                            startActivity(i);
                            check = true;
                            break;
                        }
                    }
                    if (!check)
                        Toast.makeText(getApplicationContext(), "User Not Found", Toast.LENGTH_LONG).show();
                    else
                        check = false;
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Something Went wrong/nCheck your connection",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    class MyHandler implements ValueEventListener {



        @Override
        public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
            list = new ArrayList<>();
            Iterable<com.google.firebase.database.DataSnapshot> children = dataSnapshot.getChildren();
            for (com.google.firebase.database.DataSnapshot child : children) {
                Users value = child.getValue(Users.class);
                value.setId(child.getKey());
                list.add(value);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }
}
