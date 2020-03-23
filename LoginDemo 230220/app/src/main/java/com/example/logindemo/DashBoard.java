package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class DashBoard extends AppCompatActivity {

    DatePickerDialog picker;
    private EditText dateTV,carTV,pickupTV,timeTV;
    private Button submit;
    DatabaseReference databaseReference, databaseKey, databaseUser;
    private FirebaseAuth firebaseAuth;
    Checkout checkout;

   /* String [] car = {
            "Axia",
            "Saga",
            "Civic",
            "toyota",
            "Ferrari",
            "Myvi",
    }; */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        carTV=(EditText)findViewById(R.id.carTV);
        timeTV=(EditText)findViewById(R.id.timeTV);
        dateTV=(EditText)findViewById(R.id.dateTV);
        pickupTV=(EditText)findViewById(R.id.pickupTV);
        submit=(Button)findViewById(R.id.btnsubmit);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Checkout");
        databaseKey = FirebaseDatabase.getInstance().getReference("UserKey").child(firebaseAuth.getUid());
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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carz = carTV.getText().toString();
                String timez = timeTV.getText().toString();
                String datez = dateTV.getText().toString();
                String pickupz = pickupTV.getText().toString();

                if(!TextUtils.isEmpty(carz) && !TextUtils.isEmpty(timez) && !TextUtils.isEmpty(datez) && !TextUtils.isEmpty(pickupz) ){

                    String id = databaseReference.push().getKey();

                    Checkout checkout = new Checkout(carz,timez,datez,pickupz,id);

                    databaseReference.child(id).setValue(checkout);

                    UserSaveKey userSaveKey = new UserSaveKey(id);
                    databaseKey.setValue(userSaveKey);

                    Toast.makeText(DashBoard.this, "Sent to database", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(DashBoard.this, RetrieveData.class));

                }
                else{
                    Toast.makeText(DashBoard.this,"Please enter",Toast.LENGTH_SHORT).show();
                }

                /*carTV.setText("");
                timeTV.setText("");
                dateTV.setText("");
                pickupTV.setText("");*/



            }
        });


       /* ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, car);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.carTV);
        textView.setThreshold(1);
        textView.setAdapter(adapter); */

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
