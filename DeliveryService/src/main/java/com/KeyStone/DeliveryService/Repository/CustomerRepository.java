package com.KeyStone.DeliveryService.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.KeyStone.DeliveryService.Entity.Customer;

@Repository


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Additional query methods can be defined here if needed
    Optional<Customer>findByEmail(String email);
    boolean existsByEmail(String email);
    


}
