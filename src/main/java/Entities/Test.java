package Entities;


import DatabaseConnection.DatabaseManager;

import javax.persistence.*;


public class Test {

    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();
        Client c=new Client("Yernur","Yernur","Yernur");
        SavingAccount s = new SavingAccount("Yernur",0,0,"USD",c);
        db.add(c);
    }
}

