package com.jiris.web.thymeleaf;

import com.jiris.service.channel.ChannelService;
import com.jiris.service.channel.NewMessage;
import com.jiris.service.user.UserService;
import com.jiris.web.thymeleaf.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private UserService userService;

    @Autowired
    private Session session;

    @GetMapping("/channel/join")
    public String showJoin() {
        return "channel-join";
    }

    @PostMapping("/channel/join")
    public String join(@RequestParam("channelName") String channelName, HttpServletRequest request) {
        var channel = channelService.getOrCreate(channelName);
        channel.addUser(session.getUserId(request));
        return "redirect:/channel/" + channel.info().id();
    }

    @GetMapping("/channel/{channelId}")
    public String showChannel(@PathVariable("channelId") UUID channelId, Model model) {
        var channel = channelService.get(channelId);
        var history = channel.history();
        model.addAttribute("channel", channel.info());
        model.addAttribute("history", history);
        return "channel";
    }

    @PostMapping("/channel/{channelId}/post")
    public String postMessage(@PathVariable("channelId") UUID channelId, @RequestParam("message") String message, HttpServletRequest request) {
        channelService.get(channelId).post(new NewMessage(session.getUserId(request), message));
        return "redirect:/channel/" + channelId; //TODO accept message without refreshing the view
    }
}
