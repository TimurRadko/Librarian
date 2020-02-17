package com.timurradko.entry;

import com.timurradko.entity.Book;
import com.timurradko.library.Librarian;
import com.timurradko.library.Library;

import java.text.ParseException;

public class LibManager {
    public static void main(String[] args) {
        Library library = new Library();
        Librarian librarian = new Librarian(library);

        try {
            librarian.addBook(new Book("Carrie", "Stephen King", "05.04.1974"));
            librarian.addBook(new Book("The Talisman", "Stephen King", "08.11.1984"));
            librarian.addBook(new Book("It", "Stephen King", "15.09.1986"));
            librarian.addBook(new Book("The Blade Itself", "Joe Abercrombie", "04.05.2006"));
            librarian.addBook(new Book("Before They Are Hanged", "Joe Abercrombie", "15.03.2007"));
            librarian.addBook(new Book("Last Argument of Kings", "Joe Abercrombie", "20.03.2008"));
            librarian.addBook(new Book("1984", "George Orwell", "08.06.1949"));
            System.out.println(library.viewAllBooks());
            System.out.println(library.find(new Book("1984", "George Orwell", "08.06.1949")));
            System.out.println(library.getByAuthor("George Orwell"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
