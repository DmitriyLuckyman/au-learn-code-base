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
public class TreePrinter extends BufferedWriter implements FSStructureElementHandler {
    private int leftPadding = 0;
    private Deque<Integer> levelPadding = new LinkedList<Integer>();

    public TreePrinter(Writer out) {
        super(out);
    }

    /**
     * Print the element of file system structure tree
     * @param file file handler
     * @param level nesting level relative to the root of traversing
     * @throws IOException
     */
    public void handleElement(File file, int level) throws IOException {
        calculatePadding(file, level < 0 ? 0 : level);
        final StringBuilder builder = new StringBuilder();
        if( leftPadding != 0 ){
            final char[] padding = new char[leftPadding];
            Arrays.fill(padding, ' ');
            builder.append(padding);
            builder.append("|_");
        }
        builder.append(file.getName());
        if(FileSystemWalker.isAccessDenied(file)){
            builder.append("( access denied )");
        }
        write(builder.toString());
        newLine();
        flush();
    }

    private void calculatePadding(File file, int level) {
        if(this.levelPadding.size() > level){
            while(this.levelPadding.size() != level){
                final Integer padding = this.levelPadding.pop();
                if(padding != null){
                    this.leftPadding -= padding;
                }
            }
        } else if(this.levelPadding.size() < level) {
            final int rootPadding = level > 1 ? 2 : 0;
            final int padding = file.getParentFile().getName().length() + rootPadding;
            this.levelPadding.push(padding);
            leftPadding += padding;
        }
    }

    @Override
    public void close() {
        try {
            super.close();
        } catch (IOException e) {
            System.err.println("Strange exception while close writer : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
