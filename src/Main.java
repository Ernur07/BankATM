import Entities.Bank;
import Entities.Client;
import Entities.Manager;

import java.util.ArrayList;

/**
 * GUI Starting class
 */
public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager("bank","manager","manager");
        Bank bank=new Bank(manager,new ArrayList<Client>());

        OpeningMenu menu = new OpeningMenu(bank);
    }
}
