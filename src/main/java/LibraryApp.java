import Books.BookInventoryImpl;
import Books.BookSearchInventoryImpl;
import Books.BorrowBookInventoryImpl;
import Books.Inventories.BookInventory;
import Books.Inventories.BookSearchInventory;
import Books.Inventories.BorrowBook;
import Patrons.PatronTransactionInventoryImpl;
import Patrons.PatronInventoryImpl;
import Patrons.PatronsInventory.PatronTransactionInventory;
import Patrons.PatronsHistoryInventoryImpl;
import Patrons.PatronsInventory.PatronsHistoryInventory;
import Patrons.PatronsInventory.PatronsInventory;

import java.util.Scanner;
import java.util.logging.*;

public class LibraryApp {

    private static final Logger log = Logger.getLogger("LibraryApp");

    public static void main(String[] args) {
        BookInventory booksInventory = new BookInventoryImpl();
        PatronsInventory patronsInventory = new PatronInventoryImpl();
        BookSearchInventory bookSearchInventory = new BookSearchInventoryImpl(booksInventory, patronsInventory);
        PatronTransactionInventory patronTransactionInventory = new PatronTransactionInventoryImpl(patronsInventory, bookSearchInventory);
        BorrowBook borrowBook = new BorrowBookInventoryImpl(patronsInventory, bookSearchInventory,booksInventory);

        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Choose an option Number:");
            System.out.println("1. Manage Books");
            System.out.println("2. Search Books");
            System.out.println("3. Manage Patrons");
            System.out.println("4. Exit");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addBooks(booksInventory,borrowBook, sc);
                    break;
                case 2:
                    searchBooks(bookSearchInventory ,sc);
                    break;
                case 3:
                    managePatrons(patronsInventory, patronTransactionInventory, borrowBook, sc);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        sc.close();
    }



    private static void addBooks(BookInventory booksInventory,BorrowBook borrowBook, Scanner sc) {
        boolean exit = false;

        while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1. Add a new book");
            System.out.println("2. Remove a Book");
            System.out.println("3. Show Books");
            System.out.println("4. Know the Status of the book");
            System.out.println("5. Exit");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    log.info("Enter Title of Book: ");
                    String title = sc.nextLine();
                    System.out.println("Enter the Author of the Book");
                    String author = sc.nextLine();
                    System.out.println("Enter the ISBN of the Book");
                    String isbn = sc.nextLine();
                    System.out.println("Enter the Publication year of the Book");
                    Integer publicationYear = sc.nextInt();
                    sc.nextLine(); // consume newline
                    if(!booksInventory.displayBooks().contains(title)){
                        booksInventory.addBook(title, author, isbn, publicationYear);
                    }else{
                        log.warning("Book Title Already Exists In the Inventory, Cant Add the Book Again!");
                    }
                    System.out.println("Entry Completed");    
                    break;
                case 2:
                    log.info("Enter The Book isbn You want to Remove: ");
                    isbn = sc.nextLine();
                    booksInventory.removeBook(isbn);
                    break;
                case 3:
                    System.out.println(booksInventory.displayBooks());
                    break;
                case 4:
                    System.out.println("Enter the title of the book to check status: ");
                    String bookTitle = sc.nextLine();
                    borrowBook.bookStatus(bookTitle);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }



    private static void searchBooks(BookSearchInventory bookSearchInventory, Scanner sc) {
        boolean exit = false;

        while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1. Search by title");
            System.out.println("2. Search by ISBN");
            System.out.println("3. Search by author");
            System.out.println("4. Which Patron has the book");
            System.out.println("5. Exit");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter the title of the book to search: ");
                    String title = sc.nextLine();
                    bookSearchInventory.searchByTitle(title);
                    break;
                case 2:
                    System.out.println("Enter the ISBN of the book to search: ");
                    String isbn = sc.nextLine();
                    bookSearchInventory.searchByIsbn(isbn);
                    break;
                case 3:
                    System.out.println("Enter the author of the book to search: ");
                    String author = sc.nextLine();
                    bookSearchInventory.searchByAuthor(author);
                    break;
                case 4:
                    System.out.println("Enter the Books name whose Patron you want to find out: ");
                    String booksName = sc.nextLine();
                    bookSearchInventory.getPatronNameByBook(booksName);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }




    private static void managePatrons(PatronsInventory patronsInventory, PatronTransactionInventory patronTransactionInventory, BorrowBook borrowBook, Scanner sc) {
        boolean exit = false;

        while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1. Register a new patron");
            System.out.println("2. Remove patron");
            System.out.println("3. Search Patron by Name");
            System.out.println("4. Search Patron's Phone Number");
            System.out.println("5. Update Patron Info");
            System.out.println("6. Issue a book");
            System.out.println("7. Return Book");
            // to be reomved  7
            // also add the method which patron has the book in the book one method is : public void searchByBook(String bookName) in patronsinventoryImpl
            System.out.println("8. Check book status");
            System.out.println("9. Get patron history");
            System.out.println("10. Display All Patrons");
            System.out.println("11. Exit");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter the name of the patron: ");
                    String patronName = sc.nextLine();
                    System.out.println("Enter the phone number of the patron: ");
                    String phoneNumber = sc.nextLine();
                    patronsInventory.addPatron(patronName, phoneNumber);
                    break;
                case 2:
                System.out.println("Enter the name of Patron to be removed:");
                patronName = sc.nextLine();
                patronsInventory.removePatron(patronName);
                    break;
                case 3:
                System.out.println("Enter the name to see if Registered:");
                patronName = sc.nextLine();
                patronsInventory.searchByName(patronName);
                    break;
                case 4:
                System.out.println("Enter Patrons name :");
                patronName = sc.nextLine();
                log.info(patronsInventory.getPatronsPhoneNumber(patronName));
                    break;
                case 5:
                System.out.println("Enter Patrons Name to Update");
                patronName = sc.nextLine();
                patronsInventory.updatePatron(patronName);
                    break;
                case 6:
                    System.out.println("Enter the name of the patron to issue a book: ");
                    String patronNameForIssue = sc.nextLine();
                    System.out.println("Enter the ISBN of the book to issue: ");
                    String isbnForIssue = sc.nextLine();
                    // if(patronsInventory.)
                    patronTransactionInventory.issueBook(patronNameForIssue, isbnForIssue);
                    // log.info(patronsInventory.getPatronsPhoneNumber(patronNameForIssue));
                    break;
                case 7:
                    System.out.println("Enter the patron name to return the book:");
                    String patronNameForReturn = sc.nextLine();
                    System.out.println("Enter the name of the book to return: ");
                    String nameOfBookForReturn = sc.nextLine();
                    patronTransactionInventory.returnBook(patronNameForReturn, nameOfBookForReturn);
                    break;
                case 8:
                    System.out.println("Enter the title of the book to check status: ");
                    String bookTitle = sc.nextLine();
                    borrowBook.bookStatus(bookTitle);
                    break;
                case 9:
                    PatronsHistoryInventory patronsHistoryInventory = new PatronsHistoryInventoryImpl(patronsInventory);
                    System.out.println("Enter the name of the patron to get history: ");
                    String patronNameForHistory = sc.nextLine();
                    patronsHistoryInventory.getHistoryOfPatron(patronNameForHistory);
                    break;
                case 10:
                    patronsInventory.displayPatrons();
                    break;
                case 11:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}