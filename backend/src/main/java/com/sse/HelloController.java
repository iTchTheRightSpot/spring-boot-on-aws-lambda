package com.sse;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "${cors.url}")
@RestController
@RequestMapping(value = "${api.base.url}")
public class HelloController {

    private final List<HelloMessage> list = new ArrayList<>();

    @ResponseStatus(CREATED)
    @PostMapping(consumes = "application/json")
    public void persist (@RequestBody HelloMessage message) {
        list.add(new HelloMessage(message.name()));
    }

    @ResponseStatus(OK)
    @GetMapping(path = "/{index}", produces = "application/json")
    public HelloMessage message(@NotNull @PathVariable Integer index) {
        return this.list.get(index);
    }

    @ResponseStatus(OK)
    @GetMapping(produces = "application/json")
    public List<HelloMessage> greetings () {
        return this.list;
    }

}