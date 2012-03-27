package ru.spbau.bandurin.task2;

import java.io.File;

/**
 * Interface of events from file system traverse engine
 * @author Dmitriy Bandurin
 */
public interface FSWalkerEvents {

    /**
     * Fire when error occurred
     * @param file current processed file
     * @param message error message
     * @throws Exception if any exception occurred
     */
    void error(File file, String message) throws Exception;

    /**
     * Fire when start traversing
     * @param rootFile root path for traversing
     * @throws Exception if any exception occurred
     */
    void startTraverse(File rootFile) throws Exception;

    /**
     * Fire when end traversing
     * @param fromFile root path for traversing
     * @throws Exception if any exception occurred
     */
    void endTraverse(File fromFile) throws Exception;

    /**
     * Fire when start traverse new directory
     * @param dir stepped into dir
     * @param lastDir true if this dir is last dir in current traverse order
     * @throws Exception if any exception occurred
     */
    void steppedInto(File dir, boolean lastDir) throws Exception;

    /**
     * Fire when finish traverse directory
     * @param dir stepped out dir
     * @param lastDir  true if this dir is last dir in current traverse order
     * @throws Exception if any exception occurred
     */
    void steppedOut( File dir, boolean lastDir) throws Exception;

    /**
     * Fire when find file with access rights
     * @param file access denied file
     * @throws Exception if any exception occurred
     */
    void accessDeniedElement(File file) throws Exception;

    /**
     * Fire when start process directory or file
     * @param file file to handle
     * @throws Exception if any exception occurred
     */
    void handleElement(File file) throws Exception;
}
