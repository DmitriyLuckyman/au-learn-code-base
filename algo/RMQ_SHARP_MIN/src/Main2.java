import java.io.*;
import java.util.Random;


/**
 * @author Dmitriy Bandurin
 *         Date: 02.04.13
 */
public class Main2 {
    public static void main(String[] args) throws IOException {
        Random r = new Random();
        int n = (int) 10e5;
        int q = (int) 10e5;
        File out = new File("in6");
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
                int i1 = r.nextInt(n);
                outputStream.write(String.format("change %d %d", i1, r.nextInt(maxINT)));
            } else {
                int i1 = r.nextInt(n);
                outputStream.write(String.format("count %d %d", i1, i1 + r.nextInt(n - i1)));
            }
            outputStream.newLine();
        }
        outputStream.close();
    }

}

