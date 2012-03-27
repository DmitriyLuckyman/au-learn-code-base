package ru.spbau.bandurin.task2;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;

/**
 * @author Dmitriy Bandurin
 */
public class FileSystemWalker {

    private static final int ROOT_LEVEL = 0;
    private static final int LEVEL_INCREASE_STEP = 1;

    /**
     * traverse file system structure.
     * @param fromFile start point to traverse file system.
     * @param elementHandler elementHandler for print the traversed structure
     * @param filter pattern to exclude files from traverse (may be null)
     * @param fileComparator comparator for file traverse order (may be null)
     * @param includeSubDirs traverse subdirectories also
     * @throws java.io.IOException if any error occurred while traverse filesystem
     */
    public static void traverse(final File fromFile,final FSStructureElementHandler elementHandler,
                                final Pattern filter,final Comparator<File> fileComparator,
                                final boolean includeSubDirs )
            throws IOException {
        if(testFilter(fromFile.getName(), filter)){
            if(fromFile.exists()){
                traverse(fromFile, elementHandler, filter, fileComparator, ROOT_LEVEL, includeSubDirs);
            } else {
                System.err.println("File not found : " + fromFile.getPath());
            }
        }
    }

    /**
     * This method test file for read access. Method not verify exists of file in file system
     * @param file file to test
     * @return true if file can't be read or SecurityException appears. Otherwise false.
     */
    public static boolean isAccessDenied(final File file) {
        boolean accessDenied;
        try {
            accessDenied = !file.canRead();
        } catch (SecurityException e) {
            accessDenied = true;
        }
        return accessDenied;
    }

    private static void traverse(final File file, final FSStructureElementHandler elementHandler,
                          final Pattern filter,final Comparator<File> fileComparator,
                          final int level, final boolean includeSubDirs) throws IOException{

        elementHandler.handleElement(file, level);

        if (file.isDirectory() && !isAccessDenied(file)) {
            final File[] files = file.listFiles(new FileFilter() {
                public boolean accept(File pathname) {
                    return testFilter(pathname.getName(), filter);
                }
            });
            if (files != null) {
                if (fileComparator != null) {
                    Arrays.sort(files, fileComparator);
                }
                for (File subFile : files) {
                    if (subFile.isDirectory() && includeSubDirs || subFile.isFile()) {
                        traverse(subFile, elementHandler, filter, fileComparator, level + LEVEL_INCREASE_STEP, includeSubDirs);
                    }
                }
            }
        }
    }

    private static boolean testFilter(final String name, final Pattern filter) {
        boolean result = true;
        if (filter != null) {
            result = !filter.matcher(name).find();
        }
        return result;
    }
}