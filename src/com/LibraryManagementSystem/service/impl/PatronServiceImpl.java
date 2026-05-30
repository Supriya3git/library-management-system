package com.LibraryManagementSystem.service.impl;

import com.LibraryManagementSystem.entity.Patron;
import com.LibraryManagementSystem.exception.DuplicatePatronException;
import com.LibraryManagementSystem.exception.PatronNotFoundException;
import com.LibraryManagementSystem.service.PatronService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class PatronServiceImpl implements PatronService {

    private static final Logger LOG = Logger.getLogger(PatronServiceImpl.class.getName());
    Map<String, Patron> map = new HashMap<>();

    @Override
    public void addPatron(Patron patron){
        if(patron == null){
            throw new PatronNotFoundException("Patron cannot be empty.");
        }
        if(map.containsKey(patron.getPatronId())){
            throw new DuplicatePatronException("Patron already exists.");
        }
        map.put(patron.getPatronId(), patron);
        LOG.info("Patron added successfully.");
    }

    @Override
    public void updatePatron(String patronId, Patron patron){
        LOG.info("Update Patron : "+patronId);
        Patron existingPatron = map.get(patronId);
        if(existingPatron == null){
            throw new PatronNotFoundException("Patron does not exist.");
        }
        existingPatron.setFirstName(patron.getFirstName());
        existingPatron.setLastName(patron.getLastName());
        existingPatron.setEmail(patron.getEmail());
        existingPatron.setAddress(patron.getAddress());
        existingPatron.setPhoneNo(patron.getPhoneNo());
        existingPatron.setActiveMember(existingPatron.isActiveMember());

        LOG.info("Patron updated successfully.");
    }

    @Override
    public void deletePatron(String patronId){
        LOG.info("Delete Patron : "+patronId);
        if(!map.containsKey(patronId)){
            throw new PatronNotFoundException("Patron does not exist.");
        }
        map.remove(patronId);
        LOG.info("Patron deleted successfully.");
    }

    @Override
    public Patron getPatronById(String patronId){
        LOG.info("Searching Patron : "+patronId);
        Patron patron = map.get(patronId);
        if(patron == null){
            throw new PatronNotFoundException("Patron does not exist.");
        }
        return patron;
    }

    public List<Patron> getAllPatrons(){
        LOG.info("Fetching all Patrons.");
        return new ArrayList<>(map.values());
    }
}
