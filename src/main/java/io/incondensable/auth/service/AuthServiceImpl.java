package io.incondensable.auth.service;

import io.incondensable.auth.AuthService;
import io.incondensable.auth.LoginRequestDto;
import io.incondensable.auth.entity.User;
import io.incondensable.auth.exceptions.PasswordMismatchException;
import io.incondensable.auth.exceptions.UserNotFoundException;
import io.incondensable.auth.filters.jwt.InvJwt;
import io.incondensable.auth.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author abbas
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final InvJwt jwt;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, InvJwt jwt) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwt = jwt;
    }

    @Override
    public String login(LoginRequestDto dto) {
        User user = userRepository.findByUsername(dto.getUsername()).orElseThrow(
                () -> {
                    throw new UserNotFoundException(dto.getUsername());
                }
        );

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword()))
            throw new PasswordMismatchException();

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        InvUserDetails userDetails = (InvUserDetails) auth.getPrincipal();
        String token = jwt.generateToken(userDetails);

        user.setToken(token);

        userRepository.save(user);
        return token;
    }

}
