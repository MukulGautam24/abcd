package com.scm.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import com.scm.entities.Contact;
import com.scm.entities.User;

public interface ContactService {
    // Save contact
    Contact save(Contact contact);

    // Update contact
    Contact update(Contact contact);

    // Get all contacts
    List<Contact> getAll();

    // Lambda expression to get contacts with Gmail
    default List<Contact> getGmailContacts(List<Contact> contacts) {
        return contacts.stream()
                .filter(c -> c.getEmail() != null && c.getEmail().endsWith("@gmail.com"))
                .collect(Collectors.toList());
    }


    // Get contacts whose name starts with 'M' using Streams API
    default List<Contact> getContactsStartingWithM(List<Contact> contacts) {
        return contacts.stream()
                .filter(contact -> contact.getName().startsWith("M"))
                .collect(Collectors.toList());
    }

    // Get only contact names using Streams API
    default List<String> getAllContactNames(List<Contact> contacts) {
        return contacts.stream()
                .map(Contact::getName)
                .collect(Collectors.toList());
    }

    // Sort contacts alphabetically using Streams API
    default List<Contact> getSortedContacts(List<Contact> contacts) {
        return contacts.stream()
                .sorted((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()))
                .collect(Collectors.toList());
    }

    // Get contact by ID using Optional
    default Optional<Contact> getByIdOptional(List<Contact> contacts, String id) {
        return contacts.stream()
                .filter(contact -> contact.getId().equals(id))
                .findFirst();
    }

    // Count contacts with Gmail using Streams API
    default long countGmailContacts(List<Contact> contacts) {
        return contacts.stream()
                .filter(contact -> contact.getEmail().endsWith("@gmail.com"))
                .count();
    }

    // Format contact using static method reference
    static String formatContact(Contact contact) {
        return contact.getName() + " - " + contact.getEmail();
    }

    // Sort contacts alphabetically using parallel streams and multithreading working parallel
    default void sortContactsParallel(List<Contact> contacts) {
        Contact[] contactArray = contacts.toArray(new Contact[0]);
        Arrays.parallelSort(contactArray, (c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
    }
    

    // Get contact by ID
    Contact getById(String id);

    // Delete contact
    void delete(String id);

    // Search contacts
    Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order, User user);
    Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order, User user);
    Page<Contact> searchByPhoneNumber(String phoneNumberKeyword, int size, int page, String sortBy, String order, User user);

    // Get contacts by userId
    List<Contact> getByUserId(String userId);

    // Get contacts by user with pagination
    Page<Contact> getByUser(User user, int page, int size, String sortField, String sortDirection);
}
