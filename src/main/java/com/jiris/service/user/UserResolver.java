package com.jiris.service.user;

import java.util.UUID;

@FunctionalInterface
public interface UserResolver {
    User get(UUID userId);
}
