package ru.spbau.bandurin.task1;

import java.io.*;

/**
 * Read messages from file.
 * Use close method to free resources.
 * @author : Dmitriy Bandurin
 */
public class FileMessageReader {
    private final BufferedReader bufferedReader;
    private final String filePath;
    private int currentLine;

    /**
     * Create new FileMessageReader to read messages from given file
     * @param filePath path to file with messages
     * @throws FileNotFoundException if given path not associated with a file.
     */
    public FileMessageReader(final String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        this.bufferedReader = new BufferedReader(new FileReader(new File(filePath)));
    }

    /**
     * Read message from a file to Message container.
     * @return Message read from a file or null if no more message available.
     * @throws IOException if any exception occurred while reading content from file
     * @throws IllegalMessageFormatException if message has an incorrect format.
     */
    public Message readMessage() throws IOException, IllegalMessageFormatException {
        Message message = null;
        final String count = bufferedReader.readLine();
        if(count != null){
            int linesInMessage;
            try {
                currentLine++;
                linesInMessage = Integer.parseInt(count);
            } catch (NumberFormatException e) {
                throw IllegalMessageFormatException.readLineCountProblem(filePath, currentLine, e);
            }
            message = new Message();
            for(int lineNumber = 0; lineNumber < linesInMessage; lineNumber++){
                currentLine++;
                final String line = bufferedReader.readLine();
                if(line == null){
                    throw IllegalMessageFormatException.expectMoreLines(filePath, currentLine);
                }
                message.append(line);
            }
        }
        return message;
    }

    /**
     * Free used resources.
     * @throws IOException if any exception occurred while writing content
     */
    public void close() throws IOException {
        bufferedReader.close();
    }
}
