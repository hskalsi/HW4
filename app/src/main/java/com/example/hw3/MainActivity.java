package com.example.hw3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//Rather than adding firebase to another project, I am integrating HW4 into HW3. With them exception of the button on main activity,
// the HWs do not actually interact. I did this to force myself to with in a file with other code, to help me work one something
//without breaking something else

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextEmail, editTextPassword;
    Button buttonLogIn, buttonRegister, buttonGoToHW4;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogIn = findViewById(R.id.buttonLogIn);
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonGoToHW4 = findViewById(R.id.buttonGoToHW4);

        buttonLogIn.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
        buttonGoToHW4.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void onClick(View view) {

        //Intent mainIntent = new Intent (this,Portal.class);
        //startActivity(mainIntent);

        if (view == buttonRegister){

            makeNewUsers(editTextEmail.getText().toString(),editTextPassword.getText().toString());

        }else if (view == buttonLogIn){

            signInExistingUsers(editTextEmail.getText().toString(),editTextPassword.getText().toString());

        }else if (view == buttonGoToHW4){

            Toast.makeText(this, "You are going to HW 4", Toast.LENGTH_SHORT).show();

            Intent GotoHWFour= new Intent (this, HW4Landing.class);
            startActivity(GotoHWFour);

        }
    }

    public void signInExistingUsers (String email, String password){

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "Login Successful - Good Job", Toast.LENGTH_SHORT).show();
                            // Sign in success, update UI with the signed-in user's information

                            //Log.d(TAG, "signInWithEmail:success");        -Pretty sure this is like a Toast
                            //FirebaseUser user = mAuth.getCurrentUser();       -Dont really know why I am commenting this one out yet
                            //updateUI(user);       -This does not seem like I need it

                            Intent logInIntent = new Intent (MainActivity.this,Portal.class);
                            startActivity(logInIntent);

                            //either the current ainactivity.this or it is a buttonLogIn.isClicked
                        } else {

                            Toast.makeText(MainActivity.this, "Login Failed - Try Again", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    public void makeNewUsers(String email, String password){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Registration Failed -Sorry :(", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
