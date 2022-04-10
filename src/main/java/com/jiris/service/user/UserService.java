package com.jiris.service.user;

import java.util.UUID;

public interface UserService {
    User create(NewUser newUser);
    User get(UUID userId);
    User createOrLogin(String username);
}
