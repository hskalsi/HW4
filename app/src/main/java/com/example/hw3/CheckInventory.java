package com.example.hw3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CheckInventory extends AppCompatActivity implements View.OnClickListener{

    Button buttonCheckInventoryBackToPortal;
    TextView textViewCheckInventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_inventory);

        buttonCheckInventoryBackToPortal = findViewById(R.id.buttonCheckInventoryBackToPortal);

        buttonCheckInventoryBackToPortal.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.itemPortal){

            Intent portalIntent = new Intent (this,Portal.class);
            startActivity(portalIntent);

        }else if (item.getItemId() == R.id.itemCheckInventory){

            Toast.makeText(this, "You are already in Check Inventory", Toast.LENGTH_SHORT).show();


        }else if (item.getItemId() == R.id.itemChangeInventory){

            Intent changeInventoryIntent = new Intent (this,ChangeInventory.class);
            startActivity(changeInventoryIntent);

        }else if (item.getItemId() == R.id.itemLogOut){

            //This is just sending the user back to the login screen: MainActivity
            Intent logOut= new Intent (this,MainActivity.class);
            startActivity(logOut);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        Intent portalIntent = new Intent (this,Portal.class);
        startActivity(portalIntent);

    }
}
