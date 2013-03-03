package ru.spbau.bandurin.task2;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.regex.Pattern;

/**
 * Traverse the FS structure and print FS Tree.
 * @author Dmitriy Bandurin
 */
public class Main {
    /**
     * Print directory content as files tree
     * @param args path to directory fro print
     */
    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("Expect one input parameter(directory path).");
            return;
        }

        TreePrinter writer = null;
        try{
            writer = new TreePrinter(new OutputStreamWriter(System.out));
            FSWalker walker = new FSWalker(writer, Pattern.compile("^[.]{1,2}"),
                    true, new FileComparator());
            walker.walkFrom(new File(args[0]));
        } catch (IOException e){
            System.err.println("Strange error while traverse : " + e.getMessage());
            e.printStackTrace();
        } finally {
            if(writer != null){
                try {
                    writer.close();
                } catch (Exception e) {
                    System.err.println("Strange error while close writer : " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
