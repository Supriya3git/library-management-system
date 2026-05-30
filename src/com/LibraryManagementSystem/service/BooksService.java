package com.LibraryManagementSystem.service;

import com.LibraryManagementSystem.entity.Book;

import java.util.List;

public interface BooksService{
    public void addBook(Book book);
    public void updateBook(String isbn, Book book);
    public void deleteBook(String isbn);
    public Book getBookByIsbn(String isbn);
    public List<Book> getAllBooks();
}