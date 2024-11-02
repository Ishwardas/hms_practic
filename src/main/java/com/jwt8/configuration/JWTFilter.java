package com.jwt8.configuration;

import com.jwt8.entity.user.AppUser;
import com.jwt8.repository.AppUserRepository;
import com.jwt8.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
@Component
public class JWTFilter extends OncePerRequestFilter {
    private JWTService jwtService;
private AppUserRepository appUserRepository;
    public JWTFilter(JWTService jwtService, AppUserRepository appUserRepository) {
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token!=null && token.startsWith("Bearer ")){
            String jwtToken = token.substring(8, token.length() - 1);
            String username = jwtService.getUsername(jwtToken);
            Optional<AppUser> appUser =
                    appUserRepository.findByUsername(username);
            if (appUser.isPresent())
            {
                AppUser user = appUser.get();
                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(user,null, Collections.singleton(new SimpleGrantedAuthority(user.getRole())));
                authenticationToken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}