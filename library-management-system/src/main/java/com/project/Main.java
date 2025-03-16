package com.project;
import java.util.List;
import java.util.Scanner;
import com.project.classes.*;
import com.project.dao.*;
import com.project.utils.*;

public class Main {
    public static void main(String[] args) {
        BookDAO bookDAO = new BookDAOImpl();
        MemberDAO memberDAO = new MemberDAOImpl();
        BorrowingDAO borrowingDAO = new BorrowingDAOImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display the menu
            System.out.println("\n Welcome to your Library Management System");
            System.out.println("1. Add a Book");
            System.out.println("2. Add a Member");
            System.out.println("3. Borrow a Book");
            System.out.println("4. Return a Book");
            System.out.println("5. Display All Books");
            System.out.println("6. Display All Members");
            System.out.println("7. Display All Borrowing Records");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Add a Book
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Enter available copies: ");
                    int availableCopies = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    Book book = new Book(0, title, author, genre, availableCopies);

                    //EXPORT BOOK TO CSV
                    CSVExporter.exportBooksToCSV(book);

                    //ADD BOOK TO DATABASE
                    bookDAO.addBook(book);

                    //LOG BOOK ADDED
                    Logger.log("Book added: " + book.getBookTitle());

                    System.out.println("Book added successfully!");
                    break;

                case 2:
                    // Add a Member
                    System.out.print("Enter member name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter member email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter member phone: ");
                    String phone = scanner.nextLine();

                    Member member = new Member(0, name, email, phone);

                    //EXPORT MEMBER TO CSV
                    CSVExporter.exportMembersToCSV(member);

                    //ADD MEMBER TO DATABASE
                    memberDAO.addMember(member);

                    //LOG MEMBER ADDED
                    Logger.log("Member added: " + member.getMemberName());

              
                    System.out.println("Member added successfully!");
                    break;

                case 3:
                    // Borrow a Book
                    System.out.print("Enter book ID to borrow: ");
                    int bookId = scanner.nextInt();
                    System.out.print("Enter member ID: ");
                    int memberId = scanner.nextInt();
                    scanner.nextLine(); 

                    borrowingDAO.borrowBook(bookId, memberId);

                    //LOG BOOK BORROWED
                    Logger.log("Book borrowed: Book ID " + bookId + " by Member ID " + memberId);
                    
                    System.out.println("Book borrowed successfully!");
                    break;

                case 4:
                    // Return a Book
                    System.out.print("Enter borrowing record ID to return: ");
                    int recordId = scanner.nextInt();
                    scanner.nextLine(); 

                    borrowingDAO.returnBook(recordId);


                    //LOG BOOK RETURNED
                    Logger.log("Book returned: Record ID " + recordId);

                    System.out.println("Book returned successfully!");
                    break;

                case 5:
                    // Display All Books
                    System.out.println("\nAll Books:");
                    bookDAO.getAllBooks().forEach(b -> System.out.println(
                        "ID: " + b.getBookId() + ", Title: " + b.getBookTitle() +
                        ", Author: " + b.getBookAuthor() + ", Genre: " + b.getBookGenre() +
                        ", Available Copies: " + b.getAvailableCopies()
                    ));
                    break;

                case 6:
                    // Display All Members
                    System.out.println("\nAll Members:");
                    memberDAO.getAllMembers().forEach(m -> System.out.println(
                        "ID: " + m.getMemberId() + ", Name: " + m.getMemberName() +
                        ", Email: " + m.getMemberEmail() + ", Phone: " + m.getMemberPhone()
                    ));
                    break;

                case 7:
                    // Display All Borrowing Records
                    System.out.println("\nAll Borrowing Records:");
                    List<Borrowing> records = borrowingDAO.getAllBorrowingRecords();
                    for (Borrowing record : records) {
                        System.out.println(
                            "Record ID: " + record.getRecordId() + ", Book ID: " + record.getBookId() +
                            ", Member ID: " + record.getMemberId() + ", Borrow Date: " + record.getBorrowDate() +
                            ", Return Date: " + record.getReturnDate()
                        );
                    }
                    break;

                case 8:
                    // Exit the program
                    System.out.println("Exiting the Library Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}