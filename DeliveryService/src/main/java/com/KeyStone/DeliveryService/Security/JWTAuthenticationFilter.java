package com.KeyStone.DeliveryService.Security;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private CustomUseDetailsService customUseDetailsService;

    // private final JWTUtil jwtUtil;
    // public JWTAuthenticationFilter(JWTUtil jwtUtil) {
    //     this.jwtUtil = jwtUtil;
    // }

    public void doFilterInternal(HttpServletRequest request,
         HttpServletResponse response,
          FilterChain filterChain) throws ServletException, IOException {

            String authHeader = request.getHeader("Authorization");
            String token =null;
            
            if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
            }
            if(token != null && jwtUtil.validateToken(token)) {
                String userEmail = jwtUtil.extractUserEmail(token);
                UserDetails userDetails = customUseDetailsService.loadUserByUserEmail(userEmail);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request, response);
        }

}
