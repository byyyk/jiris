package com.jiris.service.user;

import com.jiris.service.user.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.UUID;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Configuration
public class UserConfiguration {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public UserResolver userResolver() {
        return this::user;
    }

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public User user(UUID userId) {
        return new UserImpl(userId, userRepository);
    }
}
