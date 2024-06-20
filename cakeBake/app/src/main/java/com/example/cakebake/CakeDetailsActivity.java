package com.example.cakebake;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.widget.Toast;


public class CakeDetailsActivity extends AppCompatActivity {

    private CakeBakeDB cakeBakeDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake_details);

        cakeBakeDB = new CakeBakeDB(this);

        // Get cake name from intent extra
        String cakeName = getIntent().getStringExtra("cakeName");

        // Fetch cake details from database
        // You need to implement this method in CakeBakeDB class
        // It should return a Cursor containing details of the selected cake
        // For now, assuming you have implemented this method as GetcupCakeInfo()
        // You can modify it as per your database structure
        Cursor cursor = cakeBakeDB.GetCakeInfo(cakeName);

        // Display cake details
        // Display cake details
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String flavor = cursor.getString(cursor.getColumnIndex(CakeBakeDB.tblcupCakeFlavour));
            @SuppressLint("Range") String category = cursor.getString(cursor.getColumnIndex(CakeBakeDB.tblcupCakeCategory));
            @SuppressLint("Range") String quantity = cursor.getString(cursor.getColumnIndex(CakeBakeDB.tblcupCakeqty));
            @SuppressLint("Range") String price = cursor.getString(cursor.getColumnIndex(CakeBakeDB.tblcupCakeprice));

            // Display details in TextViews or any other UI components
            TextView flavorTextView = findViewById(R.id.flavorTextView);
            flavorTextView.setText(flavor);
            TextView categoryTextView = findViewById(R.id.categoryTextView);
            categoryTextView.setText(category);

            TextView quantityTextView = findViewById(R.id.quantityTextView);
            quantityTextView.setText(quantity);

            TextView priceTextView = findViewById(R.id.priceTextView);
            priceTextView.setText(price);
            // Display other details similarly
        } else {
            // Handle the case when the cursor is empty or null
            Toast.makeText(this, "No details found for this cake", Toast.LENGTH_SHORT).show();

        }
    }
}
