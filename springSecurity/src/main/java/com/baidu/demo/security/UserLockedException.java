package com.baidu.demo.security;

import org.springframework.security.core.AuthenticationException;

public class UserLockedException extends AuthenticationException {

    /**
     * Constructs a <code>UsernameNotFoundException</code> with the specified message.
     *
     * @param msg the detail message.
     */
    public UserLockedException(String msg) {
        super(msg);
    }

    /**
     * Constructs a {@code UsernameNotFoundException} with the specified message and root
     * cause.
     *
     * @param msg the detail message.
     * @param t root cause
     */
    public UserLockedException(String msg, Throwable t) {
        super(msg, t);
    }
}
