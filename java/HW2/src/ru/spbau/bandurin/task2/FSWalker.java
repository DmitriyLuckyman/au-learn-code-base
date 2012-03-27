package ru.spbau.bandurin.task2;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;

/**
 * File system walker
 * @author Dmitriy Bandurin
 */
public class FSWalker {
    private FSWalkerEvents eventsHandler;
    private Pattern filter;
    private Comparator<File> fileComparator;
    private boolean includeSubDirs;

    /**
     * @param eventsHandler Handler for events occurred while traversing
     * @param filter pattern to exclude files from traverse. Filter use find semantic (may be null)
     * @param fileComparator comparator for file traverse order. If null use default order for traverse
     * @param includeSubDirs traverse subdirectories also
     */
    public FSWalker(FSWalkerEvents eventsHandler, Pattern filter, Comparator<File> fileComparator, boolean includeSubDirs) {
        this.eventsHandler = eventsHandler;
        this.filter = filter;
        this.fileComparator = fileComparator;
        this.includeSubDirs = includeSubDirs;
    }

    /**
     * traverse file system structure.
     * @param fromFile start point to traverse file system.
     * @throws Exception if any error occurred while walk through filesystem
     */
    public void walkFrom(final File fromFile)
            throws Exception {
        eventsHandler.startTraverse(fromFile);

        if (!isAccessDenied(fromFile) && getFilter().accept(fromFile)) {
            traverse(fromFile, true);
        } else {
            eventsHandler.error(fromFile, "Can't access abstract path");
        }

        eventsHandler.endTraverse(fromFile);
    }

    /**
     * This method test file for read access. Method not verify exists of file in file system
     * @param file file to test
     * @return true if file can't be read or SecurityException appears. Otherwise false.
     */
    private boolean isAccessDenied(final File file) {
        boolean accessDenied;
        try {
            accessDenied = !file.canRead();
        } catch (SecurityException e) {
            accessDenied = true;
        }
        return accessDenied;
    }

    private void traverse(final File file, boolean lastDir) throws Exception {
        if(isAccessDenied(file)){
            eventsHandler.accessDeniedElement(file);
        } else {
            eventsHandler.handleElement(file);
            if (file.isDirectory()) {
                eventsHandler.steppedInto(file, lastDir);
                final File[] files = file.listFiles(getFilter());
                if (files != null) {
                    if (fileComparator != null) {
                        Arrays.sort(files, fileComparator);
                    }
                    int filesInDirectory = files.length;
                    for (File subFile : files) {
                        traverse(subFile, --filesInDirectory == 0);
                    }
                } else {
                    eventsHandler.error(file, "Can't get list of files");
                }
                eventsHandler.steppedOut(file, lastDir);
            }
        }
    }

    private FileFilter getFilter() {
        return new FileFilter() {
            public boolean accept(File pathname) {
                boolean result = pathname.isDirectory() && includeSubDirs || pathname.isFile();
                if (result && filter != null) {
                    result = !filter.matcher(pathname.getName()).find();
                }
                return result;
            }
        };
    }
}