package com.sse;

import com.sse.filter.IRequestHandler;
import com.sse.message.GraalRuntimeHints;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

@SpringBootApplication
@ImportRuntimeHints(value = GraalRuntimeHints.class)
@RegisterReflectionForBinding(value = IRequestHandler.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
