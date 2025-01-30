package com.scm.services;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import com.scm.entities.Contact;

import java.util.HashMap;
import org.apache.commons.lang3.tuple.Pair; 

public class ContactManager {

    public void processContacts(List<Contact> contacts) {

        // Before (without var):
        // List<String> contactNames = new ArrayList<>();
        // Map<String, List<Contact>> contactsByCity = new HashMap<>();

        // After (using var):
        var contactNames = new ArrayList<String>();  // Type inferred as List<String>
        var contactsByCity = new HashMap<String, List<Contact>>(); // Type inferred

        for (var contact : contacts) { // Type inferred as Contact
            contactNames.add(contact.getName());
            // ... other processing ...
        }

        // Even more complex types:
        // Before:
        // Map<String, List<Pair<Integer, Contact>>> complexMap = new HashMap<>();

        // After (much cleaner with var):
        var complexMap = new HashMap<String, List<Pair<Integer, Contact>>>(); // Inferred

        // Example with streams:
        var gmailContacts = contacts.stream()
                .filter(c -> c.getEmail().endsWith("@gmail.com"))
                .collect(Collectors.toList()); // Type is List<Contact>

        // Example with Optional:
        var id = 1; // Example id, replace with actual logic to get id
        Optional<Contact> optionalContact = getContactById(id); // Type is Optional<Contact> if getContactById returns Optional<Contact>
        optionalContact.ifPresent(c -> System.out.println(c.getName()));
    }

    // Method to get contact by id
    public Optional<Contact> getContactById(int id) {
        // Implement the logic to get a contact by id
        // For now, return an empty Optional
        return Optional.empty();
    }

    // ... other methods ...
}
