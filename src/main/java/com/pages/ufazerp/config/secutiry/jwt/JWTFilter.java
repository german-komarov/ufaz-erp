package com.pages.ufazerp.config.secutiry.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.pages.ufazerp.services.UserDetailsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.pages.ufazerp.util.tools.JsonUtils.message;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl userDetailsService;
    private final JWTUtil jwtUtil;
    private final JsonMapper mapper;

    public JWTFilter(UserDetailsServiceImpl userDetailsService, JWTUtil jwtUtil, JsonMapper mapper) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && !authHeader.trim().isEmpty() && authHeader.startsWith("Bearer ")){
            String jwt = authHeader.substring(7);
            if(jwt.trim().isEmpty()){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token in Bearer Header");
            }else {
                try{
                    String email = jwtUtil.validateTokenAndRetrieveSubject(jwt);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                    if(SecurityContextHolder.getContext().getAuthentication() == null){
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                } catch (UsernameNotFoundException | TokenExpiredException e) {
                    writeUnauthorizedResponse(response, e.getMessage());
                    return;
                } catch(JWTVerificationException e){
                    writeUnauthorizedResponse(response, "Invalid token");
                    return;
                }
            }
        }

        filterChain.doFilter(request, response);
    }


    public void writeUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        String responseJson = mapper.writeValueAsString(message(message));
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().println(responseJson);
    }
}
