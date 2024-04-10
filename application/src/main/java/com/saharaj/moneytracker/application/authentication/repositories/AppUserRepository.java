package com.saharaj.moneytracker.application.authentication.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saharaj.moneytracker.application.authentication.model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    //Spring Data JPA uses reflection and dynamic proxies to generate the implementation for repository methods based on naming conventions and method signatures.
    Optional <AppUser> findUserByUsername(String username);
    Optional <AppUser> findUserByEmail(String email);
}
