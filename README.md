<<<<<<< HEAD
# LibraryManagementSystem
Airtribe 1st Assignment 
=======
# Library Management System

## Overview
The Library Management System is designed to manage the operations of a library, including adding books, borrowing books, returning books, searching books, managing patrons, and calculating fines.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) installed
- An IDE or text editor for Java development

### Running the Application
1. Clone the repository to your local machine.
2. Open the project in your preferred IDE.
3. Locate the `LibraryApp.java` file.
4. Run the `LibraryApp` class to start the application.

## Features

### Adding Books
To add a book to the library, use the `addBooks` method:
```java
library.addBooks(booksInventory, borrowBook, sc);
```

### Borrowing Books
To borrow a book, use the `issueBook` method:
```java
patronTransactionInventory.issueBook(patronName, isbn);
```

### Returning Books
To return a borrowed book, use the `returnBook` method:
```java
patronTransactionInventory.returnBook(patronName, bookName);
```

### Searching Books
To search for books, use the `searchBooks` method:
```java
library.searchBooks(bookSearchInventory, sc);
```

### Managing Patrons
To manage patrons, use the `managePatrons` method:
```java
library.managePatrons(patronsInventory, patronTransactionInventory, borrowBook, sc);
```

### Calculating Fines
To calculate fines for overdue books, use the `calculateFine` method:
```java
library.calculateFine(patronName, overdueDays);
```



## Running the Project
To run the project, follow these steps:
1. Ensure you have the Java Development Kit (JDK) installed.
2. Open the project in your preferred IDE.
3. Locate the `LibraryApp.java` file.
4. Run the `LibraryApp` class to start the application.



## Required Inputs
When running the application, you will need to provide the following inputs:
- Book details (title, author, ISBN, publication year) for adding books.
- Patron details (name, phone number) for managing patrons.
- ISBN and patron name for borrowing and returning books.
- Search criteria (title, author, ISBN) for searching books.
- Patron name and overdue days for calculating fines.




## Classes and Methods

### LibraryApp.java
The main class to run the application.
```java
public class LibraryApp {
    public static void main(String[] args) {
        // ...existing code...
    }
}
```

### BookInventory.java
Handles the core functionality of managing books.
```java
public interface BookInventory {
    // ...existing code...
    void addBook(String title, String author, String isbn, int publicationYear);
    void removeBook(String isbn);
    List<String> displayBooks();
}
```

### BookSearchInventory.java
Handles the functionality of searching books.
```java
public interface BookSearchInventory {
    // ...existing code...
    void searchByTitle(String title);
    void searchByIsbn(String isbn);
    void searchByAuthor(String author);
    void getPatronNameByBook(String bookName);
}
```

### BorrowBook.java
Handles the functionality of borrowing books.
```java
public interface BorrowBook {
    // ...existing code...
    void bookStatus(String bookTitle);
}
```

### PatronsInventory.java
Handles the core functionality of managing patrons.
```java
public interface PatronsInventory {
    // ...existing code...
    void addPatron(String name, String phoneNumber);
    void removePatron(String name);
    void searchByName(String name);
    String getPatronsPhoneNumber(String name);
    void updatePatron(String name);
    void displayPatrons();
}
```

### PatronTransactionInventory.java
Handles the functionality of patron transactions.
```java
public interface PatronTransactionInventory {
    // ...existing code...
    void issueBook(String patronName, String isbn);
    void returnBook(String patronName, String bookName);
}
```

### PatronsHistoryInventory.java
Handles the functionality of managing patron history.
```java
public interface PatronsHistoryInventory {
    // ...existing code...
    void getHistoryOfPatron(String patronName);
}
```

## Conclusion
This documentation provides a brief overview of how to use the Library Management System. For more detailed information, refer to the source code and comments within each class.
>>>>>>> 83e70d1 (Initial Commit Library Management System)
