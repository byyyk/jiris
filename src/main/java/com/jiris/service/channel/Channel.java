package com.jiris.service.channel;

import jdk.jshell.Snippet;

import java.util.UUID;

public interface Channel {
    void post(NewMessage message);
    History history();

    ChannelInfo info();

    void addUser(UUID userId);
}
