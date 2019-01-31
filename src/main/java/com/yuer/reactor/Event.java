package com.yuer.reactor;

import lombok.Data;

@Data
public class Event {
    private InputSource source;
    private EventType type;
}
