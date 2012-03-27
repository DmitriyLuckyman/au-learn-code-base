package ru.spbau.bandurin.task2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrap a list of handlers in one handler
 * @author Dmitriy Bandurin
 */
public class ElementHandlerChainWrapper implements FSStructureElementHandler {
    private List<FSStructureElementHandler> handlers = new ArrayList<FSStructureElementHandler>();

    /**
     * Call to {@link FSStructureElementHandler#handleElement(java.io.File, int) handleElement} method of each handler in chain
     * @param file file which path should to print
     * @param level nesting level relative to the root of traversing
     * @throws IOException
     */
    public void handleElement(File file, int level) throws IOException {
        for (FSStructureElementHandler handler : handlers) {
            handler.handleElement(file, level);
        }
    }

    /**
     * Call to {@link FSStructureElementHandler#close } method of each handler in chain
     */
    public void close() {
        for (FSStructureElementHandler handler : handlers) {
            handler.close();
        }
        this.clear();
    }

    /**
     * Add new handler to chain.
     * @param handler handler to add
     */
    public void addHandler(FSStructureElementHandler handler){
        handlers.add(handler);
    }

    /**
     * Clear handler chain
     */
    public void clear(){
        handlers.clear();
    }
}
