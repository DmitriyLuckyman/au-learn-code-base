import java.io.*;
import java.util.Arrays;

/**
 * @author Dmitriy Bandurin
 *         Date: 28.04.13
 */
public class ListOfPrimes {

    public static void main(String[] args) throws IOException {
        final int MAX = 1000000;
        int[] primitives = new int[MAX];
        int countOfPrimitives = 0;
        primitives[countOfPrimitives++] = 1;
        primitives[countOfPrimitives++] = 2;
        int k = 3;
        while(k <= MAX){
            final int sqrt = (int) Math.sqrt(k);
            boolean isPrimitive = true;
            for(int i = 1 ; i < countOfPrimitives && primitives[i] <= sqrt ; ++i){
                if(k % primitives[i] == 0){
                    isPrimitive = false;
                    break;
                }
            }
            if(isPrimitive){
                primitives[countOfPrimitives++] = k;
            }
            k+=2;
        }
        System.out.println("countOfPrimitives = " + countOfPrimitives);
        primitives = Arrays.copyOf(primitives, countOfPrimitives);
        final String primes = Arrays.toString(primitives);
        System.out.println("Arrays.toString(primitives) = " + primes);
        final BufferedWriter primes1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("primes")));
        primes1.write(primes);
        primes1.close();

    }
}