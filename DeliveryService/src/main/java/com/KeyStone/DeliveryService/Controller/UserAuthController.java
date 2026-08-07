package com.KeyStone.DeliveryService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KeyStone.DeliveryService.DTO.AuthResponseDTO;
import com.KeyStone.DeliveryService.DTO.ForgotPasswordDTO;
import com.KeyStone.DeliveryService.DTO.LoginRequestDTO;
import com.KeyStone.DeliveryService.DTO.RegisterRequestDTO;
import com.KeyStone.DeliveryService.DTO.ResetPasswordDTO;
import com.KeyStone.DeliveryService.Service.UserAuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;



@Tag(
    name = "Authentication",
    description = "User authentication and account management APIs"
)
@RestController
@RequestMapping("/api/user_auth")
@RequiredArgsConstructor
public class UserAuthController {
	
	@Autowired
	private UserAuthService userAuthService;
	
	
	@Operation(summary = "Register a new user")
	@PostMapping("/register")
	public ResponseEntity<AuthResponseDTO>register(@RequestBody RegisterRequestDTO register){
		return ResponseEntity.ok(userAuthService.register(register));
	}
	
	@Operation(summary = "Login and get JWT token")
	@PostMapping("/login")
	public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO login){

		String token = userAuthService.login(login);

		return ResponseEntity.ok(
				new AuthResponseDTO(token, "Login Successful"));

	}
	

	@Operation(summary = "Forgot password")
	@PostMapping("/forgot_password")
	   public ResponseEntity<String>forgotPassword(@RequestBody ForgotPasswordDTO forgotPassword ){
		userAuthService.forgotPassword(forgotPassword);
		   return ResponseEntity.ok("Reset password link sent over on yur email");
	   }
	   
	   @Operation(summary = "Reset password")
	   @PostMapping("/reset_password")
	   public ResponseEntity<String>resetPassword(@RequestBody ResetPasswordDTO resetPassword){
		   userAuthService.resetPassword(resetPassword) ;
		   return ResponseEntity.ok("Password Reset successfully");
	   }
	   
	   @Operation(summary = "Logout user")
	   @PostMapping("/loggedOut")
	   public ResponseEntity<String>loggedOut(HttpServletRequest request){
		   return ResponseEntity.ok(userAuthService.logout(request));
	   }
	

}