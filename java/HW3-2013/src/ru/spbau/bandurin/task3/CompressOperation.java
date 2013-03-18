package ru.spbau.bandurin.task3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

/**
 * Compress directory structures to one zipped file
 * @author Dmitriy Bandurin
 * Date: 05.03.13
 */
public class CompressOperation implements ZipOperation {
    private final String[] filesToCompress;
    private final CompressorWalkerEventsHandler eventsHandler;
    private final FSWalker walker;

    /**
     * Create new instance of CompressOperation with specified output file and list of files to compress.
     * @param archiveFilePath archive file path
     * @param filesToCompress list of input files and directories to compress
     * @throws IOException if any I/O error occurred
     */
    public CompressOperation(final String archiveFilePath, final String[] filesToCompress) throws IOException {
        this.filesToCompress = filesToCompress;
        File archiveFile = new File(archiveFilePath);
        if(!archiveFile.createNewFile()){
            throw new IOException("Can't create new file or file already exists : " + archiveFile.getAbsolutePath());
        }
        eventsHandler = new CompressorWalkerEventsHandler(new DeflaterOutputStream( new FileOutputStream(archiveFile),
                new Deflater(Deflater.DEFAULT_COMPRESSION, true)));
        walker = new FSWalker(eventsHandler, null, true, new FileComparator());
    }

    /**
     * Compress directory structure to output file
     * @throws IOException if any I/O error occurred
     */
    public void perform() throws IOException {
        System.out.println("Start create archive");
        for (String path : filesToCompress) {
            walker.walkFrom(new File(path));
        }
        System.out.println("Archive created.");
    }

    /**
     * Free used resources
     * @throws IOException if any I/O error occurred
     */
    public void close() throws IOException {
        eventsHandler.close();
    }
}
