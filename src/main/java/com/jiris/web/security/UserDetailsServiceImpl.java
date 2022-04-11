package com.jiris.web.security;

import com.jiris.service.user.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {

    //TODO UserService
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!userRepository.existsByUsername(username)) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
        var user = userRepository.findByUsername(username);

        // TODO
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of((GrantedAuthority) () -> "USER");
            }

            @Override
            public String getPassword() {
//                return "{noop}secret"; // works when PasswordEncoder bean is removed
                return "$2a$10$DR6sd0GfajEQSO/CEWMDAeKc7FgY2Q0y3s7DEnVrSXRQAN/yh8WTu"; // "secret" in bcrypt with 10 rounds
            }

            @Override
            public String getUsername() {
                return null;
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
        };
    }
}
