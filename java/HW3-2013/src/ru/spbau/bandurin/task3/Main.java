package ru.spbau.bandurin.task3;

import java.io.IOException;
import java.util.Arrays;

/**
 * Compress/decompress directory content.
 *
 * @author Dmitriy Bandurin
 */
public class Main {
    /**
     * Compress/decompress directory content.
     *
     * @param args mixed parameters<br/>
     *
     *<pre>
     *1) CompressOperation directory content <br/>
     *  1 arg - compress (case insensitive operation code)<br/>
     *  2 arg - output file name (if file already exists throw IOException)<br/>
     *  3... args - path to files or directories to compress<br/>
     *2) Restore directory content from archive<br/>
     *  1 arg - decompress (case insensitive operation code)<br/>
     *  2 arg - path to file with archive<br/>
     *</pre>
     */
    public static void main(String[] args) {
        ZipOperation operation = null;
        try {
            if (args.length < 2) {
                throw new IllegalArgumentException("Invalid input parameters. See \"use help\" for more information");
            }
            String operationCode = args[0].toLowerCase();

            if ("compress".equals(operationCode)) {
                if (args.length < 3) {
                    throw new IllegalArgumentException("Invalid input parameters for compress operation. See \"use help\" for more information");
                }
                operation = new CompressOperation(args[1], Arrays.copyOfRange(args, 2, args.length));
            } else if ("decompress".equals(operationCode)) {
                if (args.length != 2) {
                    throw new IllegalArgumentException("Invalid input parameters for decompress operation. See \"use help\" for more information");
                }
                operation = new DecompressOperation(args[1], System.getProperty("user.dir"));
            } else {
                throw new IllegalArgumentException("Incorrect code operation. See \"use help\" for more information");
            }
            operation.perform();
        } catch (IllegalArgumentException e){
           printUseHelp(e.getMessage());
        } catch (IOException e) {
            System.err.println("Error while perform compress/decompress operation: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (operation != null) {
                try {
                    operation.close();
                } catch (IOException e) {
                    System.err.println("Strange error while free resources after operation : " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Print help for program use.
     * @param message detailed error message for incorrect use.
     */
    private static void printUseHelp(String message) {
        System.out.println(message + "\nUse help:\n" +
                "Program can run with following code operations and parameters:\n" +
                "1) 1 arg - compress (operation code)\n" +
                "   2 arg - output file name\n" +
                "   3... args - path to files or directories to compress\n" +
                "2) 1 arg - decompress (operation code)\n" +
                "   2 arg - path to file with archive\n");
    }
}
