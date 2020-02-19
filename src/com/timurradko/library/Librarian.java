package com.timurradko.library;

import com.timurradko.entity.Book;
import com.timurradko.entity.LibBook;

import java.util.*;

public class Librarian {
    private Library library;
    private final Map<Long, LibBook> ALL_BOOKS;
    private final Map<String, List<LibBook>> BOOKS_BY_AUTHORS;
    private Map<Long, LibBook> takenBooks = new HashMap<>();
    private Map<Long, String> READER_DATA = new HashMap<>();

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
        for (Map.Entry<Long, LibBook> pair : takenBooks.entrySet()) {
            Long key = pair.getKey();
            LibBook libBook = pair.getValue();
            System.out.println("This man: " + key + ", took this books: " + libBook);
            if (key == null || libBook == null) {
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
            if (author.equals(libBook.getAuthor()) && date.equals(libBook.getDate()) && title.equals(libBook.getTitle())) {
                takenBooks.put(reader.IDReader, libBook);
                books.remove(libBook);
            }
        }
    }

        public void putBookInLibrary (Reader reader){
            for (Map.Entry<Long, LibBook> pair : takenBooks.entrySet()) {
                Long readerID = pair.getKey();
                if (readerID == reader.IDReader) {
                    LibBook libBook = pair.getValue();
                    ALL_BOOKS.put(libBook.ID, libBook);
                } else {
                    System.out.println("You didn't take books in our library");
                }
            }
            addReaderToDataBase(reader);
        }

        public void addReaderToDataBase(Reader reader) {
            READER_DATA.put(reader.IDReader, reader.readerName);
        }

        public void viewReaderData (Long IDReader) {
            String nameReader = READER_DATA.get(IDReader);
            System.out.println("The reader you were looking for: " + nameReader);
        }
    }
