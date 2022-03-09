package com.example.carpoolbuddy;

import static java.lang.String.valueOf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class VehicleProfileActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore fireStore;
    private FirebaseUser user;
    String ownerName;
    Button reserveButton;
    Vehicle vehicleInfo;
    TextView ownerTextView;
    TextView ageTextView;
    TextView carModelTextView;
    TextView capacityTextView;
    String newCapacity;
    String name;
    String type;
    String capacity;
    double price;
    double balance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_profile);

        //get info from previous page
        //transfer object from one class to another with .getSerializableExtra()
        vehicleInfo = (Vehicle) getIntent().getSerializableExtra("Vehicle");
        ownerName = getIntent().getStringExtra("owner");

//set up and initialising
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();
        fireStore = FirebaseFirestore.getInstance();
        reserveButton = (Button) findViewById(R.id.reserveButton);
        ownerTextView = findViewById(R.id.ownerView);
        ownerTextView.setText(vehicleInfo.getOwner());
        ageTextView = findViewById(R.id.ageView);
        ageTextView.setText(vehicleInfo.getAge());
        carModelTextView = findViewById(R.id.modelView);
        carModelTextView.setText(vehicleInfo.getModel());
        capacityTextView = findViewById(R.id.capacityView);
        capacityTextView.setText(vehicleInfo.getCapacity());

        setUpButton();
    }

    private void goToAddVehicle() {
        Intent switchActivityIntent = new Intent(this, AddVehicleActivity.class);
        startActivity(switchActivityIntent);
    }


    //TODO 2: SET UP SETUP BUTTON
    public void setUpButton() {
//        //Only shows “book ride” button if this user is not the owner of this vehicle.
//        if (user.getUid().equals()) {
//
        }
        //Show the correct price in a label depending on the user’s role.

        //Teachers and students pay half price. Alumni and Parents pay full price.

    //Only shows “open/close” button if the user IS the owner of this vehicle.
    public void byeRide() {
        if (user.equals(vehicleInfo.getOwner())) {
            reserveButton.setText("Close my Vehicle");

        }
    }

    public void hiRide() {

    }

    public void addNewVehicleButton(View v) {
        goToAddVehicle();
    }

    //TODO BOOK RIDE
    public void bookRide() {
        //Reduce current capacity for this vehicle, in the database by 1.
        newCapacity = valueOf(vehicleInfo.getCapacity() - 1);
        capacityTextView.setText(newCapacity);
        Toast.makeText(this, "Just booked a seat~~", Toast.LENGTH_SHORT).show();
    }
    //Will be able to add a new vehicle to the database

}