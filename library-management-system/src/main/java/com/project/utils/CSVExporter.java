package com.project.utils;
import com.project.classes.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException; 

public class CSVExporter {
    private static final String BOOKS_CSV = "library-management-system\\src\\main\\resources\\books.csv";
    private static final String MEMBERS_CSV = "library-management-system\\src\\main\\resources\\members.csv";


    // Export books to CSV
    public static void exportBooksToCSV(Book book) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKS_CSV, true))) {
            writer.write("Book ID, Title, Author, Genre, Available Copies");
            writer.newLine();
            
                writer.write(
                    book.getBookId() + ", " +
                    book.getBookTitle() + ", " +
                    book.getBookAuthor() + ", " +
                    book.getBookGenre() + ", " +
                    book.getAvailableCopies()
                );
                writer.newLine();
           
        } catch (IOException e) {
            e.printStackTrace();
            Logger.log("Error: " + e.getMessage());
        }
    }

    // Export members to CSV
    public static void exportMembersToCSV(Member member) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MEMBERS_CSV, true))) {
            writer.write("Member ID, Name, Email, Phone");
            writer.newLine();
                writer.write(
                    member.getMemberId() + ", " +
                    member.getMemberName() + ", " +
                    member.getMemberEmail() + ", " +
                    member.getMemberPhone()
                );
                writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            Logger.log("Error: " + e.getMessage());
        }
    }
}