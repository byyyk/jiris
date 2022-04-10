package com.jiris.service.channel;

import com.jiris.service.channel.database.ChannelEntity;
import com.jiris.service.channel.database.ChannelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChannelServiceImpl implements ChannelService {
    private static final Logger LOG = LoggerFactory.getLogger(ChannelServiceImpl.class);

    @Autowired
    private ChannelResolver channelResolver;

    @Autowired
    private ChannelRepository channelRepository;

    public Channel create(NewChannel newChannel) {
        var channelName = unifyChannelName(newChannel.name());
        validateName(channelName);

        var entity = new ChannelEntity();
        entity.setName(channelName);
        return channelResolver.get(channelRepository.save(entity).getId());
    }

    public Channel getOrCreate(String channelName) {
        channelName = unifyChannelName(channelName);

        if (channelRepository.existsByName(channelName)) {
            return channelResolver.get(channelRepository.findByName(channelName).getId());
        } else {
            return create(new NewChannel(channelName));
        }
    }

    private String unifyChannelName(String channelName) {
        return channelName.trim().toLowerCase();
    }

    private void validateName(String channelName) {
        LOG.info("Validating channel name {}", channelName);
        if (channelName.isEmpty() || !channelName.chars().allMatch(it -> {
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

    @Override
    public Channel get(UUID id) {
        return channelResolver.get(id);
    }
}
