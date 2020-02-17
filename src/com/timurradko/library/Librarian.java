package com.timurradko.library;

import com.timurradko.entity.Book;
import com.timurradko.entity.LibBook;

import java.util.List;
import java.util.Map;

public class Librarian {
    private Library library;
    private final Map<Long, LibBook> ALL_BOOKS;
    private final Map<String, List<LibBook>> BOOKS_BY_AUTHORS;

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
}
