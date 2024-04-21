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
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long categoryID;
    @Column(unique = true, nullable = false)
    private String categoryName;
    @ManyToOne
    @JoinColumn(name="userID", referencedColumnName = "userID")
    private AppUser user;

    public long getCategoryID() {
        return this.categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public AppUser getUser() {
        return this.user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
    
}
