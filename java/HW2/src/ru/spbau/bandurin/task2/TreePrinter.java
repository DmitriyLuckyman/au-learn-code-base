package ru.spbau.bandurin.task2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

/**
 * Print file structure as tree
 * @author Dmitriy Bandurin
 */
public class TreePrinter implements FSWalkerEvents {
    private BufferedWriter writer;
    private String leftPad;
    private File rootFile;
    private int errorsCount = 0;


    /**
     * Create TreePrinter for print tree structure in givven output
     * @param out writer for write are tree starcture
     */
    public TreePrinter(Writer out) {
        writer = new BufferedWriter(out);
    }

    /**
     * Print the element of file system structure tree
     * @param file file handler
     * @throws IOException if error occurred while write data to writer
     */
    public void handleElement(File file) throws IOException {
        printElement(file, false);
    }

    /**
     * Print error to output
     * @param file current processed file
     * @param message error message
     * @throws IOException
     */
    public void error(File file, String message) throws IOException {
        writer.write("Error while traverse path: ");
        writer.write(file.getPath());
        writer.newLine();
        writer.write("Message: ");
        writer.write(message);
        writer.newLine();
        writer.flush();
        errorsCount++;
    }

    /**
     * Print traverse path.
     * @param rootFile root path for traversing
     * @throws IOException
     */
    public void startTraverse(File rootFile) throws IOException {
        leftPad = "";
        errorsCount = 0;
        this.rootFile = rootFile;
        writer.write("Tree for path : " + rootFile.getPath());
        writer.newLine();
        writer.flush();
    }

    /**
     * Print finish message
     * @param fromFile root path for traversing
     * @throws IOException
     */
    public void endTraverse(File fromFile) throws IOException {
        writer.write("Tree print complete");

        if(errorsCount > 0){
            writer.write(" with ");
            writer.write("" + errorsCount);
            writer.write(errorsCount == 1 ? " error." : " errors." );
        } else {
            writer.write(".");
        }

        writer.newLine();
        writer.flush();
        this.rootFile = null;
    }

    /**
     * Update padding for print
     * @param dir stepped into dir
     * @param lastDir true if this dir is last dir in current traverse order
     */
    public void steppedInto(File dir, boolean lastDir) {
        int paddingSize = dir.getName().length();
        if (!rootFile.equals(dir)) {
            paddingSize++;
            if (!lastDir) {
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
     * @param lastDir  true if this dir is last dir in current traverse order
     */
    public void steppedOut(File dir, boolean lastDir) {
        int padding = dir.getName().length();
        if(!rootFile.equals(dir)){
            padding+=2;
        }
        leftPad = leftPad.substring(0, leftPad.length() - padding );
    }

    /**
     * Print access denied file name and special label
     * @param file access denied file
     * @throws Exception
     */
    public void accessDeniedElement(File file) throws Exception {
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
        writer.newLine();
        writer.flush();
    }
}
