package ru.spbau.bandurin.task3;

import java.io.*;
import java.util.zip.ZipException;

/**
 * FSWalkerEvents handler to archive fs tree into one output stream.
 * @author Dmitriy Bandurin
 * Date: 03.03.13
 */
public class CompressorWalkerEventsHandler extends FSWalkerEventsAbstract {
    private final DataOutputStream dataOutputStream;
    private String absolutePrefix;

    /**
     * Create new CompressorWalkerEventsHandler with specified underlying output stream
     * @param out output stream to write archived data
     * @throws java.io.IOException if any I/O error occurred
     */
    public CompressorWalkerEventsHandler(OutputStream out) throws IOException {
        dataOutputStream = new DataOutputStream(out);
    }

    /**
     *
     * Fire when start traversing
     * @param rootFile root path for traversing
     * @throws java.io.IOException if any I/O error occurred
     */
    @Override
    public void startTraverse(final File rootFile) throws IOException {
        File parentFile = rootFile.getAbsoluteFile().getParentFile();
        this.absolutePrefix = parentFile != null ? parentFile.getAbsolutePath() : "";
    }

    /**
     * Write error message to System.err output stream
     * @param file processed file when error detected
     * @param message detailed error message
     * @throws IOException if any I/O error occurred
     */
    @Override
    public void error(final File file, final String message) throws IOException {
        System.err.printf("Error while compress file with path \"%s\" with error message : %s%n", file.getAbsolutePath(), message);
    }

    /**
     * Write error message to System.err output stream
     * @param file file which access denied detected
     * @throws IOException if any I/O error occurred
     */
    @Override
    public void accessDeniedElement(final File file) throws IOException {
        System.err.printf("File Access denied on path \"%s\"%n", file.getAbsolutePath());
    }

    /**
     * Write relative file path size and content to archive
     * @param file file to process
     * @throws IOException if any I/O error occurred while perform operation
     * @throws ZipException if size of file are change while zip content
     */
    @Override
    public void handleElement(final File file) throws IOException {
        if(file.isFile()){
            final String relativePath = file.getAbsolutePath().replace(this.absolutePrefix, "");
            System.out.printf("> %s <added>%n", relativePath);
            final long fileSize = file.length();
            final FileInputStream inputStream = new FileInputStream(file);
            dataOutputStream.writeUTF(relativePath);
            dataOutputStream.writeLong(fileSize);
            final byte[] buffer = new byte[1024];
            int length;
            int realFileSize = 0;
            while((length = inputStream.read(buffer)) != -1){
                dataOutputStream.write(buffer, 0, length);
                realFileSize += length;
                if(realFileSize > fileSize){
                    throw new ZipException("File size increased while compressing operation perform : " + file.getAbsolutePath());
                }
            }
            if(realFileSize != fileSize){
                throw new ZipException("File size changed while compress operation performs: " + file.getAbsolutePath());
            }
        }
    }

    /**
     * Free output stream resources
     * @throws IOException if any I/O error occurred while close underlying stream
     */
    public void close() throws IOException {
        dataOutputStream.close();
    }
}
