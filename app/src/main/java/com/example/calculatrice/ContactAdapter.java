package com.example.calculatrice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    private final Context context;
    private final List<Contact> contactList;

    public ContactAdapter(@NonNull Context context, @NonNull List<Contact> contactList) {
        super(context, 0, contactList);
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Implement your getView method as needed
        // This is where you inflate your list item view and populate it with contact data
        // Example:
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_main, parent, false);
        }

        Contact contact = getItem(position);

        TextView textViewName = convertView.findViewById(R.id.textViewContactName);
        TextView textViewPhone = convertView.findViewById(R.id.textViewContactPhone);

        if (contact != null) {
            textViewName.setText(contact.getName());
            textViewPhone.setText(contact.getPhone());
        }

        return convertView;
    }
}
