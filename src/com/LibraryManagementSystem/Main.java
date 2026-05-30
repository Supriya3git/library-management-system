package com.LibraryManagementSystem;

import com.LibraryManagementSystem.entity.Book;
import com.LibraryManagementSystem.entity.BorrowingRecord;
import com.LibraryManagementSystem.entity.Patron;
import com.LibraryManagementSystem.service.BooksService;
import com.LibraryManagementSystem.service.InventoryService;
import com.LibraryManagementSystem.service.LendingService;
import com.LibraryManagementSystem.service.PatronService;
import com.LibraryManagementSystem.service.impl.BooksServiceImpl;
import com.LibraryManagementSystem.service.impl.InventoryServiceImpl;
import com.LibraryManagementSystem.service.impl.LendingServiceImpl;
import com.LibraryManagementSystem.service.impl.PatronServiceImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            // Singleton BookService
            BooksService bookService = BooksServiceImpl.getInstance();

            // Normal PatronService
            PatronService patronService = new PatronServiceImpl();

            // BorrowingService uses Factory internally for BorrowingRecord creation
            LendingService lendingService =
                    new LendingServiceImpl(bookService, patronService);

            // InventoryService reads book availability
            InventoryService inventoryService =
                    new InventoryServiceImpl(bookService);

            // ----------------------------
            // 1. Add Books
            // ----------------------------
            Book book1 = new Book(
                    "ISBN001",
                    "Java Basics",
                    "James Gosling",
                    2024,
                    true
            );

            Book book2 = new Book(
                    "ISBN002",
                    "Spring Boot Guide",
                    "Rod Johnson",
                    2023,
                    true
            );

            bookService.addBook(book1);
            bookService.addBook(book2);

            // ----------------------------
            // 2. Update Book
            // ----------------------------
            Book updatedBook1 = new Book(
                    "ISBN001",
                    "Advanced Java",
                    "James Gosling",
                    2025,
                    true
            );

            bookService.updateBook("ISBN001", updatedBook1);

            // ----------------------------
            // 3. Add Patrons
            // ----------------------------
            Patron patron1 = new Patron(
                    "P001",
                    "John",
                    "Doe",
                    "john@gmail.com",
                    "9999999999",
                    "Delhi",
                    true
            );

            Patron patron2 = new Patron(
                    "P002",
                    "Jane",
                    "Smith",
                    "jane@gmail.com",
                    "8888888888",
                    "Mumbai",
                    true
            );

            patronService.addPatron(patron1);
            patronService.addPatron(patron2);

            // ----------------------------
            // 4. Display All Books and Patrons
            // ----------------------------
            System.out.println("=== All Books ===");
            List<Book> allBooks = bookService.getAllBooks();
            for (Book book : allBooks) {
                System.out.println(book);
            }

            System.out.println("\n=== All Patrons ===");
            List<Patron> allPatrons = patronService.getAllPatrons();
            for (Patron patron : allPatrons) {
                System.out.println(patron);
            }

            // ----------------------------
            // 5. Issue Book
            // ----------------------------
            System.out.println("\nIssuing ISBN001 to P001...");
            lendingService.checkoutBook("P001", "ISBN001");

            // ----------------------------
            // 6. Borrowing History
            // ----------------------------
            System.out.println("\n=== Borrowing History for P001 ===");
            List<BorrowingRecord> history = lendingService.getLendingHistory("P001");
            for (BorrowingRecord record : history) {
                System.out.println(record);
            }

            // ----------------------------
            // 7. Inventory after issue
            // ----------------------------
            System.out.println("\n=== Available Books ===");
            for (Book book : inventoryService.getAvailableBooks()) {
                System.out.println(book);
            }

            System.out.println("\n=== Borrowed Books ===");
            for (Book book : inventoryService.getBorrowedBooks()) {
                System.out.println(book);
            }

            System.out.println("\nAvailable Book Count: " + inventoryService.getAvailableBooksCount());
            System.out.println("Borrowed Book Count: " + inventoryService.getBorrowedBooksCount());
            System.out.println("Total Book Count: " + inventoryService.getTotalBooksCount());

            // ----------------------------
            // 8. Return Book
            // ----------------------------
            System.out.println("\nReturning ISBN001 from P001...");
            lendingService.returnBook("P001", "ISBN001");

            // ----------------------------
            // 9. Inventory after return
            // ----------------------------
            System.out.println("\n=== Available Books After Return ===");
            for (Book book : inventoryService.getAvailableBooks()) {
                System.out.println(book);
            }

            System.out.println("\n=== Borrowed Books After Return ===");
            for (Book book : inventoryService.getBorrowedBooks()) {
                System.out.println(book);
            }

            // ----------------------------
            // 10. Update Patron
            // ----------------------------
            Patron updatedPatron1 = new Patron(
                    "P001",
                    "John",
                    "Doe",
                    "john.new@gmail.com",
                    "7777777777",
                    "Noida",
                    true
            );

            patronService.updatePatron("P001", updatedPatron1);

            System.out.println("\n=== Updated Patron P001 ===");
            System.out.println(patronService.getPatronById("P001"));

            // ----------------------------
            // 11. Delete Book / Patron (optional demo)
            // ----------------------------
            bookService.deleteBook("ISBN002");
            patronService.deletePatron("P002");

            System.out.println("\n=== Final Books ===");
            for (Book book : bookService.getAllBooks()) {
                System.out.println(book);
            }

            System.out.println("\n=== Final Patrons ===");
            for (Patron patron : patronService.getAllPatrons()) {
                System.out.println(patron);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
