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
    public static void main(String[] args) {
        FSStructureElementHandler writer = null;
        try{
            writer = new TreePrinter(new OutputStreamWriter(System.out));
            FileSystemWalker.traverse(new File(args[0]), writer, Pattern.compile("^[.]{1,2}$"),
                    new FileComparator(), true);
        } catch (IOException e){
            System.err.println("Strange error while write content : " + e.getMessage());
            e.printStackTrace();
        } finally {
            if(writer != null){
                writer.close();
            }
        }
    }
}
