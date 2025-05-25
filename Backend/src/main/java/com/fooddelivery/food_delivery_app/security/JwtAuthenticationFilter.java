package com.fooddelivery.food_delivery_app.security;

import com.fooddelivery.food_delivery_app.service.impl.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("JwtAuthenticationFilter activated on: " + request.getMethod() + " " + request.getRequestURI());
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String username;

        // nu există header sau nu începe cu Bearer
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwtToken = authHeader.substring(7); // elimină "Bearer "

        try {
            username = jwtTokenUtil.extractUsername(jwtToken);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Extrage rolul direct din token
                String role = jwtTokenUtil.extractRole(jwtToken);

                // Adaugă prefixul ROLE_ dacă nu există deja
                if (role != null && !role.startsWith("ROLE_")) {
                    role = "ROLE_" + role;
                }

                System.out.println("Rol extras din JWT pentru utilizatorul " + username + ": " + role);

                // Creează autoritatea cu rolul corect
                List<SimpleGrantedAuthority> authorities =
                        Collections.singletonList(new SimpleGrantedAuthority(role));

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Verifică validitatea tokenului
                if (jwtTokenUtil.isTokenValid(jwtToken, ((CustomUserDetails) userDetails).getUser())) {
                    // Utilizează autoritățile extrase direct din token
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            System.err.println("Eroare la procesarea JWT: " + e.getMessage());
            // Nu aruncăm excepția mai departe, continuăm lanțul de filtre
        }

        filterChain.doFilter(request, response);
    }
}