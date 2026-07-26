package com.KeyStone.DeliveryService.Repository;
import  com.KeyStone.DeliveryService.Entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    
    Optional<UserAuth> findByUserEmail(String userEmail);
    // Optional<UserAuth> findByUserId(Long userId);
}
