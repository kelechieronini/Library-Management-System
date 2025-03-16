# Library Management System

![Java](https://img.shields.io/badge/Java-17-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-green)
![Maven](https://img.shields.io/badge/Maven-3.8.1-orange)

The **Library Management System** is a Java-based application designed to manage books, members, and borrowing records in a library. It uses **JDBC** to interact with a **PostgreSQL** database, implements the **DAO pattern** for database operations, and includes features like logging and CSV export.

---

## Features

- **Book Management**:
  - Add, update, delete, and search for books.
  - Display all books in the library.
- **Member Management**:
  - Add, update, delete, and display members.
- **Borrowing Management**:
  - Allow members to borrow and return books.
  - Track borrowing records.
- **Logging**:
  - Log system activities (e.g., book borrowing, returning) to a text file (`library_log.txt`).
- **CSV Export**:
  - Export book and member details to CSV files (`books.csv`, `members.csv`).

---

## Technologies Used

- **Java 17**: Core programming language.
- **PostgreSQL 15**: Database for storing books, members, and borrowing records.
- **JDBC**: Java Database Connectivity for interacting with the database.
- **Maven**: Build automation and dependency management.
- **Apache Commons CSV**: Library for exporting data to CSV files.

---

## Prerequisites

Before running the project, ensure you have the following installed:

1. **Java Development Kit (JDK) 17** or higher.
2. **PostgreSQL 15** or higher.
3. **Maven 3.8.1** or higher.
4. **pgAdmin4** (optional, for managing the PostgreSQL database).

---

## Setup Instructions

### 1. Database Setup

1. Open **pgAdmin4** and connect to your PostgreSQL server.
2. Create a new database named `library_db`.
3. Run the following SQL script to create the required tables:

   ```sql
   CREATE TABLE books (
       book_id SERIAL PRIMARY KEY,
       title VARCHAR(255) NOT NULL,
       author VARCHAR(255) NOT NULL,
       genre VARCHAR(100),
       available_copies INT NOT NULL
   );

   CREATE TABLE members (
       member_id SERIAL PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       email VARCHAR(255) NOT NULL,
       phone VARCHAR(20)
   );

   CREATE TABLE borrowing_records (
       record_id SERIAL PRIMARY KEY,
       book_id INT NOT NULL,
       member_id INT NOT NULL,
       borrow_date DATE NOT NULL,
       return_date DATE,
       FOREIGN KEY (book_id) REFERENCES books(book_id),
       FOREIGN KEY (member_id) REFERENCES members(member_id)
   );
   ```

4. Insert sample data (optional):

   ```sql
   INSERT INTO books (title, author, genre, available_copies) VALUES
   ('The Great Gatsby', 'F. Scott Fitzgerald', 'Fiction', 5),
   ('1984', 'George Orwell', 'Dystopian', 3);

   INSERT INTO members (name, email, phone) VALUES
   ('John Doe', 'john.doe@example.com', '123-456-7890'),
   ('Jane Smith', 'jane.smith@example.com', '987-654-3210');

   INSERT INTO borrowing_records (book_id, member_id, borrow_date) VALUES
   (1, 1, '2023-10-01'),
   (2, 2, '2023-10-02');
   ```

---

### 2. Project Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/library-management-system.git
   cd library-management-system
   ```

2. Update the database connection details in `src/main/java/com/yourcompany/library/util/DatabaseConnection.java`:

   ```java
   private static final String URL = "jdbc:postgresql://localhost:5432/library_db";
   private static final String USER = "your_username"; // Replace with your PostgreSQL username
   private static final String PASSWORD = "your_password"; // Replace with your PostgreSQL password
   ```

3. Build the project using Maven:

   ```bash
   mvn clean install
   ```

4. Run the application:

   ```bash
   mvn exec:java -Dexec.mainClass="com.yourcompany.library.Main"
   ```

---

## Usage

### Book Management

- **Add a Book**:
  ```java
  Book book = new Book(0, "The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 5);
  bookDAO.addBook(book);
  ```

- **Update a Book**:
  ```java
  Book book = bookDAO.getBookById(1);
  book.setAvailableCopies(4);
  bookDAO.updateBook(book);
  ```

- **Delete a Book**:
  ```java
  bookDAO.deleteBook(1);
  ```

- **Search for Books**:
  ```java
  List<Book> books = bookDAO.getAllBooks();
  books.forEach(b -> System.out.println(b.getTitle()));
  ```

### Member Management

- **Add a Member**:
  ```java
  Member member = new Member(0, "John Doe", "john.doe@example.com", "123-456-7890");
  memberDAO.addMember(member);
  ```

- **Update a Member**:
  ```java
  Member member = memberDAO.getMemberById(1);
  member.setPhone("987-654-3210");
  memberDAO.updateMember(member);
  ```

- **Delete a Member**:
  ```java
  memberDAO.deleteMember(1);
  ```

### Borrowing Management

- **Borrow a Book**:
  ```java
  borrowingDAO.borrowBook(1, 1); // Book ID 1, Member ID 1
  ```

- **Return a Book**:
  ```java
  borrowingDAO.returnBook(1); // Record ID 1
  ```

### Logging and CSV Export

- **Log Activities**:
  ```java
  Logger.log("Book borrowed: Book ID 1 by Member ID 1");
  ```

- **Export Books to CSV**:
  ```java
  CSVExporter.exportBooksToCSV(bookDAO.getAllBooks(), "books.csv");
  ```

---

## Project Structure

```
library-management-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── project/
│   │   │               ├── dao/
│   │   │               ├── classes/
│   │   │               ├── service/
│   │   │               ├── utils/
│   │   │               └── Main.java
│   │   └── resources/
│   │       ├── library_log.txt
│   │       ├── books.csv
│   │       ├── members.csv
│   │       └── db.properties
│   └── test/
│       └── java/
├── pom.xml
└── README.md
```


## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements.

---

Enjoy managing your library with this system! 📚
