package com.newsfeed.domain.auth.security;

import com.newsfeed.domain.user.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

// User 엔티티를 Spring Security에서 인증정보로 쓸 수 있게 감싸는 클래스이다.
@Getter
public class PrincipalDetails implements UserDetails {

    private final User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;  // 권한 없으면 null 또는 collections.emptyList()
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();  // 이메일로 로그인해서 getEmail
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
