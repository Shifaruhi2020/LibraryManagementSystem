package Books;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import Books.Inventories.BookInventory;
import Books.Inventories.BookSearchInventory;
import Books.Inventories.BorrowBook;


import Patrons.PatronsInventory.PatronsInventory;


public class BorrowBookInventoryImpl implements BorrowBook{

    private static final Logger log = Logger.getLogger("BorrowBookInventoryImpl");

    private Map<String, String> bookStatus = new HashMap<>();


    private PatronsInventory patronInventory;

    private BookSearchInventory bookSearchInventory;

    private BookInventory bookInventory;

    public BorrowBookInventoryImpl(PatronsInventory patronInventory, BookSearchInventory bookSearchInventory, BookInventory bookInventory) {
        this.bookSearchInventory = bookSearchInventory;
        this.patronInventory = patronInventory;
        this.bookInventory = bookInventory;
    }

    @Override
    public void bookStatus(String bookName) {
        String patronName = bookSearchInventory.getPatronNameByBook(bookName);
        // patronInventory.displayPatrons();
        // System.out.println(bookInventory.displayBooks());
        // System.out.println(bookInventory.displayBooks().contains(bookName));
        if(bookInventory.displayBooks().contains(bookName)){
        if (userHasBorrowedBook(bookName) ) {
            bookStatus.put(bookName, "Not Available");
            log.info("\033[1m" + bookName + " borrowed by " + patronName + "\033[0m");
            log.warning(bookName +"  Not Available");
        } else {
            bookStatus.put(bookName, "Available");
            log.info("\033[1m" + bookName + " Is available\033[0m");
        }
    }else{
        System.out.println(" Book not present in Library");
    }
      
    
        // log.info(bookStatus.toString());

          // if (!userHasBorrowedBook(bookName)) {
        //     bookStatus.put(bookName, "Not Available");
        //     log.info(bookName+ " borrowed by" +patronName);
        // } else {
        //     bookStatus.put(bookName, "Available");
        //     log.info(bookName+ "Book available");
        // }
        // log.info(bookStatus);
    }



    private Boolean userHasBorrowedBook(String bookName){
        // PatronsInventory newpatronInventory =  (PatronsInventory) patronInventory;
        // String patronName = bookSearchInventory.getPatronNameByBook(bookName);
        // System.out.println(patronName);
            for (Entry<String, List<String>> entry : patronInventory.getPatronsIssuedBooksMap().entrySet()) {
                if (entry.getValue().contains(bookName)) {
                    return true;
                }
            }
            // Entry<String, List<String>> entry = patronInventory.getPatronsIssuedBooksMap().entrySet();
            // if(patronInventory.getPatronsIssuedBooksMap().entrySet().contains(bookName))
            return false;
        }     
}
