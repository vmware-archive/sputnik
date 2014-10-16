package com.sputnik.signin;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class SignInUtils {

    /**
     * Programmatically signs in the user with the given the user ID.
     */
    public static void signin(String userId, List<GrantedAuthority> authorities) {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId, null, authorities));
    }

}