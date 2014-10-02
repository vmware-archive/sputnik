package com.sputnik.user;


import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Simple little {@link org.springframework.social.connect.ConnectionSignUp} command that allocates new userIds in memory.
 * Doesn't bother storing a user record in any local database, since this quickstart just stores the user id in a cookie.
 * @author Keith Donald
 */
public final class SimpleConnectionSignUp implements ConnectionSignUp {

    private final AtomicLong userIdSequence = new AtomicLong();

    public String execute(Connection<?> connection) {
        return Long.toString(userIdSequence.incrementAndGet());
    }

}
