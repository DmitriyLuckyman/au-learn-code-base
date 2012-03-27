package ru.spbau.bandurin.task2;

import java.io.File;
import java.io.IOException;

/**
 * Calculate summary while traversing.
 * @author Dmitriy Bandurin
 */
public class CalculateSummaryHandler implements FSStructureElementHandler {
    private int totalFiles = 0;
    private int totalDirectories = 0;

    public void handleElement(File file, int level) throws IOException {
        totalFiles++;
        if(!FileSystemWalker.isAccessDenied(file)){
            if(file.isDirectory()){
                totalDirectories++;
            }
        }
    }

    public void close() {
        totalFiles = 0;
        totalDirectories = 0;
    }

    /**
     *
     * @return total count of files
     */
    public int getTotalFiles() {
        return totalFiles;
    }

    /**
     *
     * @return total count of directories
     */
    public int getTotalDirectories() {
        return totalDirectories;
    }
}
