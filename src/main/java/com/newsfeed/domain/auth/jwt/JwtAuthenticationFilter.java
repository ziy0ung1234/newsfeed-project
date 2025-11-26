package com.newsfeed.domain.auth.jwt;

import com.newsfeed.domain.auth.security.PrincipalDetails;
import com.newsfeed.domain.auth.security.PrincipalDetailsService;
import com.newsfeed.domain.user.entity.User;
import com.newsfeed.domain.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import static com.newsfeed.domain.auth.jwt.JwtConstants.*;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final PrincipalDetailsService principalDetailsService;


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
//        System.out.println("RequestURI: " + path);

        return path.startsWith("/user/signup") ||
                path.startsWith("/user/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
        throws ServletException, IOException {

        String token = resolveToken(request);

        // 토큰이 유효 했을 때
        if (token != null && jwtProvider.validateToken(token)) {

            Long userId = jwtProvider.getUserId(token);
            PrincipalDetails principalDetails = (PrincipalDetails) principalDetailsService.loadUserByUsername(String.valueOf(userId));

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            principalDetails,
                            null,
                            principalDetails.getAuthorities()
                    );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 토큰이 유효하지 않을 때 처리(후순위)

        filterChain.doFilter(request, response);

    }

    private String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION_HEADER);

        if (bearer != null && bearer.startsWith(BEARER_PREFIX)) {
            return bearer.substring(7);
        }

        return null;
    }
}
