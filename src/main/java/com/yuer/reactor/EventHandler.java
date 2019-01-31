package com.yuer.reactor;

import lombok.Data;

@Data
public abstract class EventHandler {
    private InputSource source;
    public abstract void handle(Event event);
}
