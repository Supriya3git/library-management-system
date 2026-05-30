package com.LibraryManagementSystem.service;

import com.LibraryManagementSystem.entity.BorrowingRecord;

import java.util.List;

public interface LendingService {
    public void checkoutBook(String patronId, String isbn);
    public void returnBook(String patronId, String isbn);
    public List<BorrowingRecord> getLendingHistory(String patronId);
    public List<BorrowingRecord> getActiveLendings();
}
