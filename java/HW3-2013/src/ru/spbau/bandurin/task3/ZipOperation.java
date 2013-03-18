package ru.spbau.bandurin.task3;

import java.io.IOException;

/**
 * Common interface for zip operations
 * @author Dmitriy Bandurin
 * Date: 05.03.13
 */
public interface ZipOperation {
    /**
     * Perform zip operation
     * @throws IOException
     */
    public void perform() throws IOException;

    /**
     * Free used resources
     * @throws IOException
     */
    public void close() throws IOException;

}
