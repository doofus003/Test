package com.example.calculatrice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculatrice.ContactDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ContactDatabase contactDatabase;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            // Handle item click to open details activity
            Contact selectedContact = ((ContactAdapter) listView.getAdapter()).getItem(i);

            Intent intent = new Intent(MainActivity.this, ContactDetailsActivity.class);
            intent.putExtra("contactName", selectedContact.name);
            intent.putExtra("contactPhone", selectedContact.phone);
            startActivity(intent);
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactDatabase = ContactDatabase.getDatabase(this);
        listView = findViewById(R.id.listView);

        // Set up adapter and populate the list view
        updateContactList();

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            // Handle item click to open details activity
            // You can start a new activity here
        });

        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            // Handle long press to delete contact
            deleteContact(i);
            return true;
        });
    }

    private void updateContactList() {
        // Fetch contacts from the database and update the adapter
        List<Contact> contacts = contactDatabase.contactDao().getAllContacts();
        ContactAdapter adapter = new ContactAdapter(this, contacts);
        listView.setAdapter(adapter);
    }

    private void deleteContact(int position) {
        // Delete the contact from the database and update the list
        Contact contactToDelete = ((ContactAdapter) listView.getAdapter()).getItem(position);
        contactDatabase.contactDao().delete(contactToDelete);
        updateContactList();
    }
}
