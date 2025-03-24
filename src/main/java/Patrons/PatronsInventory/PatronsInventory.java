package Patrons.PatronsInventory;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface PatronsInventory {
    
    public abstract void addPatron(String name, String phoneNumber);
   
    public abstract void removePatron(String name);

    public abstract void searchByName(String name);

    public abstract void updatePatron(String name);

    public abstract void displayPatrons();

    public abstract String getPatronsPhoneNumber(String name);

    public abstract void searchByBook(String bookName);

    public Map<String, List<String>> getPatronsIssuedBooksMap();

    public Map<String, String> getPatronsDetailsMap();

    public Map<String, Map<String, List<LocalDate>>> getHistoryOfPatronMap();

}
