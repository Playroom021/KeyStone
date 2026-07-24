package com.KeyStone.DeliveryService.Security;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.KeyStone.DeliveryService.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;

@Service

public class CustomUseDetailsService {

    @Autowired
    private UserRepository userRepository;

    private UserDetails loadUserByUserEmail(String userEmail){
        UserAuth user= userRepository.findByUserEmail(userEmail)
        .orElseThrow(()-> new UsernameNotFoundException("User not found with email: "+userEmail));
        return new org.springframework.security.core.userdetails.User(user.getUserEmail(),user.getUserPassword(),user.getAuthorities());
    }
}
