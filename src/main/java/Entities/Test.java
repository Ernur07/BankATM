package Entities;


import DatabaseConnection.DatabaseManager;

import javax.persistence.*;


public class Test {

    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();
        /*Client c=db.findClient(1);
        SavingAccount sa= new SavingAccount("name",0,0.5,"usd",c);*/
        System.out.println(db.getAllClient());

    }
}

