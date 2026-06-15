package com.test.aiko.exceptions;

public class LincenseAlreadyActivatedException extends RuntimeException {
    public LincenseAlreadyActivatedException() {
        super("The license has already been activated");
    }
}
