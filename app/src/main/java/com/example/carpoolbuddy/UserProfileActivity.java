package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userEmail;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        user = mAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userEmail = user.getEmail();
        userName = user.getDisplayName();

        TextView greetingTextView = (TextView) findViewById(R.id.greeting);
        TextView emailTextView = (TextView) findViewById(R.id.emailAddress);

        greetingTextView.setText("welcome," + userName);
        emailTextView.setText(userEmail);

    }
    private void goToVehicleActivity() {
        Intent switchActivityIntent = new Intent(this, VehiclesInfoActivity.class);
        startActivity(switchActivityIntent);
    }

    private void goToAuthActivity(){
        Intent switchActivityIntent = new Intent(this, AuthActivity.class);
        startActivity(switchActivityIntent);
    }

    //done✨
    public void signOut(View v){
        //Create intent to go back to AuthActivity after signout complete.
        // Make sure to finish() this activity after starting the intent.
        try {
            mAuth.signOut();
            Toast.makeText(this, "User Signed out!", Toast.LENGTH_SHORT).show();
            goToAuthActivity();
            finish();
        }catch (Exception err) {
            Toast errorMessageToUser = Toast.makeText(this, "and I whoop..." + err.toString(), Toast.LENGTH_LONG);
            errorMessageToUser.show();
        }
    }

    //done✨
    public void seeVehicles(View v){
    goToVehicleActivity();
    finish();
    }

}
