package Books.Inventories;

import java.util.List;
import java.util.Map;

import Books.Book;

public interface BookInventory {

    public abstract void addBook(String title, String author, String isbn, int publicationYear);
   
    public abstract void removeBook(String isbn);

    public abstract List<String> displayBooks();

    public Map<String,Book> getBooksMap(); 

}
