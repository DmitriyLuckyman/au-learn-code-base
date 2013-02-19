package ru.spbau.bandurin.task1;

import java.io.IOException;
import java.io.Writer;

/**
 * Abstract  message writer for {@link MessageWriter} which directly works with {@link java.io.Writer}.
 * @author Dmitriy Bandurin
 */
public abstract class AbstractMessageWriter implements MessageWriter {
    private Writer writer;

    /**
     * Set writer for message write
     * @param writer writer
     */
    protected void initWriter(final Writer writer) {
        this.writer = writer;
    }

    /**
     * Write single line to writer
     * @param line line to write
     * @throws IOException if any exception occurred while writing content
     */
    protected void writeLine(final String line) throws IOException {
        writer.write(line);
        writer.write("\n");
        writer.flush();
    }

    /**
     * Free used resources
     * @throws IOException if any exception occurred while close used resource
     */
    public void close() throws IOException {
        if(writer != null){
            writer.close();
        }
    }
}
