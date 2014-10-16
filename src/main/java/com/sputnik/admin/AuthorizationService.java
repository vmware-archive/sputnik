package com.sputnik.admin;

import com.sputnik.persistence.User;
import com.sputnik.persistence.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class AuthorizationService {

    private static String ADMIN_ROLE = "ADMIN";

    @Inject
    UserRepository userRepository;

    public List<GrantedAuthority> getAuthorities(String userId) {
        User currentUser = userRepository.findOne(Long.valueOf(userId));

        if(currentUser.getAdmin()) {
            return AuthorityUtils.createAuthorityList(ADMIN_ROLE);
        } else {
            return AuthorityUtils.NO_AUTHORITIES;
        }
    }

    public static String getAdminRole() {
        return ADMIN_ROLE;
    }
}
