package Entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Represents Loan of the clients
 */
@Entity
public class Loan {

    @Id
    @SequenceGenerator(name = "loan_id_gen", sequenceName = "loan_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_id_gen")
    int id;

    private double annualInterestRate;
    private int numberOfYears;
    private double loanAmount;
    private Date loanDate;
    private double totalPayment;
    @ManyToOne(fetch = FetchType.EAGER, cascade ={CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "client_id")
    private Client owner;

    public Loan(){}
    public Loan(double annualInterestRate, int numberOfYears,
                double loanAmount, Client owner) {
        this.annualInterestRate = annualInterestRate;
        this.numberOfYears = numberOfYears;
        this.loanAmount = loanAmount;
        loanDate = new Date();
        this.totalPayment = this.getTotalPayment1();
        this.owner=owner;
    }
    public Loan(int numberOfYears,
                double loanAmount, Client owner) {
        this.numberOfYears = numberOfYears;
        this.loanAmount = loanAmount;
        loanDate = new Date();
        this.totalPayment = this.getTotalPayment();
        this.owner=owner;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getMonthlyPayment() {
        double monthlyInterestRate = annualInterestRate / 1200;
        return loanAmount * monthlyInterestRate / (1 -
                (Math.pow(1 / (1 + monthlyInterestRate), numberOfYears * 12)));
    }

    public double getTotalPayment1() {
        return getMonthlyPayment() * numberOfYears * 12;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public double getTotalPayment() {
        return this.totalPayment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

}
