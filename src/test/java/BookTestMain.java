
// import Books.BookInventoryImpl;
// import Books.BookSearchInventoryImpl;
// import Books.BorrowBookInventoryImpl;
// import Books.Inventories.BookInventory;
// import Books.Inventories.BookSearchInventory;
// import Books.Inventories.BorrowBook;
// import Patrons.PatronTransactionInventoryImpl;
// import Patrons.PatronInventoryImpl;
// import Patrons.PatronsInventory.PatronTransactionInventory;
// import Patrons.PatronsHistoryInventoryImpl;
// import Patrons.PatronsInventory.PatronsHistoryInventory;
// import Patrons.PatronsInventory.PatronsInventory;
// import java.util.logging.*;

// public class BookTestMain {

//     private static final Logger log = Logger.getLogger("BookTestMain");

//     public static void main(String[] args) {
//         BookInventory booksInventory = new BookInventoryImpl();
//         PatronsInventory patronsInventory = new PatronInventoryImpl();
//         BookSearchInventory bookSearchInventory = new BookSearchInventoryImpl(booksInventory, patronsInventory);
//         PatronTransactionInventory patronTransactionInventory = new PatronTransactionInventoryImpl(patronsInventory, bookSearchInventory);
//         BorrowBook borrowBook = new BorrowBookInventoryImpl(patronsInventory, bookSearchInventory);

//         addBooks(booksInventory);
//         searchBooks(bookSearchInventory);
//         managePatrons(patronsInventory, patronTransactionInventory, borrowBook);
//     }

//     private static void addBooks(BookInventory booksInventory) {
//         booksInventory.addBook("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", 1925);
//         booksInventory.addBook("To Kill a Mockingbird", "Harper Lee", "9780061120084", 1960);
//         booksInventory.addBook("1984", "George Orwell", "9780451524935", 1949);
//     }

//     private static void searchBooks(BookSearchInventory bookSearchInventory) {
//         bookSearchInventory.searchByTitle("The Great Gatsby");
//         bookSearchInventory.searchByIsbn("9780743273565");
//         bookSearchInventory.searchByAuthor("F. Scott Fitzgerald");
//         bookSearchInventory.getBookNameFromIsbn("9780743273565");
//     }

//     private static void managePatrons(PatronsInventory patronsInventory, PatronTransactionInventory patronTransactionInventory, BorrowBook borrowBook) {
//         patronsInventory.addPatron("John Doe", "123-456-7890");
//         patronTransactionInventory.issueBook("John Doe", "9780743273565");
//         log.info(patronsInventory.getPatronsPhoneNumber("John Doe"));
//         borrowBook.bookStatus("The Great Gatsby");

//         PatronsHistoryInventory patronsHistoryInventory = new PatronsHistoryInventoryImpl(patronsInventory);
//         patronsHistoryInventory.getHistoryOfPatron("John Doe");
//     }
// }