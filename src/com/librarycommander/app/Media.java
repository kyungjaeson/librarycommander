package com.librarycommander.app;

import java.time.Duration;

public abstract class Media extends Item {
    private Duration length;

    public Duration getLength() {
        return length;
    }

    public void setLength(Duration length) {
        this.length = length;
    }
}