package com.LibraryManagementSystem.service.impl;

import com.LibraryManagementSystem.entity.BorrowingRecord;
import com.LibraryManagementSystem.enums.BorrowStatus;

import java.time.LocalDate;
import java.util.UUID;

public class BorrowingRecordFactory {

    public static BorrowingRecord createBorrowingRecord(
            String patronId,
            String isbn) {

        BorrowingRecord record = new BorrowingRecord();

        record.setRecordId(
                UUID.randomUUID().toString());

        record.setPatronId(patronId);

        record.setBookIsbn(isbn);

        record.setBorrowDate(LocalDate.now());

        record.setDueDate(
                LocalDate.now().plusDays(14));

        record.setBorrowStatus(
                BorrowStatus.BORROWED);

        return record;
    }
}