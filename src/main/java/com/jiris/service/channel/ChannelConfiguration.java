package com.jiris.service.channel;

import com.jiris.service.channel.database.ChannelRepository;
import com.jiris.service.channel.database.MessageRepository;
import com.jiris.service.user.UserService;
import com.jiris.service.user.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.UUID;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Configuration
public class ChannelConfiguration {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private UserService userService;

    @Bean
    public ChannelResolver channelResolver() {
        return this::channel;
    }

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public Channel channel(UUID id) {
        return new ChannelImpl(id, messageRepository, channelRepository, userService);
    }
}
