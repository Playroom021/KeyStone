package com.KeyStone.DeliveryService.DTO;

import lombok.*;
// import com.KeyStone.DeliveryService.model.Role;

@Data 
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LoginRequestDTO {

    public String userEmail;
    public String password;
    
}
