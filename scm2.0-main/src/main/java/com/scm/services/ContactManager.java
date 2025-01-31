package com.scm.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import com.scm.entities.Contact; 

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


    //New String methods for Java 11 

    public void validateContactInfo() {
        String name = "   ";
        if (name.isBlank()) {
            System.out.println("Name is blank"); 
        }

        String phoneNumber = "123-456-7890";
        if (!phoneNumber.isBlank()) {
            // Validate phone number format
        }
    }

    public void printAddressLines() {
        String address = "123 Main St\nAnytown, CA 91234";
        address.lines().forEach(System.out::println);

        List<String> addressLines = address.lines().collect(Collectors.toList());
    }

    public void printTextExamples() {
        String text = "  Hello World! \u2005  ";
        String strippedText = text.strip();
        System.out.println(strippedText); 

        String trimmedText = text.trim();
        System.out.println(trimmedText); 
    }

    public void printRepeatedGreeting() {
        String greeting = "Hello";
        String repeatedGreeting = greeting.repeat(3);
        System.out.println(repeatedGreeting); 
    }

    //New HttpClient usage as Http/2 or websocket for Java 11

    public String fetchContactDetails(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new IOException("HTTP request failed: " + response.statusCode());
        }
    }


    //We can use var in lambda expressions for dealing with complex types
    private List<Contact> contacts = new ArrayList<>(); 

    public void printGmailContacts() {
        contacts.stream()
            .filter((var c) -> c.getEmail().endsWith("@gmail.com")) 
            .forEach((var c) -> System.out.println(c.getName())); 
    }

    //Switch Expressions for Java 12
    // public String getContactType(Contact contact) {
    //     return switch (contact.getType()) {
    //         case "PERSONAL" -> "Personal";
    //         case "WORK" -> "Work";
    //         case "OTHER" -> "Other";
    //         default -> "Unknown";
    //     };
    // }

    //Text Blocks for Java 13
    String emailTemplate = """
    <html>
        <body>
            <h1>Hello, ${contact.name}</h1>
            <p>...</p>
        </body>
    </html>
    """;

    String sql = """
    INSERT INTO contacts (name, email, phone)
    VALUES ('${contact.name}', '${contact.email}', '${contact.phone}');
    """;



}
