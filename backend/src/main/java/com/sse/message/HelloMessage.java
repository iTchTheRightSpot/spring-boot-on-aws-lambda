package com.sse.message;

import java.io.Serializable;

public record HelloMessage(String name) implements Serializable { }