package com.jiris.web.thymeleaf.session;

import com.jiris.web.CookieNames;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.UUID;

@Service
public class SessionImpl implements Session {

    @Override
    public UUID getUserId(HttpServletRequest request) {
        var id = Arrays.stream(request.getCookies())
                .filter(it -> it.getName().equals(CookieNames.USER_ID))
                .findFirst()
                .orElseThrow()
                .getValue();
        return UUID.fromString(id);
    }
}
