package com.alinso.stock.security;

import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomAuthUser  extends User {

    private final int dbId;


    public CustomAuthUser(String username, String password, boolean enabled,
                          boolean accountNonExpired, boolean credentialsNonExpired,
                          boolean accountNonLocked,
                          Collection authorities,
                          int dbId
    ) {

        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities);
        this.dbId = dbId;


    }

    public int getId() {
        return this.dbId;
    }
}
