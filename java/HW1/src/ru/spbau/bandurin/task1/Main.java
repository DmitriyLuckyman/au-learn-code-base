package ru.spbau.bandurin.task1;

import java.io.IOException;

/**
 * Compress messages from input file to output stream(file or console)
 * @author Dmitriy Bandurin
 */
public class Main {

    /**
     *
     * @param args first parameter of args - input file path,
     *             second parameter of args - output file path(if not exist than messages writes to System.out).
     */
    public static void main(String[] args) {
        FileMessageReader reader = null;
        MessageWriter writer = null;
        try{
            reader = new FileMessageReader(args[0]);

            if(args.length == 2){
                writer = new CompressingMessageWriter(new FileMessageWriter(args[1]));
            } else {
                writer = new CompressingMessageWriter(new ConsoleMessageWriter());
            }

            Message message;
            while((message = reader.readMessage()) != null){
                writer.writeMessage(message);
            }
        } catch (IllegalMessageFormatException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("Strange exception : " + e.getMessage());
            e.printStackTrace();
        } finally {
            if(reader != null){
                reader.close();
            }
            if(writer != null){
                writer.close();
            }
        }
    }
}
