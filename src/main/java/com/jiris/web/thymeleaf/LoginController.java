package com.jiris.web.thymeleaf;

import com.jiris.service.user.UserService;
import com.jiris.web.CookieNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

//    @PostMapping("/login")
//    public String login(@ModelAttribute("credentials") CredentialsModel credentials, HttpServletResponse response) {
//        var user = userService.createOrLogin(credentials.username());
//        //TODO session ID
//        response.addCookie(new Cookie(CookieNames.USER_ID, user.info().id().toString()));
//        return "redirect:/channel/join";
//    }
}
