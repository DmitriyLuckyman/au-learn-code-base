package ru.spbau.bandurin.task1;

/**
 * Exception for Illegal message format .
 * @author Dmitriy Bandurin
 */
public class IllegalMessageFormatException extends Exception{

    /**
     * Create new exception with detailed message and cause
     * @param message detailed message
     * @param cause original cause
     */
    public IllegalMessageFormatException(final String message,final Throwable cause) {
        super(message, cause);
    }

    /**
     * Create new exception with detailed message
     * @param message detailed message
     */
    public IllegalMessageFormatException(final String message) {
        super(message);
    }

    /**
     * Create IllegalMessageFormatException with special message for read line count error
     * @param filePath path to file with messages
     * @param currentLine current line in file with error
     * @param cause original cause
     * @return initialized IllegalMessageFormatException object
     */
    public static IllegalMessageFormatException readLineCountProblem(final String filePath, final int currentLine, Throwable cause){
        return new IllegalMessageFormatException(
                String.format("Expect valid number of lines in %d line in %s file.", currentLine, filePath), cause);
    }

    /**
     * Create IllegalMessageFormatException with special message for more lines expected error
     * @param filePath final String filePath, final int currentLine
     * @param currentLine current line in file with error
     * @return  initialized IllegalMessageFormatException object
     */
    public static IllegalMessageFormatException expectMoreLines(final String filePath, final int currentLine){
        return new IllegalMessageFormatException(
                String.format("Expect more lines in message. Stop on %d line in %s file.", currentLine, filePath));
    }
}
