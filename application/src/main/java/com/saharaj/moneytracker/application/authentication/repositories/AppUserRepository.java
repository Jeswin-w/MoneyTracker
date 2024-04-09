package com.saharaj.moneytracker.application.authentication.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saharaj.moneytracker.application.authentication.model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional <AppUser> findUserByUsername(String username);
}
