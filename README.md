# Library Management System

## Overview

This project is a Java-based Library Management System designed using Object-Oriented Programming principles. It provides functionality for managing books, patrons, lending operations, borrowing history, and inventory tracking.

The application uses in-memory collections (`HashMap` and `List`) for data storage and focuses on clean design and separation of responsibilities.

---

## Features

### Book Management

* Add books to the catalog
* Update book details
* Search books by ISBN
* Remove books from the catalog
* View all books

### Patron Management

* Register new patrons
* Update patron information
* View patron details
* Remove patrons from the system

### Lending Management

* Check out books to patrons
* Return borrowed books
* Validate patron eligibility
* Maintain lending records

### Borrowing History

* Track borrowing activity
* Store borrow and return dates
* View borrowing history for a patron

### Inventory Management

* Track available books
* Track borrowed books
* View inventory statistics

---

## Project Structure

### Entities

* Book
* Patron
* BorrowingRecord
* BorrowStatus

### Services

* BookService
* PatronService
* BorrowingService
* InventoryService

### Implementations

* BookServiceImpl
* PatronServiceImpl
* BorrowingServiceImpl
* InventoryServiceImpl

---

## Class Diagram

```text
                    +----------------+
                    |      Book      |
                    +----------------+
                    | isbn           |
                    | title          |
                    | author         |
                    | available      |
                    +----------------+
                            ^
                            |
                            |
                    +------------------+
                    | BorrowingRecord  |
                    +------------------+
                    | recordId         |
                    | patronId         |
                    | bookIsbn         |
                    | borrowDate       |
                    | returnDate       |
                    | status           |
                    +------------------+
                            |
                            |
                            v

                    +----------------+
                    |    Patron      |
                    +----------------+
                    | patronId       |
                    | firstName      |
                    | lastName       |
                    | activeMember   |
                    +----------------+


+-------------------+      +--------------------+
|   BookService     |      |  PatronService     |
+-------------------+      +--------------------+

            \                  /
             \                /
              \              /
               \            /
                \          /
             +--------------------+
             | BorrowingService   |
             +--------------------+
                      |
                      |
                      v
             +--------------------+
             | InventoryService   |
             +--------------------+
```

---

## Design Decisions

* ISBN is treated as the unique identifier for books.
* Patron ID uniquely identifies a library member.
* Only active patrons can borrow books.
* A book can only be borrowed when it is available.
* Book availability is updated automatically during issue and return operations.
* Inventory information is derived from the current status of books.

---

## Design Patterns Used

### Factory Pattern
The Factory Pattern is used to create `BorrowingRecord` objects. This centralizes object creation logic and keeps the borrowing service focused on business operations rather than object initialization.

### Singleton Pattern
The Singleton Pattern is used in `BookServiceImpl` to ensure a single instance manages the book catalog throughout the application. This provides a single source of truth for book management within the system.

---


## Assumptions

* Data is stored in memory and is not persisted.
* Each book has a unique ISBN.
* Each patron has a unique Patron ID.
* No fine calculation or reservation functionality is included in the base implementation.

---

## Future Enhancements

* Reservation system
* Multi-branch support
* Recommendation engine
* Fine calculation for overdue books
* Database integration

---

## Technologies Used

* Java
* Collections Framework
* Object-Oriented Programming (OOP)
* IntelliJ IDEA

```
```
