package com.ocean.core.authentication;

import com.ocean.core.model.LoginUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author ltx
 */
public class OceanAuthentication implements Authentication {
    private Boolean isAuthenticated;

    @Getter
    @Setter
    private Long userId;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private LoginUser user;

    private List<GrantedAuthority> authority = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authority;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return username;
    }
}
