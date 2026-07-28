package com.KeyStone.DeliveryService.DTO;

import com.KeyStone.DeliveryService.ENUM.Role;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDTO {
	
	public String userName;
	public String userEmail;
	public String password;
	public String phone;
	public Role role;
	

}
