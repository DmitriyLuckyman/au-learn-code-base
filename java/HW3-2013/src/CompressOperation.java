package ru.spbau.bandurin.task3;

import java.io.IOException;

/**
 * User: dbandurin
 * Date: 06.03.13
 */
public class CompressOperation implements ZipOperation {
    private String archiveFile;
    private String[] filesToCompress;

    public CompressOperation(final String archiveFile, final String[] filesToCompress) {
        this.archiveFile = archiveFile;
        this.filesToCompress = filesToCompress;
    }

    public void perform() throws IOException {

    }

    public void close() throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
