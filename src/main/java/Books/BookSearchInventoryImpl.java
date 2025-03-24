package Books;
import java.util.List;
// import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import Books.Inventories.BookSearchInventory;
import Books.Inventories.BookInventory;
import Patrons.PatronsInventory.PatronsInventory;

public class BookSearchInventoryImpl implements BookSearchInventory{

    private static final Logger log = Logger.getLogger("BookSearchInventoryImpl");

    private BookInventory booksInventory;

    private PatronsInventory patronsInventory;

    public BookSearchInventoryImpl(BookInventory booksInventory, PatronsInventory patronsInventory) {
        this.booksInventory = booksInventory;
        this.patronsInventory = patronsInventory;
    }

    public BookSearchInventoryImpl(){}
   
    public void searchByTitle(String title) {
        Map<String, Book> booksMap = booksInventory.getBooksMap();
        // log.info("BooksSearchInventory map :"+booksMap);
        Boolean found = false;
        for (Book book : booksMap.values()) {
            if (book.getTitle().equals(title)) {
                log.info("\033[1m" + book.title + " found\033[0m");
                found = true;
                break;
            }
        }
        if (!found) {
            log.info("\033[1mBook not found\033[0m");
        }
    }

    public void searchByAuthor(String author) {
        Map<String,Book> booksMap = booksInventory.getBooksMap();
        Boolean found = false;
        for (Book book : booksMap.values()) {
            if (book.getAuthor().equals(author)) {
                log.info("\033[1m" + book.author + " found\033[0m");
                found = true;
                break;
            }
        }
        if (!found) {
            log.info("\033[1mBook not found\033[0m");
        }
    
    }

    public String searchByIsbn(String isbn) {
        Map<String,Book> booksMap = booksInventory.getBooksMap();
        Boolean found = false;
        String bookName = null;
        for (Book book : booksMap.values()) {
            if (book.getIsbn().equals(isbn)) {
                bookName = book.getTitle();
                log.info("\033[1m" + book.getIsbn() + " found\033[0m");
                // System.out.println(bookName);
                found = true;
                break;
                
            }
        }
        if (!found) {
            log.info("\033[1mBook not found\033[0m");
        }
        return bookName;
        
    }

    // public String getBookNameFromIsbn(String isbn) {
    //     Map<String,Book> booksMap = booksInventory.getBooksMap();
    //     String bookName = null;
    //     for (Book book : booksMap.values()) {
    //         log.info(book.toString());
    //         if (book.getIsbn().equals(isbn)) {
    //            bookName = book.getTitle();
    //            log.info("\033[1m" + bookName + "\033[0m");
    //             break;
    //         }
    //     }
    //     return bookName;
    // }

    public String getPatronNameByBook(String bookName) {
        String patronName = null;
        for (Entry<String, List<String>> entry : patronsInventory.getPatronsIssuedBooksMap().entrySet()) {
            // System.out.println(patronsInventory.getPatronsIssuedBooksMap());
            // log.info("Checking patron: " + entry.getKey());
           
            if (!entry.getValue().contains(bookName)) {
                log.info("Book Does not Exist or Not borrowed by anyone");
                return null;
            }
            log.info("Book found with patron: " + entry.getKey());
            patronName = entry.getKey();
            // return entry.getKey();
        }
        // log.info("Book not found");
        return patronName;// Return null if the book is not found
    }

}
