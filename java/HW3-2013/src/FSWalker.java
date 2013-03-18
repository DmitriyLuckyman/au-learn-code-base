package ru.spbau.bandurin.task3;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;

/**
 * File system walker
 * @author Dmitriy Bandurin
 */
public class FSWalker {
    private FSWalkerEvents eventsHandler;
    private Comparator<File> fileComparator;
    private FileFilter fileFilter;

    /**
     * Create new FsWalker with specified event handler file comparator and file Filter.
     * @param eventsHandler Handler for events occurred while traversing
     * @param filter pattern to exclude files from traverse. Filter use find semantic (may be null)
     * @param includeSubDirs traverse subdirectories also
     * @param fileComparator comparator for file traverse order. If null used default order for traverse
     */
    public FSWalker(final FSWalkerEvents eventsHandler, final Pattern filter, final boolean includeSubDirs, final Comparator<File> fileComparator) {
        this.eventsHandler = eventsHandler;
        this.fileFilter = getFilter(filter, includeSubDirs);
        this.fileComparator = fileComparator;
    }

    /**
     * Traverse file system structure.
     * @param fromFile start point to traverse file system.
     * @throws java.io.IOException if any error occurred while walk through filesystem
     */
    public void walkFrom(final File fromFile)
            throws IOException {
        eventsHandler.startTraverse(fromFile);

        if (!isAccessDenied(fromFile) && fileFilter.accept(fromFile)) {
            traverse(fromFile, 1, 1);
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

    private void traverse(final File file, int totalCountOfFiles, int currentPosition) throws IOException {
        if(isAccessDenied(file)){
            eventsHandler.accessDeniedElement(file);
        } else {
            eventsHandler.handleElement(file);
            if (file.isDirectory()) {
                eventsHandler.steppedInto(file, totalCountOfFiles, currentPosition);
                final File[] files = file.listFiles(fileFilter);
                if (files != null) {
                    if (fileComparator != null) {
                        Arrays.sort(files, fileComparator);
                    }
                    int filesInDirectory = 0;
                    for (File subFile : files) {
                        traverse(subFile, files.length, ++filesInDirectory);
                    }
                } else {
                    eventsHandler.error(file, "Can't get list of files");
                }
                eventsHandler.steppedOut(file, totalCountOfFiles, currentPosition);
            }
        }
    }

    private FileFilter getFilter(final Pattern filter, final boolean includeSubDirs) {
        return new FileFilter() {
            public boolean accept(final File pathName) {
                boolean result = pathName.isDirectory() && includeSubDirs || pathName.isFile();
                if (result && filter != null) {
                    result = !filter.matcher(pathName.getName()).find();
                }
                return result;
            }
        };
    }
}