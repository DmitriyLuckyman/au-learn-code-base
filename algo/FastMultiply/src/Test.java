import java.io.*;
import java.util.Random;

/**
 * @author Dmitriy Bandurin
 *         Date: 16.04.13
 */
public class Test {

    public static void main(String[] args) throws IOException {
        File out = new File("in2");
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
        int n = 100000;
        wr.write('1');
        wr.newLine();
        Random r = new Random();
        for(int k =0; k < 2;k++){
            for(int i = 0; i < n; i++){
                wr.append((char) ('0' + r.nextInt(9)));
            }
            wr.append(' ');
        }
        wr.close();
    }
}
