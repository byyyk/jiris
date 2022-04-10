package com.jiris.service.channel;

import java.util.UUID;

public interface ChannelService {
    Channel get(UUID id);
    Channel getOrCreate(String channelName);
}
