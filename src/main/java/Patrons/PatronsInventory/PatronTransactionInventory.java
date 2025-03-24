package Patrons.PatronsInventory;

public interface PatronTransactionInventory {

    public abstract void issueBook(String name,String bookName);

    public abstract void returnBook(String name, String bookName);

}
