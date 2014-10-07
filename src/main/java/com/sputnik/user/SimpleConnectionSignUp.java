package com.sputnik.user;


import com.sputnik.persistence.User;
import com.sputnik.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * Simple little {@link org.springframework.social.connect.ConnectionSignUp} command that allocates new userIds in memory.
 * Doesn't bother storing a user record in any local database, since this quickstart just stores the user id in a cookie.
 * @author Keith Donald
 */
@Component
public final class SimpleConnectionSignUp implements ConnectionSignUp {

    @Autowired
    private UserRepository userRepository;

    public String execute(Connection<?> connection) {
        User user = userRepository.save(new User());

        return Long.toString(user.getId());
    }

}
