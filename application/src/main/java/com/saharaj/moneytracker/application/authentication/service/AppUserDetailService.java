package com.saharaj.moneytracker.application.authentication.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.saharaj.moneytracker.application.authentication.model.AppUser;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.saharaj.moneytracker.application.authentication.repositories.AppUserRepository;

@Service
public class AppUserDetailService implements UserDetailsService   {

    private AppUserRepository appUserRepository;

    public AppUserDetailService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional <AppUser> foundUser = appUserRepository.findUserByUsername(username);
        if (foundUser.isPresent()) {
            AppUser user = foundUser.get();
            return User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .build();
        }
        throw new UsernameNotFoundException("no user");
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        Optional <AppUser> foundUser = appUserRepository.findUserByEmail(email);
        if (foundUser.isPresent()) {
            AppUser user = foundUser.get();
            return User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .build();
        }
        throw new UsernameNotFoundException("no user");
    }

}
