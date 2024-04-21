package com.saharaj.moneytracker.application.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saharaj.moneytracker.application.tracker.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
    
}
