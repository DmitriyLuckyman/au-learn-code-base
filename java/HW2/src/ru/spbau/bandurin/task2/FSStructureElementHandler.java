package ru.spbau.bandurin.task2;

import java.io.File;
import java.io.IOException;

/**
 * handle file structure element interface
 * @author Dmitriy Bandurin
 */
public interface FSStructureElementHandler {
    /**
     * Handle the file element.
     * @param file file to handle
     * @param level nesting level relative to the root of traversing
     * @throws IOException
     */
    void handleElement(File file, int level) throws IOException;

    /**
     * Free used resources
     */
    void close();
}
