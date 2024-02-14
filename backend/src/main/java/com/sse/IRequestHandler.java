package com.sse;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IRequestHandler implements RequestStreamHandler {

    private static final Logger log = LoggerFactory.getLogger(IRequestHandler.class);
    private static final SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

    static {
        try {
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(Application.class);
        } catch (ContainerInitializationException e) {
            log.error(e.getMessage());
            throw new RuntimeException("could not initialize Spring Boot application", e);
        }
    }

    @Override
    public void handleRequest(
            InputStream inputStream,
            OutputStream outputStream,
            Context context
    ) throws IOException {
        handler.proxyStream(inputStream, outputStream, context);
    }

}
