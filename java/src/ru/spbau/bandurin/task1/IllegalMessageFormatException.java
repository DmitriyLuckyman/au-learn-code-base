package ru.spbau.bandurin.task1;

/**
 * Exception for Illegal message format .
 * @author Dmitriy Bandurin
 */
public class IllegalMessageFormatException extends Exception{
    public IllegalMessageFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalMessageFormatException(String message) {
        super(message);
    }
}
