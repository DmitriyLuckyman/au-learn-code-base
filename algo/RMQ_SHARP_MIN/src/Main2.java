import java.io.*;
import java.util.Random;


/**
 * @author Dmitriy Bandurin
 *         Date: 02.04.13
 */
public class Main2 {
    public static void main(String[] args) throws IOException {
        Random r = new Random();
        int n = (int) 1e6;
        int q = (int) 1e6;
        File out = new File("in7");
        out.createNewFile();
        BufferedWriter outputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
        outputStream.write(String.format("%d %d", n, q));
        outputStream.newLine();
        int maxINT = (int) 10e9;
        for(int i = 0; i < n; i++){
            outputStream.write(String.format("%d ", r.nextInt(maxINT)));
        }
        outputStream.newLine();
        for(int i = 0; i < q; i++){
            if(r.nextInt(10) % 3 == 0){
                int i1 = r.nextInt(n) + 1;
                int rl  = i1 + r.nextInt(100);
                outputStream.write(String.format("add %d %d %d", i1, rl < n ? rl : n, r.nextInt(maxINT)));
            } else {
                int i1 = r.nextInt(n) + 1;
                outputStream.write(String.format("max %d %d", i1, i1 + 20 < n ? i1 + 20 : r.nextInt(n - i1 + 1)));
            }
            outputStream.newLine();
        }
        outputStream.close();
    }

}

