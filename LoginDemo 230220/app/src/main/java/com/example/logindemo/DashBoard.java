package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class DashBoard extends AppCompatActivity {

    DatePickerDialog picker;
    private EditText dateTV,carTV,pickupTV,timeTV,durTV;
    private Button submit;
    DatabaseReference databaseReference, databaseKey, databaseUser;
    private FirebaseAuth firebaseAuth;
    Checkout checkout;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour, currentMinute;
    String amPm;

    String [] car = {
            "Axia",
            "Saga",
            "Civic",
            "Aruz",
            "Ferrari",
            "Myvi",
    };

    Integer [] dur = {
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10,
            12,
            24,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        carTV=(AutoCompleteTextView)findViewById(R.id.carTV);
        timeTV=(EditText)findViewById(R.id.timeTV);
        dateTV=(EditText)findViewById(R.id.dateTV);
        pickupTV=(EditText)findViewById(R.id.pickupTV);
        durTV=(EditText)findViewById(R.id.durTV);
        submit=(Button)findViewById(R.id.btnsubmit);


        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Checkout");
        databaseUser = FirebaseDatabase.getInstance().getReference("UserInfo").child(firebaseAuth.getUid());

        dateTV=(EditText)findViewById(R.id.dateTV);
        dateTV.setInputType(InputType.TYPE_NULL);
        dateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(DashBoard.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateTV.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        timeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(DashBoard.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        timeTV.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carz = carTV.getText().toString();
                String timez = timeTV.getText().toString();
                String datez = dateTV.getText().toString();
                String pickupz = pickupTV.getText().toString();
                String duration = durTV.getText().toString();

                if(!TextUtils.isEmpty(carz) && !TextUtils.isEmpty(timez) && !TextUtils.isEmpty(datez) && !TextUtils.isEmpty(pickupz) ){

                    String id = databaseReference.push().getKey();

                    Checkout checkout = new Checkout(carz,timez,datez,pickupz,id,duration);

                    databaseReference.child(firebaseAuth.getUid()).setValue(checkout);


                    Toast.makeText(DashBoard.this, "Sent to database", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(DashBoard.this, RetrieveData.class));

                }
                else{
                    Toast.makeText(DashBoard.this,"Please enter",Toast.LENGTH_SHORT).show();
                }

            }
        });


       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, car);
        AutoCompleteTextView textView = (AutoCompleteTextView)findViewById(R.id.carTV);
        textView.setThreshold(1);
        textView.setAdapter(adapter);

        ArrayAdapter<Integer> adapter2 = new ArrayAdapter<Integer>(this, android.R.layout.simple_dropdown_item_1line, dur);
        AutoCompleteTextView textView2 = (AutoCompleteTextView)findViewById(R.id.durTV);
        textView2.setThreshold(1);
        textView2.setAdapter(adapter2);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.book:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        return true;
                }
                return false;

            }
        });
    }

}
