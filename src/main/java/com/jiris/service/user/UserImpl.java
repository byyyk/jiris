package com.jiris.service.user;

import com.jiris.service.channel.ChannelResolver;
import com.jiris.service.user.database.UserRepository;

import java.util.UUID;

public class UserImpl implements User {
    private final UUID id;
    private final UserRepository userRepository;

    public UserImpl(UUID id, UserRepository userRepository) {
        this.id = id;
        this.userRepository = userRepository;
    }

    public UserInfo info() {
        var entity = userRepository.getById(id);
        return new UserInfo(entity.getId(), entity.getUsername());
    }

}
