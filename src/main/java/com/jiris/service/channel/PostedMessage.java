package com.jiris.service.channel;

import java.time.LocalDateTime;

public record PostedMessage(String postedBy, String text, LocalDateTime postedAt) {
}
