package io.incondensable.auth.filters.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.incondensable.auth.service.InvUserDetails;
import io.incondensable.global.exception.model.BusinessException;
import io.incondensable.global.exception.model.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Optional;

/**
 * @author abbas
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private InvJwt jwt;
    @Autowired
    private UserDetailsService userDetailsService;

    private final String BEARER_PREFIX = "Bearer ";
    private final String JWT_IS_EXPIRED = "Jwt Token is not valid.";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        extractJwtToken(request).ifPresentOrElse( // 1. extract and then look for the User using its JWT token.
                token -> {
                    String username = jwt.getUsernameFromToken(token);
                    try {
                        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) createAuthentication(username);
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        filterChain.doFilter(request, response);
                    } catch (BusinessException e) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                        response.setStatus(HttpStatus.UNAUTHORIZED.value());
                        try {
                            PrintWriter writer = response.getWriter();
                            objectMapper.writeValue(writer, new ErrorDetails(JWT_IS_EXPIRED, HttpStatus.UNAUTHORIZED, null));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } catch (ServletException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }, () -> {
                    try {
                        response.sendError(HttpStatus.UNAUTHORIZED.value(), "failed to authorize.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
        );
    }

    private Optional<String> extractJwtToken(HttpServletRequest request) {
        try {
            String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (Objects.nonNull(auth)) {
                if (auth.startsWith(BEARER_PREFIX)) {
                    String token = auth.substring(BEARER_PREFIX.length()).trim();
                    if (!token.isBlank() && jwt.validateTokens(token)) {
                        return Optional.of(token);
                    }
                }
            }
            return Optional.empty();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    private Authentication createAuthentication(String username) throws BusinessException {
        InvUserDetails userDetails = (InvUserDetails) userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );
    }

    @Override
    public boolean shouldNotFilter(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        if (servletPath.contains("/swagger") || servletPath.contains("/v3/api-docs") || servletPath.contains("docs"))
            return true;

        if (servletPath.contains("encode"))
            return true;

        if (servletPath.contains("login"))
            return true;

        return false;
    }

}
