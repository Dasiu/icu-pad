package com.icupad.exception;

public class CanNotChangePassword extends RuntimeException {
    public CanNotChangePassword(String msg) {
        super(msg);
    }
}
