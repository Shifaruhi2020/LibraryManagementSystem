package Books.Inventories;

public interface BookSearchInventory {

    public void searchByTitle(String title);
    
    public void searchByAuthor(String author);
    
    public String searchByIsbn(String isbn);

    // public String getBookNameFromIsbn(String isbn);

    public String getPatronNameByBook(String title);

}
