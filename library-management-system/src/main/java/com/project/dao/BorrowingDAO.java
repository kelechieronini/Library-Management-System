package com.project.dao;

import java.util.List;

import com.project.classes.Borrowing;

public interface BorrowingDAO {
    void borrowBook(int bookId, int memberId);

    void returnBook(int recordId);

    List<Borrowing> getAllBorrowingRecords();
}
