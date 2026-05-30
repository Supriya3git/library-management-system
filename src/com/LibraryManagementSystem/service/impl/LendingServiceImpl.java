package com.LibraryManagementSystem.service.impl;

import com.LibraryManagementSystem.entity.Book;
import com.LibraryManagementSystem.entity.BorrowingRecord;
import com.LibraryManagementSystem.entity.Patron;
import com.LibraryManagementSystem.enums.BorrowStatus;
import com.LibraryManagementSystem.service.BooksService;
import com.LibraryManagementSystem.service.LendingService;
import com.LibraryManagementSystem.service.PatronService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class LendingServiceImpl implements LendingService {

    private static final Logger LOG = Logger.getLogger(LendingServiceImpl.class.getName());
    private BooksService booksService;
    private PatronService patronService;

    List<BorrowingRecord> borrowingRecords = new ArrayList<>();

    public LendingServiceImpl(BooksService bookService, PatronService patronService) {
        this.booksService = bookService;
        this.patronService = patronService;
    }

    @Override
    public void checkoutBook(String patronId, String isbn){
        LOG.info("Issuing Book with ISBN : "+isbn+"and Patron ID : "+patronId);
        Patron patron = patronService.getPatronById(patronId);
        if(!patron.isActiveMember()){
            throw new RuntimeException("Patron membership is inactive.");
        }
        Book book = booksService.getBookByIsbn(isbn);
        if(!book.isAvailable()){
            throw new RuntimeException("Book is already issued.");
        }
        BorrowingRecord borrowingRecord =
                BorrowingRecordFactory
                        .createBorrowingRecord(
                                patronId,
                                isbn);

        borrowingRecords.add(borrowingRecord);

        book.setAvailable(false);
        LOG.info("Book issued successfully.");
    }

    @Override
    public void returnBook(String patronId, String isbn){
        LOG.info("Returning Book with ISBN : "+isbn+"and Patron ID : "+patronId);
        for(BorrowingRecord record : borrowingRecords){
            if(record.getPatronId().equals(patronId)
                    && record.getBookIsbn().equals(isbn)
                    && record.getBorrowStatus() == BorrowStatus.BORROWED){
                record.setReturnDate(LocalDate.now());
                record.setBorrowStatus(BorrowStatus.RETURNED);
                Book book = booksService.getBookByIsbn(isbn);
                book.setAvailable(true);

                LOG.info("Book returned successfully.");
            }
        }
        throw new RuntimeException("Borrowing record not found.");
    }

    @Override
    public List<BorrowingRecord> getLendingHistory(String patronId){
        List<BorrowingRecord> borrowingRecordHistory = new ArrayList<>();
        for (BorrowingRecord record : borrowingRecords){
            if(record.getPatronId().equals(patronId)){
                borrowingRecordHistory.add(record);
            }
        }
        return borrowingRecordHistory;
    }

    @Override
    public List<BorrowingRecord> getActiveLendings(){
        List<BorrowingRecord> active = new ArrayList<>();
        for (BorrowingRecord record : borrowingRecords){
            if(record.getBorrowStatus() == BorrowStatus.BORROWED){
                active.add(record);
            }
        }
        return active;
    }
}
