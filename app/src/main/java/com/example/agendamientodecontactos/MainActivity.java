package com.example.agendamientodecontactos;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText txtName, txtNumber, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtName = findViewById(R.id.editName);
        txtNumber = findViewById(R.id.editNumber);
        txtEmail = findViewById(R.id.editEmail);
    }

    public void cmdSave_onClick(View v){
        String name = txtName.getText().toString();
        String number = txtNumber.getText().toString();
        String email = txtEmail.getText().toString();

        SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(name, number+ ";" + email);
        editor.apply();

        editor.commit();


        if(name.isEmpty() || number.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Data saved correctly", Toast.LENGTH_SHORT).show();
    }

    public void cmdSearch_onClick(View v){
        String name, number, email;
        String names = txtName.getText().toString();
        SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);

        name = preferences.getString(names,"");

        String[] parts = name.split(";");
        if(parts.length == 2){
            number = parts[0];
            email = parts[1];
            txtNumber.setText(number);
            txtEmail.setText(email);

            Toast.makeText(this, "Data of " + names + " found", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Data not found", Toast.LENGTH_SHORT).show();
        }

    }

    public void cmdCleanFields_onClick(View v){
        txtName.setText("");
        txtNumber.setText("");
        txtEmail.setText("");
    }
}