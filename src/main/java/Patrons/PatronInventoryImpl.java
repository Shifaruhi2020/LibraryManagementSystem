package Patrons;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import Patrons.PatronsInventory.PatronsInventory;

public class PatronInventoryImpl implements PatronsInventory{

    private static final Logger log = Logger.getLogger("PatronInventoryImpl");

    // instance variables

    private Map<String, String> patronDetailsMap = new HashMap<>(); //<patronNmae, PhoneNumber>

    public Map< String, List<String> > patronsIssuedBooksMap = new HashMap<>(); //<patronNmae, List<bookName>>

    private Map< String, Map< String, List <LocalDate> > > historyOfPatron = new HashMap<>();

    LocalDate returnDate;
    
    // getters

    @Override
    public Map<String, List<String>> getPatronsIssuedBooksMap() {
        return patronsIssuedBooksMap;
    }

    public Map<String, String> getPatronsDetailsMap() {
        return patronDetailsMap;
    }

    public Map<String, Map<String, List<LocalDate>>> getHistoryOfPatronMap(){
        return historyOfPatron;
    }

    @Override
    public void addPatron(String userName, String phoneNumber) {
       if (historyOfPatron.containsKey(userName) && patronDetailsMap.containsKey(userName)) {
            System.out.println("Patron already registered");
        }historyOfPatron.put(userName, new HashMap<>());
        patronDetailsMap.put(userName, phoneNumber);
        // patronsIssuedBooksMap.put(userName,null);
        log.info(userName + " \033[1m "+"added successfully \033[0m");

    }

    public String isPresentInHistoryMap(String userName){
        if(historyOfPatron.containsKey(userName)){
            return userName;
        }
        else{
            return "User not registered";
        }
    }

    @Override
    public void removePatron(String name) {
        Boolean found = false;
        if (historyOfPatron.containsKey(name)) {
            historyOfPatron.remove(name);
            patronDetailsMap.remove(name);
            patronsIssuedBooksMap.remove(name);
            found = true;
            log.warning(name + " \033[1m Patron removed successfully \033[0m");
        }
        if (!found) {
            log.warning(name + " \033[1m Patron not found \033[0m");
        }   
    }

    @Override
    public void searchByName(String name) {
        Boolean found = false;
        for(String patronName:patronDetailsMap.keySet()){
            if(patronName.equals(name)){
                log.info("found: " +patronName + " \033[1m \033[0m");
                found = true;
                break;
            }
        }
        if(!found){
            log.info(name+ " \033[1m not found \033[0m");
        }
    }

    @Override
    public void displayPatrons() {
        if(patronDetailsMap.keySet() != null){
            log.info("List of Library members : "+patronDetailsMap.keySet() + " \033[1m \033[0m");
        }    
    }

    @Override
    public String getPatronsPhoneNumber(String name) {
        if (patronDetailsMap.containsKey(name)) {
            return ("Phone number for " + name + ": " + patronDetailsMap.get(name) + " \033[1m \033[0m");
        } else {
            return (name + " \033[1m not found \033[0m");
        }
    }

    @Override
    public void updatePatron(String patronName) {
        Boolean found = false;
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        // updating in the patrons list
        if(patronDetailsMap.get(patronName) != null){
            log.warning("Update Information for "+patronName + " \033[1m \033[0m");
            System.out.println("Enter 1 to update name");
            System.out.println("Enter 2 to update phone number");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice){
                case 1:
                    updateName(patronName);
                    break;
                case 2:
                    updatePhoneNumber(patronName);
                    break;
                default:
                    log.info("Invalid choice" + " \033[1m \033[0m");
                    break;
            }
            found = true;
        }
        if(!found){
            log.info(patronName+ " \033[1m Patron not found \033[0m");
            log.info(patronDetailsMap.toString());
            log.info(historyOfPatron.toString());
            log.info("Enter Patron Phone Number to register:" + " \033[1m \033[0m");
            String phoneNumber = scanner.nextLine();
            addPatron(patronName, phoneNumber);
        }
    }

    private void updateName(String oldName){
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        log.info("Enter new name: " + " \033[1m \033[0m");
        String newName = scanner.nextLine();
     // Check if the old name exists in the  Patrons details map
     if (patronDetailsMap.containsKey(oldName)) {
        // Retrieve the associated phone number
        String phoneNumber = patronDetailsMap.get(oldName);
        // Remove the old key and insert the new key with the existing phone number
        patronDetailsMap.remove(oldName);
        patronDetailsMap.put(newName, phoneNumber);
        log.info("Updated successfully: " + oldName + " → " + newName + " \033[1m \033[0m");
    } else {
        log.info("Patron not found." + " \033[1m \033[0m");
    }
    // simulataneously updating the historyofPatronsMap
    log.info("old history is :" +historyOfPatron + " \033[1m \033[0m");
    if(historyOfPatron.containsKey(oldName)){
        Map<String, List<LocalDate>> booksMapInHistoryMap = historyOfPatron.get(oldName);

        historyOfPatron.remove(oldName);
        historyOfPatron.put(newName, booksMapInHistoryMap);
        log.info("updated the history map also" +historyOfPatron + " \033[1m \033[0m");
    }
    }

    private void updatePhoneNumber(String nameOfPatron){
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        boolean replacePhoneNumber = false;
        System.out.println("Enter new phone number: " + " \033[1m \033[0m");
        String newphoneNumber = scanner.nextLine();
        if(patronDetailsMap.containsKey(nameOfPatron)){
            String oldPhoneNumber = patronDetailsMap.get(nameOfPatron);
            replacePhoneNumber = patronDetailsMap.replace(nameOfPatron, oldPhoneNumber, newphoneNumber);
        }
        if(replacePhoneNumber){
            log.info(nameOfPatron+ " Number replaced to ->" +newphoneNumber + " \033[1m \033[0m");
        }
        else{
            log.info(nameOfPatron+ " Not registered Library Member" + " \033[1m \033[0m");
        }
    }

    @Override
    public void searchByBook(String bookName) {
        Boolean found = false;
        for (Map.Entry<String, Map<String, List<LocalDate>>> entry : historyOfPatron.entrySet()) {
            if (entry.getValue().containsKey(bookName)) {
                List<LocalDate> dates = entry.getValue().get(bookName);
                if (dates.contains(returnDate)) {
                    log.info(bookName + " not found in Patrons Inventory" + " \033[1m \033[0m");
                } else {
                    log.info(bookName + " found, issued to " + entry.getKey() + " \033[1m \033[0m");
                }
                found = true;
                break;
            }
        }
        if (!found) {
            log.info(bookName + " not found" + " \033[1m \033[0m");
        }
    }


}
