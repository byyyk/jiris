package com.jiris.web.thymeleaf.session;

import com.jiris.service.user.User;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public interface Session {
    UUID getUserId(HttpServletRequest request);
}
