package com.LibraryManagementSystem.service.impl;

import com.LibraryManagementSystem.entity.Book;
import com.LibraryManagementSystem.service.BooksService;
import com.LibraryManagementSystem.service.InventoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class InventoryServiceImpl implements InventoryService {

    private static final Logger LOG = Logger.getLogger(InventoryServiceImpl.class.getName());
    private BooksService booksService;

    public InventoryServiceImpl(BooksService bookService) {
        this.booksService = bookService;
    }

    @Override
    public List<Book> getAvailableBooks(){
        LOG.info("Fetch available books.");
        List<Book> availableBooks = new ArrayList<>();
        for(Book book : booksService.getAllBooks()){
            if(book.isAvailable()){
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    @Override
    public List<Book> getBorrowedBooks(){
        LOG.info("Fetch borrowed books.");
        List<Book> borrowedBooks = new ArrayList<>();
        for(Book book : booksService.getAllBooks()){
            if(!book.isAvailable()){
                borrowedBooks.add(book);
            }
        }
        return borrowedBooks;
    }

    @Override
    public int getAvailableBooksCount(){
        return getAvailableBooks().size();
    }

    @Override
    public int getBorrowedBooksCount(){
        return getBorrowedBooks().size();
    }

    @Override
    public int getTotalBooksCount(){
        return booksService.getAllBooks().size();
    }
}
