package com.LibraryManagementSystem.service;

import com.LibraryManagementSystem.entity.Book;

import java.util.List;

public interface InventoryService {
    public List<Book> getAvailableBooks();
    public List<Book> getBorrowedBooks();
    public int getAvailableBooksCount();
    public int getBorrowedBooksCount();
    public int getTotalBooksCount();
}
