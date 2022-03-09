package com.example.carpoolbuddy;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class VehicleRecyclerViewAdapter extends RecyclerView.Adapter<VehicleViewHolder> {
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private RecyclerViewClickLister listener;

    //we need to fetch these from firebases and save them into the arraylist
    ArrayList<String> carOwnerNames;
    ArrayList<String> carSeatsLeft;

    //TODO THERE IS SOMETHING WRONG WHEN I CLICK

    public VehicleRecyclerViewAdapter(ArrayList<Vehicle> vehicles, RecyclerViewClickLister listener) {
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        carOwnerNames = new ArrayList<String>();
        carSeatsLeft = new ArrayList<String>();
        this.listener = listener;

        for(Vehicle vehicle : vehicles){
            carOwnerNames.add(vehicle.getOwner());
            carSeatsLeft.add(String.valueOf(vehicle.getCapacity()));
        }
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cis_row_view, parent, false);
        VehicleViewHolder holder = new VehicleViewHolder(myView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        holder.nameText.setText(carOwnerNames.get(position));
        holder.seatsLeftTextView.setText(carSeatsLeft.get(position) + "seats left");

    }

    @Override
    public int getItemCount() {
        return carOwnerNames.size();
    }

    public interface RecyclerViewClickLister{
        void onClick(View v, int position);
    }

}
