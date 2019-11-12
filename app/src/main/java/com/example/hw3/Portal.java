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

public class Portal extends AppCompatActivity implements View.OnClickListener{

    TextView textViewPortal;
    Button buttonGoToCheckInventory, buttonGoToChangeInventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal);

        buttonGoToCheckInventory = findViewById(R.id.buttonGoToCheckInventory);
        buttonGoToChangeInventory = findViewById(R.id.buttonGoToChangeInventory);

        buttonGoToCheckInventory.setOnClickListener(this);
        buttonGoToChangeInventory.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);

        //make sure return is always the last time
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.itemPortal){

            Toast.makeText(this, "You are already on the Portal Page", Toast.LENGTH_SHORT).show();

            Intent portalIntent = new Intent (this,Portal.class);
            startActivity(portalIntent);


        }else if(item.getItemId() == R.id.itemCheckInventory){

            Intent checkInventoryIntent = new Intent (this,CheckInventory.class);
            startActivity(checkInventoryIntent);

        }else if (item.getItemId() == R.id.itemChangeInventory){

            Intent changeInventoryIntent = new Intent (this,ChangeInventory.class);
            startActivity(changeInventoryIntent);

        }else if (item.getItemId() == R.id. itemLogOut){

            //This is just sending the user back to the login screen: MainActivity

            Intent logOut= new Intent (this,MainActivity.class);
            startActivity(logOut);


            Intent logOutIntent = new Intent (this,MainActivity.class);
            startActivity(logOutIntent );



        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        if (view == buttonGoToCheckInventory){

            Intent portalGoToCheckInventory= new Intent (this,CheckInventory.class);
            startActivity(portalGoToCheckInventory);

        }else if(view == buttonGoToChangeInventory){

            Intent portalGoToChangeInventory= new Intent (this,ChangeInventory.class);
            startActivity(portalGoToChangeInventory);

        }

    }
}
