package com.jiris.service.user;

import com.jiris.service.user.database.UserEntity;
import com.jiris.service.user.database.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserResolver userResolver;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(NewUser newUser) {
        var username = unifyUserName(newUser.username());
        validateName(username);

        var entity = new UserEntity();
        entity.setUsername(username);
        return userResolver.get(userRepository.save(entity).getId());
    }

    @Override
    public User get(UUID userId) {
        return userResolver.get(userId);
    }

    @Override
    public User createOrLogin(String username) {
        username = unifyUserName(username);

        // TODO this creates user if does not exists and does not check password, swap with login functionality
        if (userRepository.existsByUsername(username)) {
            return userResolver.get(userRepository.findByUsername(username).getId());
        } else {
            return create(new NewUser(username));
        }
    }

    private String unifyUserName(String username) {
        return username.trim();
    }

    private void validateName(String username) {
        LOG.info("Validating username {}", username);
        if (username.isEmpty() || !username.chars().allMatch(it -> {
            var c = (char) it;
            return c >= 'a' && c <= 'z' ||
                    c >= 'A' && c <= 'Z' ||
                    c >= '0' && c <= '9' ||
                    c == '-' ||
                    c == '_';
        })) {
            throw new IllegalArgumentException(""); // TODO exception mapper and custom exception
        }
    }
}
