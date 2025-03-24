package Patrons;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import Patrons.PatronsInventory.PatronsInventory;
import Patrons.PatronsInventory.PatronsHistoryInventory;

public class PatronsHistoryInventoryImpl implements PatronsHistoryInventory{

    private static final Logger log = Logger.getLogger("PatronsHistoryInventoryImpl");

    private PatronsInventory patronInventory;

    public PatronsHistoryInventoryImpl(PatronsInventory patronInventory) {
        this.patronInventory = patronInventory;
    }

    @Override
    public void getHistoryOfPatron(String userName) {
        Map<String, Map<String, List<LocalDate>>> historyOfPatron = patronInventory.getHistoryOfPatronMap();
        if (historyOfPatron.containsKey(userName)) {
            log.info("\033[1m"+"History for " + userName + ": \033[0m");
            Map<String, List<LocalDate>> userHistory = historyOfPatron.get(userName);
            for (String bookName : userHistory.keySet()) {
                log.info("  " + bookName + " - " + userHistory.get(bookName));
            }
        } else {
            log.info(userName + "\033[1m"+" has no history. \033[0m");
        }
    
    }


}
