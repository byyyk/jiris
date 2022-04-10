package com.jiris.web.v1;

import com.jiris.service.channel.NewMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rest/v1/channel")
public class ChannelRest {

//    private final Jiris jiris;
//
//    @Autowired
//    public ChannelRest(Jiris jiris) {
//        this.jiris = jiris;
//    }
//
//    @PostMapping("{channel}/post")
//    public void post(NewMessage message, @PathVariable("channel") String channel) {
//        jiris.channel(channel).post(message);
//    }
//
//    @GetMapping("{channel}/history")
//    public void history(@PathVariable("channel") String channel) {
//        jiris.channel(channel).history();
//    }
}
