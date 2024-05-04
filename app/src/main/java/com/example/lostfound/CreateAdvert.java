package com.example.lostfound;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateAdvert extends AppCompatActivity {

    EditText editTextName, editTextDescription, editTextPhone, editTextDate, editTextLocation;
    Button buttonSave;
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    RadioButton lost, found;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_advert);
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        editTextName = findViewById(R.id.editTextName);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextDate = findViewById(R.id.editTextDate);
        editTextLocation = findViewById(R.id.editTextLocation);
        buttonSave = findViewById(R.id.buttonSave);
        lost = findViewById(R.id.radioButtonLost);
        found = findViewById(R.id.radioButtonFound);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataToDB();
            }
        });
    }

    private void addDataToDB() {
        String name = editTextName.getText().toString();
        String description = editTextDescription.getText().toString();
        String phone = editTextPhone.getText().toString();
        String date = editTextDate.getText().toString();
        String location = editTextLocation.getText().toString();
        String type = "";
        if(lost.isChecked()) type = "lost";
        else if(found.isChecked()) type = "found";

        if(name.isEmpty() || description.isEmpty() || phone.isEmpty() || date.isEmpty() || location.isEmpty() || type.isEmpty()) {
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
            return;
        }
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("description", description);
        values.put("phone", phone);
        values.put("date", date);
        values.put("type", type);
        values.put("location", location);
        database.insert("my_table", null, values);
        Toast.makeText(this, "Advert created successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}