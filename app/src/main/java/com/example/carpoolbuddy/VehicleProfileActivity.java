package com.example.carpoolbuddy;

import static java.lang.String.valueOf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

public class VehicleProfileActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore firestore;
    private FirebaseUser user;
    String ownerName;
    Button reserveButton;
    Button closeButton;
    Button openButton;
    Vehicle vehicleInfo;
    TextView ownerTextView;
    TextView ageTextView;
    TextView carModelTextView;
    TextView capacityTextView;
    TextView openTextView;
    String newCapacity;
    String userEmail;
    Vehicle chosenVehicle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_profile);
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        user = FirebaseAuth.getInstance().getCurrentUser();
        chosenVehicle =  com.example.carpoolbuddy.VehiclesInfoActivity.clickedVehicle;

        ownerTextView = findViewById(R.id.ownerView);
        ageTextView = findViewById(R.id.ageView);
        carModelTextView = findViewById(R.id.modelView);
        capacityTextView = findViewById(R.id.capacityView);
        openTextView = findViewById(R.id.openView);
        reserveButton = findViewById(R.id.reserveButton);
        closeButton = findViewById(R.id.closeButton);
        openButton = findViewById(R.id.openButton);
        userEmail = user.getEmail();

//        transfer object from one class to another with .getSerializableExtra()
//        vehicleInfo = (Vehicle) getIntent().getSerializableExtra("Vehicle");
//        ownerName = getIntent().getStringExtra("owner");
    }

    public void setUpButton(View v) {
    //Only shows “book ride” button if this user is not the owner of this vehicle.
        if (user.getEmail().equals(chosenVehicle.getEmail())) {
            reserveButton.setVisibility(View.INVISIBLE);
        } else {
            openButton.setVisibility(View.INVISIBLE);
            closeButton.setVisibility(View.INVISIBLE);
        }

        String modelName = chosenVehicle.getModel();
        String age = chosenVehicle.getAge();
        String owner = chosenVehicle.getOwner();
        String capacity = String.valueOf(chosenVehicle.getCapacity());
        String open = chosenVehicle.openToString();

        ownerTextView.setText(owner);
        ageTextView.setText(age);
        carModelTextView.setText(modelName);
        capacityTextView.setText(capacity);
        openTextView.setText(open);
        }

    public void byeRide(View v) {
        firestore.collection("Vehicles").whereEqualTo("model", chosenVehicle.getModel()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot snap: task.getResult().getDocuments()){
                    firestore.collection("Vehicles").document(snap.getId()).update("open", false);
                }
            }
         });

        goToVehiclesInfo();
    }

    public void hiRide(View v) {

            reserveButton.setText("Close my Vehicle");
            firestore.collection("Vehicles").whereEqualTo("model", chosenVehicle.getModel()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot snap: task.getResult().getDocuments()){
                        firestore.collection("Vehicles").document(snap.getId()).update("open", true);
                    }
                }
            });
        goToVehiclesInfo();
    }

    public void addNewVehicleButton(View v) {
        goToAddVehicle();
    }


    public void bookRide(View v) {
        //Reduce current capacity for this vehicle, in the database by 1.
        newCapacity = valueOf(vehicleInfo.getCapacity() - 1);
        capacityTextView.setText(newCapacity);
        Toast.makeText(this, "Just booked a seat~~", Toast.LENGTH_SHORT).show();
    }
    //Will be able to add a new vehicle to the database

    private void goToAddVehicle() {
        Intent switchActivityIntent = new Intent(this, AddVehicleActivity.class);
        startActivity(switchActivityIntent);
    }

    private void goToVehiclesInfo(){
        Intent switchActivityIntent = new Intent(this, VehiclesInfoActivity.class);
        startActivity(switchActivityIntent);
    }

}