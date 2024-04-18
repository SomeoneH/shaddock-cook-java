package com.shaddock.common;


public class ShaddockException extends RuntimeException {

    public ShaddockException() {
    }

    public ShaddockException(String message) {
        super(message);
    }

    //抛出异常
    public static void fail(String message) {
        throw new ShaddockException(message);
    }

}
