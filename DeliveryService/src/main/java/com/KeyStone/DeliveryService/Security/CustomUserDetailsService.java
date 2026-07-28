package com.KeyStone.DeliveryService.Security;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.KeyStone.DeliveryService.Repository.UserAuthRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.KeyStone.DeliveryService.Entity.UserAuth;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Service

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        UserAuth user = userAuthRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail));

        return new User(
                user.getUserEmail(),
                user.getPassword(),
                Collections.emptyList()
        );
        // return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getPassword());
    }
}
