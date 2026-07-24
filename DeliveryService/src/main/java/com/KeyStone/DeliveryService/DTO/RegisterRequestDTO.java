package com.KeyStone.DeliveryService.DTO;

import lombok.*;
import com.KeyStone.DeliveryService.ENUM.Role;

@Data 
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class RegisterRequestDTO {
    
    public String userName;
    public String userEmail;
    public String password;
    public Role role;

}
