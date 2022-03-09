package com.example.carpoolbuddy;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class VehicleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    protected TextView nameText;
    protected TextView seatsLeftTextView;
    Context mContext;

    private VehicleRecyclerViewAdapter.RecyclerViewClickLister listener;

    //User CAN click on recyclerView rows and see information for the vehicle

    public VehicleViewHolder(@NonNull View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        nameText = itemView.findViewById(R.id.nameTextView);
        seatsLeftTextView = itemView.findViewById(R.id.seatsLeftTextView);
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        listener.onClick(view, getAdapterPosition());
    }
}
