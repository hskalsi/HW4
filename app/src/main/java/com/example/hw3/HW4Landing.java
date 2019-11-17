package com.example.hw3;

//This is Fully Completed

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HW4Landing extends AppCompatActivity implements View.OnClickListener {


    EditText editTextBirdName, editTextZip, editTextNameOfReporter;
    Button buttonReport, buttonSearch;
    TextView textViewHW4Intro, textViewIntro2, textViewReportaBird, textViewShowBird, textViewShowReporter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw4_landing);

        editTextBirdName = findViewById(R.id.editTextBirdName);
        editTextZip = findViewById(R.id.editTextZip);
        editTextNameOfReporter = findViewById(R.id.editTextNameOfReporter);

        buttonReport = findViewById(R.id.buttonReport);
        buttonSearch = findViewById(R.id.buttonSearch);

        textViewHW4Intro = findViewById(R.id.textViewHW4Intro);
        textViewIntro2 = findViewById(R.id.textViewIntro2);
        textViewReportaBird = findViewById(R.id.textViewReportaBird);
        textViewShowBird = findViewById(R.id.textViewShowBird);
        textViewShowReporter = findViewById(R.id.textViewShowReporter);

        buttonSearch.setOnClickListener(this);
        buttonReport.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Bird");

        if (view == buttonReport){

            String createBirdName = editTextBirdName.getText().toString();

            String zipCodeTransfer = editTextZip.getText().toString();
            int zipCode = Integer.parseInt(zipCodeTransfer);

            String createReporterName = editTextNameOfReporter.getText().toString();

            Bird myBird = new Bird(createBirdName,zipCode,createReporterName);

            myRef.push().setValue(myBird);

            Toast.makeText(this, "Report Successful!", Toast.LENGTH_SHORT).show();

        } else if (view == buttonSearch){

            //Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
            //This Test Worked

            //String zipCodeTransferForSearch = editTextZip.getText().toString();

            int zipSearch = Integer.parseInt(editTextZip.getText().toString());

            //would this be any different than doing what i do above?
            //int zipSearchTest = Integer.parseInt(editTextZip.getText());

            //Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
            //Test Successful
            //not 100% sure about this next line of code

            myRef.orderByChild("zip").equalTo(zipSearch).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    //change to not a String??
                    Bird foundBird = dataSnapshot.getValue(Bird.class);
                    String findBird = foundBird.birdName;
                    String findReporter = foundBird.reporter;

                    textViewShowBird.setText(findBird);
                    textViewShowReporter.setText(findReporter);

                    //String testOne = "test 1";
                    //String testTwo = "test 1";

                    //textViewShowBird.setText(testOne);
                    //textViewShowReporter.setText(testTwo);

                    textViewShowBird.setText(findBird);
                    textViewShowReporter.setText(findReporter);

                    //textView that displays the birdName
                    //textView that displays the reporter


                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }

    }
}
