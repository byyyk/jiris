package com.jiris.service.channel;

import java.util.UUID;

public record NewMessage(UUID userId, String text) {
}
