package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RetrieveData extends AppCompatActivity {

    private TextView a, b, c, d,totalPrice;
    DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    private String newID;
    private Button btnconfirm,btncancel;
    double HargaTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_data);

        btnconfirm = (Button)findViewById(R.id.btnconfirm);
        btncancel = (Button) findViewById(R.id.btncancel);

        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RetrieveData.this, Resit.class));
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RetrieveData.this, DashBoard.class));
            }
        });

        a = findViewById(R.id.tvbc);
        b = findViewById(R.id.tvbt);
        c = findViewById(R.id.tvbd);
        d = findViewById(R.id.tvbp);
        totalPrice = findViewById(R.id.tvbtp);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Checkout").child(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String car = dataSnapshot.child("carz").getValue().toString();
                String time = dataSnapshot.child("timez").getValue().toString();
                String date = dataSnapshot.child("datez").getValue().toString();
                String pickup = dataSnapshot.child("pickupz").getValue().toString();
                Integer durationz = Integer.valueOf(dataSnapshot.child("durationz").getValue().toString());
                a.setText("Car : " + car);
                b.setText("Time : " + time);
                c.setText("Date : " + date);
                d.setText("Pick Up Place : " + pickup);
                totalPrice.setText("Total Price : RM " + durationz);



                if (car.equals("Saga")) {
                    HargaTotal = 10.00 * durationz;
                } else if (car.equals("Axia")) {
                    HargaTotal = 9.00 * durationz;
                } else if (car.equals("Aruz")) {
                    HargaTotal = 12.00 * durationz;
                } else if (car.equals("Civic")) {
                    HargaTotal = 15.00 * durationz;
                } else if (car.equals("Ferrari")) {
                    HargaTotal = 50.00 * durationz;
                } else if (car.equals("Myvi")) {
                    HargaTotal = 8.00 * durationz;
                }

                totalPrice.setText("Total : RM " + String.format("%.2f", HargaTotal));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(RetrieveData.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}


