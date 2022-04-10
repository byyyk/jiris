package com.jiris.service.channel;

import com.jiris.service.channel.database.ChannelRepository;
import com.jiris.service.channel.database.MessageEntity;
import com.jiris.service.channel.database.MessageRepository;
import com.jiris.service.user.User;
import com.jiris.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

public class ChannelImpl implements Channel {

    private static final Logger LOG = LoggerFactory.getLogger(ChannelImpl.class);
    private final MessageRepository messageRepository;
    private final UserService userService;
    private final UUID id;
    private final ChannelRepository channelRepository;

    public ChannelImpl(UUID id, MessageRepository messageRepository, ChannelRepository channelRepository, UserService userService) {
        this.id = id;
        this.channelRepository = channelRepository;
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    @Override
    public void post(NewMessage message) {
        LOG.info("New message on channel {}: {}", id, message);
        messageRepository.save(toEntity(message));
    }

    @Override
    public History history() {
        var messages = messageRepository.getMessagesFromChannel(id);

        // TODO messages and users are detached on entity level (there is only UUID instead of regular hibernate
        //  ManyToOne) because they will be separated into two different microservices at the next development stage.
        var usernames = new HashMap<UUID, String>();
        messages.stream()
                .map(MessageEntity::getUserId)
                .distinct()
                .map(userService::get)
                .map(User::info)
                .forEach(it -> usernames.put(it.id(), it.username()));

        return new History(messages.stream()
                .map(it -> new PostedMessage(usernames.get(it.getUserId()), it.getText(), it.getPostedAt()))
                .collect(Collectors.toList()));
    }

    @Override
    public ChannelInfo info() {
        var entity = channelRepository.findById(id).orElseThrow();
        return new ChannelInfo(id, entity.getName());
    }

    @Override
    public void addUser(UUID userId) {
        // TODO
    }

    private MessageEntity toEntity(NewMessage message) {
        var entity = new MessageEntity();
        entity.setPostedAt(LocalDateTime.now(ZoneOffset.UTC));
        entity.setUserId(message.userId());
        entity.setText(message.text());
        entity.setChannelId(id);

        return entity;
    }

}
