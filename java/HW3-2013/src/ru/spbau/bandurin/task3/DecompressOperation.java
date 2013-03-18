package ru.spbau.bandurin.task3;

import java.io.*;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/**
 * Operation to restore directory structure from archived file.
 * @author Dmitriy Bandurin
 * Date: 05.03.13
 */
public class DecompressOperation implements ZipOperation  {
    private final DataInputStream inputStream;
    private final File rootDir;

    /**
     * Create new DecompressOperation for
     * @param archivePath path to archive file
     */
    public DecompressOperation(final String archivePath, String outputDir) throws FileNotFoundException {
        this.inputStream = new DataInputStream(new InflaterInputStream(
                new FileInputStream(new File(archivePath)), new Inflater(true)));
        this.rootDir = new File(outputDir);
    }

    /**
     * Restore directory structure from archived file
     * @throws IOException if any I/O error occurred
     */
    public void perform() throws IOException {
        boolean eof = false;
        while (!eof){
            final File path;
            try {
                 path = new File(rootDir, inputStream.readUTF());
            } catch (EOFException e) {
                eof = true;
                System.out.println("eof");
                continue;
            }
            System.out.printf("> %s <unpacked>%n", path);
            File parentDir = path.getParentFile();
            if(!parentDir.exists() && !parentDir.mkdirs()){
                throw new IOException("Can't create directories for new file :" + parentDir.getAbsolutePath());
            }
            if(!path.createNewFile()){
                throw new IOException("Can't create new file or file already exist:" + path.getAbsolutePath());
            }
            restoreFile(path);
        }
    }

    /**
     * Restore current decompress file data to specified path
     * @param path path to restore file content
     * @throws IOException if any I/O error occurred
     */
    private void restoreFile(File path) throws IOException {
        FileOutputStream outputStream = null;
        try {
            final long length = inputStream.readLong();
            final byte[] readBuffer = new byte[1024];
            outputStream = new FileOutputStream(path);
            long needToReadBytes = length;
            while(needToReadBytes != 0){
                int shouldRead = needToReadBytes < readBuffer.length ? (int) needToReadBytes : readBuffer.length;
                inputStream.readFully(readBuffer, 0, shouldRead);
                outputStream.write(readBuffer, 0, shouldRead);
                needToReadBytes -= shouldRead;
            }
        } finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    System.err.println("Strange exception while close outputStream.");
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Free used resources
     * @throws IOException if any I/O error occurred
     */
    public void close() throws IOException {
        inputStream.close();
    }
}
