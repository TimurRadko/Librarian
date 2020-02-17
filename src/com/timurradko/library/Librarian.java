package com.timurradko.library;

import com.timurradko.entity.Book;
import com.timurradko.entity.LibBook;

import java.util.*;

public class Librarian {
    private Library library;
    private final Map<Long, LibBook> ALL_BOOKS;
    private final Map<String, List<LibBook>> BOOKS_BY_AUTHORS;
    private Map <String, LibBook> takenBooks = new HashMap<>();

    public Librarian(Library library) {
        this.library = library;
        ALL_BOOKS = library.ALL_BOOKS;
        BOOKS_BY_AUTHORS = library.BOOKS_BY_AUTHORS;
    }

    public void addBook(Book book) {
        LibBook libBook = new LibBook(book);
        LibBook oldBook = ALL_BOOKS.put(libBook.ID, libBook);
        if (oldBook != null) {
            throw new RuntimeException("Book is already exist");
        }
        addToAuthorList(libBook);
    }

    private void addToAuthorList(LibBook book) {
        String author = book.getAuthor();
        List<LibBook> authorBooks = BOOKS_BY_AUTHORS.get(author);
        if (authorBooks == null) {
            authorBooks = new Library.LibrarySortedList(LibBook.COMPARATOR_BY_DATE);
            authorBooks.add(book);
            BOOKS_BY_AUTHORS.put(author, authorBooks);
        }
        authorBooks.add(book);
    }

    public void viewAllBooksAndId() {
        for (Map.Entry<Long, LibBook> pair : ALL_BOOKS.entrySet()) {
            Long key = pair.getKey();
            LibBook book = pair.getValue();
            System.out.println("ID book: " + key + ". Consist this book: " + book);
        }
    }

    public void viewAllTakenBooks() {
        for (Map.Entry<String, LibBook> pair : takenBooks.entrySet()) {
            String key = pair.getKey();
            LibBook book = pair.getValue();
            System.out.println("This man: " + key + ", took this books: " + book);
            if (key == null || book == null) {
                System.out.println("All books in the library");
            }
        }
    }

    public void removeBook(Book book, Reader reader) {
        Collection<LibBook> books = ALL_BOOKS.values();
        String author = book.getAuthor();
        Date date = book.getDate();
        String title = book.getTitle();
        for (LibBook libBook : books) {
            if (author.equals(libBook.getAuthor()) || date.equals(libBook.getDate()) || title.equals(libBook.getTitle())){
                takenBooks.put(reader.readerName, libBook);
                books.remove(libBook);
            }
        }
    }

    public void returnBook(LibBook book, Reader reader) {

    }
}
