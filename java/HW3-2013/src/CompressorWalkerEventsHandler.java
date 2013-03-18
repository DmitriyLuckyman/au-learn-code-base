package ru.spbau.bandurin.task3;

import java.io.*;

/**
 * User: dbandurin
 * Date: 03.03.13
 */
public class CompressorWalkerEventsHandler extends FSWalkerEventsAbstract {
    private final DataOutputStream dataOutputStream;
    private String rootPath;

    public CompressorWalkerEventsHandler(OutputStream out) {
        dataOutputStream = new DataOutputStream(out);
    }

    @Override
    public void startTraverse(File rootFile) throws IOException {
        this.rootPath = rootFile.getAbsolutePath();
    }

    @Override
    public void error(File file, String message) throws IOException {
        System.err.printf("Error while compress file with path \"%s\" with error message : %s%n", file.getAbsolutePath(), message);
    }

    @Override
    public void accessDeniedElement(File file) throws IOException {
        System.err.printf("File Access denied on path \"%s\"%n", file.getAbsolutePath());
    }

    @Override
    public void handleElement(File file) throws IOException {
        if(file.isFile()){
            String relativePath = file.getAbsolutePath().replace(this.rootPath, "");
            long fileSize = file.length();
            System.out.printf("Compress file = %s:%d%n", relativePath, fileSize);
            FileInputStream inputStream = new FileInputStream(file);
            dataOutputStream.writeUTF(relativePath);
            dataOutputStream.writeLong(fileSize);
            byte[] buffer = new byte[1024];
            int length;
            int realFileSize = 0;
            while((length = inputStream.read(buffer)) != -1){
                dataOutputStream.write(buffer, 0, length);
                realFileSize += length;
            }
            if(realFileSize != fileSize){
                throw new RuntimeException("Incorrect file size for : " + file.getAbsolutePath());
            }
        }
    }


    public void close() throws IOException {
        dataOutputStream.close();
    }
}
