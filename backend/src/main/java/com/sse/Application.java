package com.sse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sse.message.GraalRuntimeHints;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;

@SpringBootApplication
@ImportRuntimeHints(value = GraalRuntimeHints.class)
@RegisterReflectionForBinding(value = ReqHandler.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }

}
