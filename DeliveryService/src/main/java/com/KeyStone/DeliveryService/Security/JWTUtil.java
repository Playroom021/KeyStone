package com.KeyStone.DeliveryService.Security;

import java.security.*;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.KeyStone.DeliveryService.Entity.UserAuth;
import io.jsonwebtoken.*;
 
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.KeyStone.DeliveryService.ENUM.Permission;
import com.KeyStone.DeliveryService.ENUM.Role;
import com.KeyStone.DeliveryService.Security.RoleBasedPermissions;

@Component


public class JWTUtil {

    private final Key key;
    private final long validateTime= 1000 * 60 * 60 *12 ; // 12 Hour 

    public JWTUtil() {
        String secret = System.getenv("JWT_SECRET");
        if (secret==null || secret.isEmpty()){
            secret="Replace This Place useing some secret key";
        }
        key=Keys.hmacShaKeyFor(secret.getBytes());

    }

    public String generateToken(UserAuth user){
        Map<String,Object> claims=new HashMap<>();
        claims.put("Role",user.getRole());

        Set<Permission>permission=RoleBasedPermissions.getRoleBasedPermissons().get(user.getRole());
        
        Date now =new Date();
        Date expire= new Date(now.getTime()+validateTime);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUserEmail())
                .setIssuedAt(now)
                .setExpiration(expire)
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaims(token);
            return true;

        }
        catch(JwtExeption e){
            return false;
        }
    }

    public Claims getClaim(String token){
        return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody();
    }

    public String getUserEmail(String token){
        return getClaim(token)
        .getSubject();
    }

}