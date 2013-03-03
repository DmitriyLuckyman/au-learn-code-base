package ru.spbau.bandurin.task2;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

/**
 * Print file structure as tree
 * @author Dmitriy Bandurin
 */
public class TreePrinter extends FSWalkerEventsAbstract {
    private Writer writer;
    private String leftPad;
    private File rootFile;
    private int errorsCount = 0;
    private final String lineSeparator;


    /**
     * Create TreePrinter for print tree structure in given output
     * @param out writer for write are tree structure.
     * @throws NullPointerException if out parameter is null.
     */
    public TreePrinter(Writer out) {
        if(out == null){
            throw new NullPointerException("Writer can't be null");
        }
        writer = out;
        lineSeparator = System.getProperty("line.separator");
    }

    /**
     * Print the element of file system structure tree
     * @param file file handler
     * @throws IOException if error occurred while write data to writer
     */
    @Override
    public void handleElement(File file) throws IOException {
        printElement(file, false);
    }

    /**
     * Print error to output
     * @param file current processed file
     * @param message error message
     * @throws IOException if can't write to output writer
     */
    @Override
    public void error(File file, String message) throws IOException {
        writer.write("Error while traverse path: ");
        writer.write(file.getPath());
        writer.write(lineSeparator);
        writer.write("Message: ");
        writer.write(message);
        writer.write(lineSeparator);
        writer.flush();
        errorsCount++;
    }

    /**
     * Print traverse path.
     * @param rootFile root path for traversing
     * @throws IOException if can't write to output writer
     */
    @Override
    public void startTraverse(File rootFile) throws IOException {
        leftPad = "";
        errorsCount = 0;
        this.rootFile = rootFile;
        writer.write("Tree for path : " + rootFile.getPath());
        writer.write(lineSeparator);
        writer.flush();
    }

    /**
     * Print finish message
     * @param fromFile root path for traversing
     * @throws IOException if can't write to output writer
     */
    @Override
    public void endTraverse(File fromFile) throws IOException {
        writer.write("Tree print complete");

        if(errorsCount > 0){
            writer.write(" with ");
            writer.write("" + errorsCount);
            writer.write(errorsCount == 1 ? " error." : " errors." );
        } else {
            writer.write(".");
        }

        writer.write(lineSeparator);
        writer.flush();
        this.rootFile = null;
    }

    /**
     * Update padding for print
     * @param dir stepped into dir
     * @param totalCountOfFiles Total count of files in parent directory
     * @param currentPosition current position of traversed element in traverse order in parent directory
     */
    @Override
    public void steppedInto(File dir, Integer totalCountOfFiles, Integer currentPosition) {
        int paddingSize = dir.getName().length();
        if (!rootFile.equals(dir)) {
            paddingSize++;
            if (currentPosition < totalCountOfFiles) {
                leftPad += "|";
            } else {
                paddingSize++;
            }
        }
        final char[] padding = new char[paddingSize];
        Arrays.fill(padding, ' ');
        leftPad += new String(padding);
    }

    /**
     * Update padding for print
     * @param dir stepped out dir
     * @param totalCountOfFiles Total count of files in parent directory
     * @param currentPosition current position of traversed element in traverse order in parent directory
     */
    @Override
    public void steppedOut(File dir, Integer totalCountOfFiles, Integer currentPosition) {
        int padding = dir.getName().length();
        if(!rootFile.equals(dir)){
            padding+=2;
        }
        leftPad = leftPad.substring(0, leftPad.length() - padding );
    }

    /**
     * Print access denied file name and special label
     * @param file access denied file
     * @throws IOException if can't print element info to output writer
     */
    @Override
    public void accessDeniedElement(File file) throws IOException {
        printElement(file, true);
    }

    /**
     * Free used resources
     * @throws IOException if can't close writer
     */
    public void close() throws IOException {
        writer.close();
    }

    private void printElement(File file, boolean isAccessDenied) throws IOException {
        writer.append(leftPad);
        if(!rootFile.equals(file)){
            writer.append("|_");
        }
        writer.append(file.getName());
        if(isAccessDenied){
            writer.append("( access denied )");
        }
        writer.write(lineSeparator);
        writer.flush();
    }
}
