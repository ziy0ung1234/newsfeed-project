package com.newsfeed.domain.auth.security;

import com.newsfeed.domain.user.entity.User;
import com.newsfeed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Jwt에서 userId를 꺼내고 DB에서 User을 찾고, PrincipalDetails로 감싸서 반환한다.
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // 여기서 username 대신 userId를 username처럼 사용한다.
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));

        return new PrincipalDetails(user);
    }
}
