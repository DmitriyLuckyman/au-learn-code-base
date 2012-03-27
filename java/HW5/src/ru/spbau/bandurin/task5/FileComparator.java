package ru.spbau.bandurin.task2;

import java.io.File;
import java.util.Comparator;

/**
 * @author Dmitriy Bandurin
 */
public class FileComparator implements Comparator<File> {
    public int compare(File o1, File o2) {
        if (o1.isDirectory() && o2.isDirectory() || o1.isFile() && o2.isFile()) {
            return o1.getName().compareTo(o2.getName());
        }
        return o1.isDirectory() ? -1 : 1;
    }
}
