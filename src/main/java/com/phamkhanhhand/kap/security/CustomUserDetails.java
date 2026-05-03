package com.phamkhanhhand.kap.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final String username;
    private final String fullName;
    private final String department;

    public CustomUserDetails(String username, String fullName, String department) {
        this.username = username;
        this.fullName = fullName;
        this.department = department;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; // bạn có thể thêm roles nếu muốn
    }

    @Override
    public String getPassword() {
        return null; // không dùng password ở đây
    }

    @Override
    public String getUsername() {
        return username;
    }

    // Các method khác trả về true để đơn giản
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
