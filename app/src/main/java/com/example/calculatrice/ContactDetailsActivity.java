package com.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.os.Bundle;

public class ContactDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        // Get data from the intent
        Intent intent = getIntent();
        String contactName = intent.getStringExtra("contactName");
        String contactPhone = intent.getStringExtra("contactPhone");

        // Populate UI
        TextView textViewContactName = findViewById(R.id.textViewContactName);
        TextView textViewContactPhone = findViewById(R.id.textViewContactPhone);

        textViewContactName.setText(contactName);
        textViewContactPhone.setText(contactPhone);

        // Button for Dialing
        Button buttonDial = findViewById(R.id.buttonDial);
        buttonDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber(contactPhone);
            }
        });
    }

    private void dialPhoneNumber(String phoneNumber) {
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(dialIntent);
    }
}