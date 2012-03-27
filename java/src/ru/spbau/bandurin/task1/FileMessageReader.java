package ru.spbau.bandurin.task1;

import java.io.*;

/**
 * Read messages from file.
 * Use close method to free resources.
 * @author : Dmitriy Bandurin
 */
public class FileMessageReader {
    private BufferedReader bufferedReader;
    private String filePath;
    private int currentLine;

    /**
     *
     * @param filePath path to file with messages
     */
    public FileMessageReader(String filePath) {
        File file = new File(filePath);
        if(file.exists() && file.canRead()){
            try {
                this.bufferedReader = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Can't open file : " + filePath, e);
            }
            this.filePath = filePath;
        } else {
            throw new RuntimeException("Can't open or read file : " + filePath);
        }
    }

    /**
     *
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
                throw new IllegalMessageFormatException(new StringBuilder()
                        .append("Expect valid number of lines in ")
                        .append(currentLine).append(" line in ").append(filePath).append(" file.").toString(), e);
            }
            message = new Message();
            for(int lineNumber = 0; lineNumber < linesInMessage; lineNumber++){
                currentLine++;
                final String line = bufferedReader.readLine();
                if(line == null){
                    throw new IllegalMessageFormatException(new StringBuilder()
                            .append("Expect more lines in message. Stop on ")
                            .append(currentLine).append(" line in ").append(filePath).append(" file.").toString());
                }
                message.append(line);
            }
        }
        return message;
    }

    /**
     * Free used resources.
     */
    public void close(){
        if (bufferedReader != null){
            try {
                bufferedReader.close();
            } catch (IOException e) {
                System.err.println(new StringBuilder().append("Can't close bufferedReader : ")
                        .append(e.getMessage()).append(" for file ").append(filePath).toString());
                e.printStackTrace();
            }
        }
    }
}
