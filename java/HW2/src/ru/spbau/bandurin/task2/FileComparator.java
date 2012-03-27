package ru.spbau.bandurin.task2;

import java.io.File;
import java.util.Comparator;

/**
 * Custom file comparator
 * @author Dmitriy Bandurin
 */
public class FileComparator implements Comparator<File> {

    /**
     * Compare two files.
     * @param o1 first compared file
     * @param o2 second compared file
     * @return if arguments have some type(directory or file) then object compared as is.
     * if first argument is directory then -1, otherwise 1
     */
    public int compare(File o1, File o2) {
        if (o1.isDirectory() && o2.isDirectory() || o1.isFile() && o2.isFile()) {
            return o1.getName().compareTo(o2.getName());
        }
        return o1.isDirectory() ? -1 : 1;
    }
}
