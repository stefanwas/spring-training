package com.stefan.training.spring.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AuthenticatedUser extends User implements UserDetails {

    public AuthenticatedUser(String username, String password, String encryptedPassword, String roles) {
        super(username, password, encryptedPassword, roles);
    }

    @Override
    public String getPassword() {
        // this is intentional
        return getEncryptedPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        String roles = getRoles();

        if (!StringUtils.isEmpty(roles)) {
            String[] separateRoles = roles.split(",");
            System.out.println("Separate Roles=" + separateRoles);
            Arrays.stream(separateRoles).forEach(
                    role -> authorities.add((GrantedAuthority) () -> role.trim().toUpperCase()));
        }
        return authorities;
    }


}
