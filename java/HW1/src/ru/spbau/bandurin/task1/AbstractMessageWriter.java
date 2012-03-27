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
     * set writer for message write
     * @param writer writer
     */
    protected void initWriter(Writer writer) {
        this.writer = writer;
    }

    /**
     * write single line to writer
     * @param line line to write
     * @throws IOException if any exception occurred while writing content
     */
    public void writeLine(String line) throws IOException {
        writer.write(line);
        writer.write("\n");
        writer.flush();
    }

    /**
     * Free used resources
     */
    public void close(){
        if(writer != null){
            try {
                writer.close();
            } catch (IOException e) {
                System.err.println(new StringBuilder().append("Can't close Writer : ")
                        .append(e.getMessage()).toString());
                e.printStackTrace();
            }
        }
    }
}
