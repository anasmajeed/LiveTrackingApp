package com.example.anas_ch.livetracking;

import android.app.ListActivity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Friends extends AppCompatActivity {

    Users Value;
    List<Users> list = null;
    String namefromback,passwordfromback,idfromback;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = database.getReference();

        idfromback = getIntent().getStringExtra("id");
        namefromback = getIntent().getStringExtra("name");
        passwordfromback = getIntent().getStringExtra("password");


        databaseReference.child("foo").addValueEventListener(new MyHandler());

        lv = (ListView)findViewById(R.id.listview);

        try {
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (list != null) {
                        try {
                            Users u = list.get(position);
                            Intent j = new Intent(getApplicationContext(), MapsActivity2.class);
                            j.putExtra("idfromback", idfromback);
                            j.putExtra("id", u.getId());
                            j.putExtra("latitude",u.getLatitude());
                            j.putExtra("longitude",u.getLongitude());
                            startActivity(j);
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Wait for few Seconds", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Wait for few Seconds", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Something went wrong wait",Toast.LENGTH_SHORT).show();
        }
    }

    class MyHandler implements ValueEventListener{



        @Override
        public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
            list = new ArrayList<>();
            Iterable<com.google.firebase.database.DataSnapshot> children = dataSnapshot.getChildren();
            for(com.google.firebase.database.DataSnapshot child : children){
                Users value = child.getValue(Users.class);
                value.setId(child.getKey());
                list.add(value);
            }
            ArrayAdapter<Users> obj = new ArrayAdapter<Users>(getApplicationContext(),android.R.layout.simple_list_item_1,list);
            lv.setAdapter(obj);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }



}
