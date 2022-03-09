package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class VehiclesInfoActivity extends AppCompatActivity {
    RecyclerView recView;
    ArrayList carOwnerNames;
    ArrayList seatsLeft;

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private FirebaseUser user;
    private VehicleRecyclerViewAdapter.RecyclerViewClickLister listener;

    private ArrayList<Vehicle> vehicleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        vehicleList = new ArrayList<>();

        //Setup UI layout, firebase instances and initialize vehicles ArrayList.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_info);
        recView = findViewById(R.id.recView);

        carOwnerNames = new ArrayList();
        seatsLeft = new ArrayList();

        //we are getting information from the data base and we need to fetch all the data from the firebase
        getAndPopulateData();
        recView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void getAndPopulateData() {
        //Get all of the vehicles from the database that are open.
        //In vehicles, all users, collections
        firestore.collection("Vehicles").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot ds = task.getResult();
                    for (QueryDocumentSnapshot document : ds) {
                        //Use document.toObject(Vehicle.class)
                        // This will deserialize the contents of the database information and give you a Vehicle object.
                        Vehicle objectVehicle = document.toObject(Vehicle.class);

                        // Add all vehicles to the vehicles ArrayList.
                        //On completion of task for fetching all vehicles, set new RecyclerViewAdapter with the list of vehicles fetched.
                        vehicleList.add(objectVehicle);

                        Log.d("KEONA TEST", objectVehicle.getOwner());
                    }
                    setOnClickListener();
                    VehicleRecyclerViewAdapter myAdapter = new VehicleRecyclerViewAdapter(vehicleList, listener);
                    recView.setAdapter(myAdapter);

                } else {
                    Log.d("Error getting documents: ", String.valueOf(task.getException()));
                    //and this
                }
            }
        });

    }

    private void setOnClickListener() {
        listener = new VehicleRecyclerViewAdapter.RecyclerViewClickLister() {
            @Override
            public void onClick(View v, int position) {
                //go to vehicle info activity when you click~
                Intent intent = new Intent(getApplicationContext(), VehiclesInfoActivity.class);
                startActivity(intent);
            }
        };
    }

    public void goToAddVehicle(View v) {
        Intent switchActivityIntent = new Intent(this, AddVehicleActivity.class);
        startActivity(switchActivityIntent);
    }
}