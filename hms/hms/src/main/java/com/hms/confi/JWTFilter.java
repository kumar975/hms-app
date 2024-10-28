package com.hms.confi;

import com.hms.entity.AppUser;
import com.hms.repository.AppUserRepository;
import com.hms.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private JWTService jwtService;
    private AppUserRepository userRepository;

    public JWTFilter(JWTService jwtService, AppUserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

          String token=request.getHeader("Authorization");
        System.out.println(token);
        if(token!=null &&token.startsWith("Bearer ")){
            String tokenval= token.substring(8,token.length()-1);
            System.out.println(tokenval);
            String username = jwtService.getUsername(tokenval);
            Optional<AppUser> opUsername = userRepository.findByUsername(username);
            if(opUsername.isPresent()){

            }

        }
        filterChain.doFilter(request,response);
    }
}
