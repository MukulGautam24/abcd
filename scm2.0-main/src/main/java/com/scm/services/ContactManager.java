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
import java.util.SequencedCollection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import com.nimbusds.openid.connect.sdk.assurance.evidences.Organization;
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
            // contactNames.add(contact.getName());
            // ... other processing ...
        }

        // Even more complex types:
        // Before:
        // Map<String, List<Pair<Integer, Contact>>> complexMap = new HashMap<>();

        // After (much cleaner with var):
        var complexMap = new HashMap<String, List<Pair<Integer, Contact>>>(); // Inferred

        // Example with streams:
        // var gmailContacts = contacts.stream()
        //         .filter(c -> c.getEmail().endsWith("@gmail.com"))
        //         .collect(Collectors.toList()); // Type is List<Contact>

        // Example with Optional:
        var id = 1; // Example id, replace with actual logic to get id
        Optional<Contact> optionalContact = getContactById(id); // Type is Optional<Contact> if getContactById returns Optional<Contact>
        // optionalContact.ifPresent(c -> System.out.println(c.getName()));
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
    // private List<Contact> contacts = new ArrayList<>(); 

    // public void printGmailContacts() {
        // contacts.stream()
            // .filter((var c) -> c.getEmail().endsWith("@gmail.com")) 
            // .forEach((var c) -> System.out.println(c.getName())); 
    // }

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
    // String emailTemplate = """
    // <html>
    //     <body>
    //         <h1>Hello, ${contact.name}</h1>
    //         <p>...</p>
    //     </body>
    // </html>
    // """;

    // String sql = """
    // INSERT INTO contacts (name, email, phone)
    // VALUES ('${contact.name}', '${contact.email}', '${contact.phone}');
    // """;


    //Record Classes for Java 14
    // public record Contact(String name, String email, String phone) {} 

//    In your ContactManager class:
//      public void addContact(Contact contact) {
//      logic to add contact to the database ....
//    }

    // Example method to demonstrate pattern matching for instanceof
    public void checkInstance(Object object) {
        if (object instanceof Contact contact) { 
            // Access contact fields directly:
            // System.out.println("Contact Name: " + contact.getName()); 
        }
    }

    //Sealed Classes for Java 15
    public sealed class Contact permits Person, Company { 
        // ... Contact class definition ...
    }
    
    public final class Person extends Contact { 
        // ... Person class definition ...
    }
    
    public final class Company extends Contact { 
        // ... Company class definition ...
    }
    
    // It would be illegal to create a new class that extends Contact 
    // unless it is explicitly permitted by the Sealed Classes declaration.


    //Java 17 introduced enhanced switch expressions with pattern matching
    public String getContactType(Object obj) {
        return switch (obj) {
            case Person person -> "Person";
            case Company company -> "Company";
            case Organization organization -> "Organization";
            default -> "Unknown";
        };
    }



    //java 19 introduced virtual threads for lightweight concurrency
    // public void processContactsConcurrently() {
    //     ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor(); 

    //     List<Contact> contacts = getContacts(); 
    //     for (Contact contact : contacts) {
    //         executor.submit(() -> { 
    //             // Process contact asynchronously 
    //             // (e.g., update contact data, send notifications)
    //         });
    //     }

    //     executor.shutdown(); 
    //     executor.awaitTermination(1, TimeUnit.MINUTES); 
    // }

    //java 19 introduced enhanced switch expressions with pattern matching
    // public String getContactName(Object obj) {
    //     return switch (obj) {
    //         case Contact(String name, _) -> name; 
    //         case Company(String name, _) -> name; 
    //         default -> "Unknown";
    //     };
    // }

    //java 19 introduced enhanced switch expressions with pattern matching
    // public void processContactsWithStructuredConcurrency() {
    //     try (var scope = Scope.launch()) { 
    //         List<CompletableFuture<Void>> futures = new ArrayList<>();
    //         for (Contact contact : getContacts()) {
    //             futures.add(scope.fork(() -> { 
    //                 // Process contact asynchronously 
    //             }));
    //         }
    //         CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join(); 
    //     }
    // }

    // java 20 introduced scoped values for thread-local values

    // Define a Scoped Value for the currently logged-in user
    // ScopedValue<User> currentUser = ScopedValue.of(User.class);

    // Within a method that requires the current user:
    // User user = currentUser.get(); 

    // ... use the user object to perform actions ... 

    // Example of setting the current user within a scope:
    // try (Scope scope = Scope.open()) {
    //     currentUser.set(loggedInUser); 
        // ... perform operations that require the current user ...
    // } 

    // The currentUser value is automatically cleared when the scope is closed.

    //java 20 introduced pattern matching for switch expressions
    // public String getContactType(Object obj) {
    //     return switch (obj) {
    //         case Person(String name, String email, _) -> "Person"; 
    //         case Company(String name, String address, _) -> "Company"; 
    //         default -> "Unknown";
    //     };
    // }



    //java 21 introduced sequenced collections for deterministic iteration order
    // SequencedCollection<Contact> contacts = // ... obtain a sequenced collection ...

    // for (Contact contact : contacts) { 
    //     // Process contacts in the guaranteed order 
    // }


    


}
