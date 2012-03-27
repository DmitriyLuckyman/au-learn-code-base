package ru.spbau.bandurin.task2;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.regex.Pattern;

/**
 * Example of extended use.
 * Traverse the FS structure and print FS Tree.
 * Also calculate total number of files and directories.
 * @author Dmitriy Bandurin
 */
public class ExtendedMain {

    public static void main(String[] args) {
        ElementHandlerChainWrapper handlers = null;
        try {
            handlers = new ElementHandlerChainWrapper();
            final TreePrinter treePrinter = new TreePrinter(new OutputStreamWriter(System.out));
            handlers.addHandler(treePrinter);
            final CalculateSummaryHandler summaryHandler = new CalculateSummaryHandler();
            handlers.addHandler(summaryHandler);

            FileSystemWalker.traverse(new File(args[0]), handlers, Pattern.compile("^[.]{1,2}$"),
                    new FileComparator(), true);

            System.out.println(new StringBuilder()
                    .append("Total Files : ").append(summaryHandler.getTotalFiles())
                    .append(" Total Directories : ").append(summaryHandler.getTotalDirectories()).toString());

        } catch (IOException e) {
            System.err.println("Strange error while write content : " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (handlers != null) {
                handlers.close();
            }
        }
    }
}

