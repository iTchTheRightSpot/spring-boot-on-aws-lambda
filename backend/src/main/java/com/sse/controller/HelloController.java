package com.sse.controller;

import com.sse.model.HelloMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@CrossOrigin(origins = "*")
@RestController
public class HelloController {

    private final List<HelloMessage> list = new ArrayList<>();

    @PostMapping
    void persist (@RequestBody HelloMessage message) {
        list.add(new HelloMessage(message.name()));
    }

    @GetMapping(path = "/sse", produces = TEXT_EVENT_STREAM_VALUE)
    SseEmitter sseEmitter () throws IOException {
        SseEmitter emitter = new SseEmitter(15000L); // 15 seconds
        emitter.send(this.list);
        return emitter;
    }

    @GetMapping
    List<HelloMessage> greetings () {
        return this.list;
    }

}