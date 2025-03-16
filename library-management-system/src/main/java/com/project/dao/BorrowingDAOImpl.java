package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.classes.Borrowing;
import com.project.utils.DatabaseConnection;

public class BorrowingDAOImpl implements BorrowingDAO {

    @Override
    public void borrowBook(int bookId, int memberId) {
        String query = "INSERT into borrowing_records (member_id, book_id, borrow_date) VALUES (?, ?, CURRENT_DATE)";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);) {
            stmt.setInt(1, memberId);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void returnBook(int recordId) {
        String query = "UPDATE borrowing_records SET return_date = CURRENT_DATE WHERE record_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, recordId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public List<Borrowing> getAllBorrowingRecords() {
        List<Borrowing> records = new ArrayList<>();
        String query = "SELECT * FROM borrowing_records";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Borrowing record = new Borrowing(
                    rs.getInt("record_id"),
                    rs.getInt("book_id"),
                    rs.getInt("member_id"),
                    rs.getDate("borrow_date"),
                    rs.getDate("return_date")
                );
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

}
