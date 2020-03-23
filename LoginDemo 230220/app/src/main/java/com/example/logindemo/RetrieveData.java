package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RetrieveData extends AppCompatActivity {

    private TextView a, b, c, d;
    DatabaseReference databaseReference,databaseKey;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    private String newID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_data);

        a = findViewById(R.id.tvbc);
        b = findViewById(R.id.tvbt);
        c = findViewById(R.id.tvbd);
        d = findViewById(R.id.tvbp);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

//        databaseKey = FirebaseDatabase.getInstance().getReference("UserKey").child(firebaseAuth.getUid());
//
//        databaseKey.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                newID = dataSnapshot.child("id").getValue().toString();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        databaseReference = firebaseDatabase.getReference("Checkout").child(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //UserSaveKey userSaveKey = dataSnapshot.getValue(UserSaveKey.class);
                //String uniqueId = userSaveKey.getId();

                String car= dataSnapshot.child("carz").getValue().toString();
                String time= dataSnapshot.child("timez").getValue().toString();
                String date= dataSnapshot.child("datez").getValue().toString();
                String pickup= dataSnapshot.child("pickupz").getValue().toString();
                a.setText("Car : " + car);
                b.setText("Time : " + time);
                c.setText("Date : " + date);
                d.setText("Pick Up Place : " + pickup);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(RetrieveData.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}


