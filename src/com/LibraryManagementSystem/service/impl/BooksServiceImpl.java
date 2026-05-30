package com.LibraryManagementSystem.service.impl;

import com.LibraryManagementSystem.entity.Book;
import com.LibraryManagementSystem.exception.BookNotFoundException;
import com.LibraryManagementSystem.exception.DuplicateBookException;
import com.LibraryManagementSystem.service.BooksService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class BooksServiceImpl implements BooksService {

    private static final Logger LOG = Logger.getLogger(BooksServiceImpl.class.getName());
    Map<String,Book> map = new HashMap<>();
    private static BooksServiceImpl instance;
    private BooksServiceImpl(){
    }

    public static BooksServiceImpl getInstance() {
        if(instance == null){
            instance = new BooksServiceImpl();
        }
        return instance;
    }

    @Override
    public void addBook(Book book){
        if(book == null){
            throw new BookNotFoundException("Book cannot be null.");
        }
        if(map.containsKey(book.getISBN())){
            LOG.info("This Book already exists.");
            throw new DuplicateBookException("Book already exists with ISBN : "+book.getISBN());
        }

        map.put(book.getISBN(), book);
        LOG.info("Book added Successfully : "+book.getISBN());
    }

    @Override
    public void updateBook(String isbn, Book book){
        LOG.info("Update Book : "+book.getISBN());
        Book existingBook = map.get(isbn);
        if(existingBook == null){
            throw new BookNotFoundException("Book not found with ISBN : "+book.getISBN());
        }
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPublicationYear(book.getPublicationYear());
        LOG.info("Book updated Successfully : "+book.getISBN());
    }

    @Override
    public void deleteBook(String isbn){
        LOG.info("Delete Book : "+isbn);
        if(!map.containsKey(isbn)){
            throw new BookNotFoundException("Book not found with ISBN : "+isbn);
        }
        map.remove(isbn);
        LOG.info("Book deleted Successfully : "+isbn);
    }

    @Override
    public Book getBookByIsbn(String isbn){
        LOG.info("Searching Book with ISBN : "+isbn);
        Book book = map.get(isbn);
        if(book == null){
            throw new BookNotFoundException("Book not found with ISBN : "+isbn);
        }
        return book;
    }

    @Override
    public List<Book> getAllBooks(){
        LOG.info("Fetching all books.");
        return new ArrayList<>(map.values());
    }

}