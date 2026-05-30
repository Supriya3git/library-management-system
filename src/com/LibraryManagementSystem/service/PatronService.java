package com.LibraryManagementSystem.service;

import com.LibraryManagementSystem.entity.Patron;

import java.util.List;

public interface PatronService {
    public void addPatron(Patron patron);
    public void updatePatron(String patronId, Patron patron);
    public void deletePatron(String patronId);
    public Patron getPatronById(String patronId);
    public List<Patron> getAllPatrons();
}
