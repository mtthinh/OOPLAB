package hust.soict.hedspi.aims.media;

import java.util.*;

public class Book extends Media {
    private List<String> authors = new ArrayList<String>();

    public Book(int id) {
        super.setId(id);
    }

    public void addAuthor(String authorName) {
        if (authors.contains(authorName)) {
            System.out.println("Author already exists.");
        } else {
            authors.add(authorName);
            System.out.println("Author added successfully.");
        }
    }

    public void removeAuthor(String authorName) {
        if (authors.contains(authorName)) {
            authors.remove(authorName);
            System.out.println("Author removed successfully.");
        } else {
            System.out.println("Author not found.");
        }
    }
}