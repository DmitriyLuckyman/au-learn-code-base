import java.lang.*;
import java.io.*;

public class MainClass {

    public static void main(String[] args) throws IOException{
        BufferedWriter bwriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("in3")));
        int count = 0;
        for(int i = 33997*33967; count < 1000;  i--){
            if(QuadraticResidue.isRQ(i,33997) && QuadraticResidue.isRQ(i,33967)){
                System.out.println((33997*33967) + " " + i);
                count++;
            }
        }
/*        final int prime = 524287 * 127;
        final int count = (prime - 1) / 2;
        bwriter.write(String.valueOf(count));
        bwriter.newLine();
        for(int i = 1; i <= count; i++){
            bwriter.write(String.format("%d %d", prime, i));
            bwriter.newLine();
        }
        bwriter.close();*/
    }
}