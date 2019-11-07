package Entities;


import DatabaseConnection.DatabaseManager;

import javax.persistence.*;


public class Test {

    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();
        Client c=new Client("Yernur","Yernur","Yernur","Yernur");
        db.add(c);
        System.out.println(db.getAllClient());
    }
}

