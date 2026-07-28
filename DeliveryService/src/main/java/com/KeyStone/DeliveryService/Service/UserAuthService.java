package com.KeyStone.DeliveryService.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.KeyStone.DeliveryService.DTO.AuthResponseDTO;
import com.KeyStone.DeliveryService.DTO.ForgotPasswordDTO;
import com.KeyStone.DeliveryService.DTO.LoginRequestDTO;
import com.KeyStone.DeliveryService.DTO.RegisterRequestDTO;
import com.KeyStone.DeliveryService.DTO.ResetPasswordDTO;
import com.KeyStone.DeliveryService.Entity.UserAuth;
import com.KeyStone.DeliveryService.Repository.UserAuthRepository;
import com.KeyStone.DeliveryService.Security.EmailLogService;
import com.KeyStone.DeliveryService.Security.JWTUtil;
import com.KeyStone.DeliveryService.Security.TokenKillingService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAuthService {
	
	@Autowired
	private UserAuthRepository userAuthRepo;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailLogService emailLogService;
	
	@Autowired
	private TokenKillingService tokenKill;
	
	
	public AuthResponseDTO register(RegisterRequestDTO register) {
		
		Optional<UserAuth >existingUser=userAuthRepo.findByUserEmail(register.userEmail);
//				.orElseThrow(()-> new RuntimeException("User not found"));
		
		if(existingUser.isPresent()) {
			throw new RuntimeException("User already exist");
		}
		
		
		UserAuth user = new UserAuth();
		user.setUserName(register.userName);
		user.setUserEmail(register.userEmail);
		user.setPassword(passwordEncoder.encode(register.password));
		user.setPhone(register.phone);
		user.setRole(register.role);
		
		
		userAuthRepo.save(user);
		
		String token = jwtUtil.generateToken(user);
		return new AuthResponseDTO(token,"Register Successful");
		
	}
	
	public String login(LoginRequestDTO login) {
		
		UserAuth user = userAuthRepo.findByUserEmail(login.userEmail)
				       .orElseThrow(()-> new RuntimeException("User not found"));
		
		if(!passwordEncoder.matches(login.password, user.getPassword())) {
			throw new RuntimeException("Invalid Credential");
			
		}
		
		return jwtUtil.generateToken(user);
		
	}
	
	public void forgotPassword(ForgotPasswordDTO forgotPassword) {
		
		UserAuth user= userAuthRepo.findByUserEmail(forgotPassword.userEmail)
				     .orElseThrow(()-> new RuntimeException("User not found"));
		
		String token= UUID.randomUUID().toString();
		
		user.setResetToken(token);
		user.setTokenExpireTime(new Date(System.currentTimeMillis()+10*60*1000));
		
		userAuthRepo.save(user);
		
		emailLogService.sendResetPasswordMail(forgotPassword.userEmail,token);
		
	}
	
	public void resetPassword(ResetPasswordDTO resetPassword) {
		
		UserAuth user= userAuthRepo.findByUserEmail(resetPassword.token)
				     .orElseThrow(()-> new RuntimeException("Invalid token"));
		
		if(user.getTokenExpireTime().before(new Date())) {
			throw new RuntimeException("Link expire");
		}
		
		user.setPassword(passwordEncoder.encode(resetPassword.newPassword));
		
		user.setResetToken(null);
		user.setTokenExpireTime(null);
		
		userAuthRepo.save(user);
		
	}
	
	public String logout(HttpServletRequest request) {
		String header = request.getHeader("Authorozation");
		String token= jwtUtil.extractToken(header);
		
		if(token !=null) {
			tokenKill.blockTokenProcess(token);
		}
		
		return "Logged outsuccessfully";
	}

}


