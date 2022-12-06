package com.isaackubo.guava.demo5;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;

public class FileChangeEvent {
    private final WatchEvent.Kind<?> kind;

    private final Path path;

    public FileChangeEvent(WatchEvent.Kind<?> kind, Path path) {
        this.kind = kind;
        this.path = path;
    }

    public WatchEvent.Kind<?> getKind() {
        return kind;
    }

    public Path getPath() {
        return path;
    }
}
