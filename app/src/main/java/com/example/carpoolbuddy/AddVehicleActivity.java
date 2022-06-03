package com.example.carpoolbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddVehicleActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    private EditText ownerField;
    private EditText carModelField;
    private EditText capacityField;
    private EditText ageField;
    private EditText emailField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        ownerField = findViewById(R.id.editTextOwner);
        carModelField = findViewById(R.id.editTextCarModel);
        capacityField = findViewById(R.id.editTextCapacity);
        ageField = findViewById(R.id.editTextAge);
        emailField = findViewById(R.id.emailField);
    }

    public int convertStringToInteger(String inputString){
        int number = Integer.parseInt(inputString);
        return number;
    }

    private void goToVehicleInfoActivity() {
        Intent switchActivityIntent = new Intent(this, VehiclesInfoActivity.class);
        startActivity(switchActivityIntent);
    }

    public boolean formValid() {
//Checks all of the textEdits and returns true if they all include valid info.
//Show toast on error.
        String ownerString = ownerField.getText().toString();
        String carModelString = carModelField.getText().toString();
        String capacityString = capacityField.getText().toString();
        String ageString = ageField.getText().toString();
        String emailString = emailField.getText().toString();

        if (!(ownerString.isEmpty() || carModelString.isEmpty() || capacityString.isEmpty() || ageString.isEmpty() )) {
            if( convertStringToInteger(capacityString) > 0 && convertStringToInteger(ageString)> 0){
                return true;
            }
        } else {
            Toast errorFormValid = Toast.makeText(this, "Please enter useful information", Toast.LENGTH_LONG);
            errorFormValid.show();
            return false;
        }
        return false;
    }

    //done
    public void addNewVehicle(View v) {
        try{
            //Call formValid to check if input is ok
            formValid();

            //Check the type of vehicle chosen, use the database reference to store the vehicle subclass instance.
            // If a user adds a Car, upload a Car object, if user adds an Electric Car upload an ElectricCar object.
            String ownerString = ownerField.getText().toString();
            String carModelString = carModelField.getText().toString();
            String capacityString = capacityField.getText().toString();
            String ageString = ageField.getText().toString();
            String emailString = emailField.getText().toString();

            Vehicle newVehicle = new Vehicle (ownerString, carModelString, convertStringToInteger(capacityString), ageString, emailString );
            firestore.collection("Vehicles").document(newVehicle.getVehicleID()).set(newVehicle);
            goToVehicleInfoActivity();
            finish();
        }

        catch (Exception e) {
            Toast error = Toast.makeText(this, "Please enter useful information", Toast.LENGTH_LONG);
            error.show();
        }

    }
}