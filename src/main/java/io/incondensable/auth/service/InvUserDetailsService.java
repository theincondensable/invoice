package io.incondensable.auth.service;

import io.incondensable.auth.entity.User;
import io.incondensable.auth.exceptions.UserNotFoundException;
import io.incondensable.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author abbas
 */
@Service
public class InvUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(username).orElseThrow(
                () -> {
                    throw new UserNotFoundException(username);
                }
        );

        return InvUserDetails.mapUserToInvUserDetails(u);
    }

}
