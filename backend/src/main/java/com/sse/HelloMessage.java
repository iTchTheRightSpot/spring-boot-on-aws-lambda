package com.sse;

import java.io.Serializable;

public record HelloMessage(String name) implements Serializable { }