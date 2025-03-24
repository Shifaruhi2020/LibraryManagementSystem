package Books;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


import Books.Inventories.BookInventory;



public class BookInventoryImpl implements BookInventory {

    private static final Logger log = Logger.getLogger("BookInventoryImpl");

    private Map<String, String> bookStatus = new HashMap<>();
    private Map<String,Book> booksMap ;

    
    public BookInventoryImpl() {
        this.booksMap = new HashMap<>(); // Initialize booksMap to avoid null errors
    }

    public void setBooksMap(Map<String,Book> bookMap){
        this.booksMap = bookMap;
    }


     @Override
        public void addBook(String title, String author, String isbn, int publicationYear) {
        Book book = new Book(title, author, isbn, publicationYear);
            if(booksMap.containsKey(isbn) && bookStatus.containsKey(title)){
                log.warning("Book Already Exists");
            }else{
                booksMap.put(isbn, book);
            bookStatus.put(title, "Available");
            log.info("\033[1mBook added:\033[0m " + book.title);
            }   
        }


        // @SuppressWarnings("unused")
        @Override 
        public void removeBook(String isbn) {
            // Book removedBook = booksMap.remove(isbn);
            // bookStatus.remove(removedBook.title);
            if (isbn != null && booksMap.containsKey(isbn)) {
                Book removedBook = booksMap.remove(isbn);
                // have to correcr it

                bookStatus.remove(removedBook.title);
                log.info(removedBook.title + "Book Removed Sucessfully");
            } else {
                log.info("\033[1mBook not found\033[0m");
            }
            // log.info(booksMap.toString());
        }
        
        @Override
        public List<String> displayBooks() {
            List<String> booksList = new ArrayList<>();
            for (Map.Entry<String, String> entry : bookStatus.entrySet()) {
                if ("Available".equals(entry.getValue())) {
                    booksList.add(entry.getKey());
                }
            }
            return booksList;
        }


    public Map<String,Book> getBooksMap(){
        return booksMap; 
    }

    }
