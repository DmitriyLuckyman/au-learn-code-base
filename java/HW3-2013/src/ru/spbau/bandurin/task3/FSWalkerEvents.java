package ru.spbau.bandurin.task3;

import java.io.File;
import java.io.IOException;

/**
 * Interface of events from file system traverse engine
 * @author Dmitriy Bandurin
 */
public interface FSWalkerEvents {

    /**
     * Fire when error occurred
     * @param file current processed file
     * @param message error message
     * @throws java.io.IOException if any io problems occurred
     */
    void error(File file, String message) throws IOException;

    /**
     * Fire when start traversing
     * @param rootFile root path for traversing
     * @throws java.io.IOException if any io problems occurred
     */
    void startTraverse(File rootFile) throws IOException;

    /**
     * Fire when end traversing
     * @param fromFile root path for traversing
     * @throws java.io.IOException if any io problems occurred
     */
    void endTraverse(File fromFile) throws IOException;

    /**
     * Fire when start traverse new directory
     *
     * @param dir stepped into dir
     * @param totalCountOfFiles Total count of files in parent directory
     * @param currentPosition current position of traversed element in traverse order in parent directory
     * @throws java.io.IOException if any io problems occurred
     */
    void steppedInto(File dir, Integer totalCountOfFiles, Integer currentPosition) throws IOException;

    /**
     * Fire when finish traverse directory
     * @param dir stepped out dir
     * @param totalCountOfFiles Total count of files in parent directory
     * @param currentPosition current position of traversed element in traverse order in parent directory *
     * @throws java.io.IOException if any io problems occurred
     */
    void steppedOut(File dir, Integer totalCountOfFiles, Integer currentPosition) throws IOException;

    /**
     * Fire when find file with access rights
     * @param file access denied file
     * @throws java.io.IOException if any io problems occurred
     */
    void accessDeniedElement(File file) throws IOException;

    /**
     * Fire when start process directory or file
     * @param file file to handle
     * @throws java.io.IOException if any io problems occurred
     */
    void handleElement(File file) throws IOException;
}
