package com.saharaj.moneytracker.application.tracker.model;

import com.saharaj.moneytracker.application.authentication.model.AppUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long accountID;
    @Column(unique = true, nullable = false)
    private String accountName;
    @ManyToOne
    @JoinColumn(name="userID", referencedColumnName = "userID")
    private AppUser user;

    public long getAccountID() {
        return this.accountID;
    }

    public void setAccountID(long bankID) {
        this.accountID = bankID;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public void setAccountName(String bankname) {
        this.accountName = bankname;
    }


    public AppUser getUser() {
        return this.user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
    

    public float getBalance() {
        return this.balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
    @Column(nullable = false)
    private float balance;

}
