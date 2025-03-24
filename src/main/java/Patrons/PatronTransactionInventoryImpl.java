package Patrons;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import Books.Inventories.BookSearchInventory;
import Patrons.PatronsInventory.PatronTransactionInventory;
import Patrons.PatronsInventory.PatronsInventory;

public class PatronTransactionInventoryImpl implements PatronTransactionInventory{

    private static final Logger log = Logger.getLogger("PatronTransactionInventoryImpl");
    
    LocalDate issueDate = LocalDate.now();

    //LocalDate returnDate = LocalDate.now();

    LocalDate returnDate = LocalDate.now().plusDays(16); //hardcode return date to demonstrate late fine


    private BookSearchInventory searchInventory;

    private PatronsInventory patronInventory;

    public PatronTransactionInventoryImpl(PatronsInventory patronInventory, BookSearchInventory searchInventory) {
        this.patronInventory = patronInventory;
        this.searchInventory = searchInventory;
    }

    // public Map<String, Map<String, List<LocalDate>>> getHistoryOfPatron() {
    //     return patronInventory.historyOfPatron;
    // }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
    @Override
    public void issueBook(String userName,String isbn) {
        Map<String, Map<String, List<LocalDate>>> historyOfPatron = patronInventory.getHistoryOfPatronMap();
        Map<String, List<String>> patronsIssuedBookMap = patronInventory.getPatronsIssuedBooksMap();
        // log.info(" \033[1m" + "history in get method \033[0m" + historyOfPatron);
        // log.info(" \033[1m" + "patronsIssuedBookMap issue \033[0m" + patronsIssuedBookMap);
        String bookName = searchInventory.searchByIsbn(isbn);
        if(bookName != null ){
        log.info(bookName);
        if(patronInventory.getPatronsDetailsMap().keySet().contains(userName)){

        log.info(" \033[1m" + bookName + " issued successfully to " + userName + " \033[0m");
            // Check if the user already has a history
            if (!historyOfPatron.containsKey(userName)) {
                historyOfPatron.put(userName, new HashMap<>());
            }
            if (!historyOfPatron.get(userName).containsKey(bookName)) {
                historyOfPatron.get(userName).put(bookName, new ArrayList<>());
            }
            historyOfPatron.get(userName).get(bookName).add(issueDate);
            // System.out.println(patronsIssuedBookMap.get(userName));
            // checking in patronIssueMap : < patronName, List<bookName> >
            if (!patronsIssuedBookMap.containsKey(userName)) {
                patronsIssuedBookMap.put(userName, new ArrayList<>());
            }
            if (!patronsIssuedBookMap.get(userName).contains(bookName)) {
                patronsIssuedBookMap.get(userName).add(bookName);
            }
        }else{ 
            // System.out.println(patronInventory.getPatronsDetailsMap().keySet().contains(userName));
            log.warning(userName +" not found, Please Register First");
        }
        }else{
                if(bookName == null){log.warning( " not found, Isuue Valid Book");}
            }
            // System.out.println(patronsIssuedBookMap);
        }
    
    @Override
    public void returnBook(String userName, String bookName) {
        Map<String, Map<String, List<LocalDate>>> historyOfPatron = patronInventory.getHistoryOfPatronMap();
        Map<String, List<String>> patronsIssuedBookMap = patronInventory.getPatronsIssuedBooksMap();

        if(bookName != null ){
            log.info(bookName);

            if(patronInventory.getPatronsDetailsMap().keySet().contains(userName)){
        

        if (!historyOfPatron.containsKey(userName)) {
            log.info(" \033[1m" + userName + " has no borrowing history. \033[0m");
            return;
        }
        if (historyOfPatron.containsKey(userName) & historyOfPatron.get(userName).containsKey(bookName)) {
            historyOfPatron.get(userName).get(bookName).add(returnDate);
            log.info(" \033[1m" + bookName + " successfully returned by " + userName + " \033[0m");
            long overdueDays = java.time.temporal.ChronoUnit.DAYS.between(issueDate, returnDate) - 14; // Assuming a 14-day borrowing period
            if (overdueDays > 0) {
                log.info("return date was overdue by , " + overdueDays + " Days, OverDue Fine is : " + calculateFine(userName, historyOfPatron) +" Rupees.");
            }
        } else {
            // log.info(" \033[1m" + userName + " has not borrowed " + bookName + " \033[0m");
        }

        if (!patronsIssuedBookMap.containsKey(userName)) {
            log.info(" \033[1m" + userName + " has no borrowing history. \033[0m");
            return;
        }
        if (patronsIssuedBookMap.containsKey(userName) & patronsIssuedBookMap.get(userName).contains(bookName)) {
            patronsIssuedBookMap.get(userName).remove(bookName);
            // log.info(" \033[1m" + bookName + " successfully returned by " + userName + " \033[0m");
        } else {
            log.info(" \033[1m" + userName + " has not borrowed " + bookName + " \033[0m");
        }

    }else{ 
        // System.out.println(patronInventory.getPatronsDetailsMap().keySet().contains(userName));
        log.warning(userName +" not found, Please Register First");
    }
    }else{
            if(bookName == null){log.warning( " Book Not Issued");}
        }
    }

    public Integer calculateFine(String userName, Map<String, Map<String, List<LocalDate>>> historyOfPatron){
        int fine = 0;
        int fineRatePerDay = 1; // Assuming the fine rate is $1 per day

        if (historyOfPatron.containsKey(userName)) {
            Map<String, List<LocalDate>> booksHistory = historyOfPatron.get(userName);
            for (Map.Entry<String, List<LocalDate>> entry : booksHistory.entrySet()) {
                List<LocalDate> dates = entry.getValue();
                if (dates.size() >= 2) {
                    LocalDate issueDate = dates.get(0);
                    LocalDate returnDate = dates.get(1);
                    long overdueDays = java.time.temporal.ChronoUnit.DAYS.between(issueDate, returnDate) - 14; // Assuming a 14-day borrowing period
                    if (overdueDays > 0) {
                        fine += overdueDays * fineRatePerDay;
                    }
                }
            }
        }

        return fine;
    }


}



    
