package com.test.aiko.exceptions;

public class InvalidLicenseException extends RuntimeException {
    public InvalidLicenseException() {
        super("invalid lincense");
    }
}
