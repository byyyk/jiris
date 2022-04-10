package com.jiris.service.channel;

import java.util.UUID;

@FunctionalInterface
public interface ChannelResolver {
    Channel get(UUID id);
}
