import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {
    //    long t = System.currentTimeMillis();
        InputStream inputStream = args.length > 0 ? new FileInputStream(args[0]) : System.in;
        final InputStreamReader in = new InputStreamReader(inputStream);
        final BufferedReader bufferedReader = new BufferedReader(in);
        int n = Integer.parseInt(bufferedReader.readLine());
        while (n > 0) {

            String[] s = bufferedReader.readLine().split(" ");
            final MyBigInteger i1 = new MyBigInteger(toIntArray(s[0].toCharArray()));
            final MyBigInteger i2 = new MyBigInteger(toIntArray(s[1].toCharArray()));
            final MyBigInteger r = karatsuba(i1, i2);

/*            System.out.println("MyBigInteger.normalize = " + MyBigInteger.normalize);
            System.out.println("MyBigInteger.add = " + MyBigInteger.add);
            System.out.println("MyBigInteger.subtract = " + MyBigInteger.subtract);
            System.out.println("MyBigInteger.multiply = " + MyBigInteger.multiply);
            System.out.println("MyBigInteger.shiftLeft = " + MyBigInteger.shiftLeft);
            System.out.println("MyBigInteger.shiftRight = " + MyBigInteger.shiftRight);
            System.out.println("MyBigInteger.toBigInt = " + MyBigInteger.toBigInt);
            System.out.println("MyBigInteger.toIntArray = " + MyBigInteger.toIntArray);
            System.out.println("MyBigInteger.multiplyB = " + MyBigInteger.multiplyB);
            System.out.println("MyBigInteger.multiplyS = " + MyBigInteger.multiplyS);*/
    //        System.out.println("(z-t) = " + (z - t));
            final char[] print = r.print();
            System.out.println(print);
            n--;
        }
     //   long z = System.currentTimeMillis();
       // System.out.println("time = " + (z - t));
    }

    public static MyBigInteger karatsuba(MyBigInteger x, MyBigInteger y) {

        // cutoff to brute force
        int N = Math.max(x.value.length, y.value.length);
        if (N <= 2500) return x.multiply(y);                // optimize this parameter

        // number of bits divided by 2, rounded up
        N = (N / 2) + (N % 2);

        MyBigInteger b = x.shiftRight(N);
        MyBigInteger a = x.subtract(b.shiftLeft(N));
        MyBigInteger d = y.shiftRight(N);
        MyBigInteger c = y.subtract(d.shiftLeft(N));

        // compute sub-expressions
        MyBigInteger ac = karatsuba(a, c);
        MyBigInteger bd = karatsuba(b, d);
        MyBigInteger abcd = karatsuba(a.add(b), c.add(d));

        return ac.add(abcd.subtract(ac).subtract(bd).shiftLeft(N)).add(bd.shiftLeft(2 * N));
    }

    public static int[] toIntArray(char[] chars) {
    //    long start = System.currentTimeMillis();
        final int[] ints = new int[chars.length];
        for (int i = 0; i < chars.length; ++i) {
            ints[i] = chars[i] - '0';
        }
     //   long end = System.currentTimeMillis();
     //   MyBigInteger.toIntArray += end - start;
        return ints;
    }
}

class MyBigInteger {
/*    static long multiply = 0;
    static long add = 0;
    static long subtract = 0;
    static long shiftLeft = 0;
    static long shiftRight = 0;
    static long normalize = 0;
    static long toBigInt = 0;
    static long toIntArray = 0;
    static long multiplyB = 0;
    static long multiplyS = 0;*/
    public int[] value;
    public int offset;

    MyBigInteger(int[] value) {
        this.value = value;
        this.offset = 0;
    }

    MyBigInteger multiply(MyBigInteger y) {
   //     long start = System.currentTimeMillis();
        final BigInteger bigInteger = toBigInteger();
        final BigInteger bigInteger1 = y.toBigInteger();

     //   long startM = System.currentTimeMillis();
        final BigInteger multiply1 = bigInteger.multiply(bigInteger1);
    //    long stopM = System.currentTimeMillis();
     //   multiplyB += stopM - startM;

      //  startM = System.currentTimeMillis();
        final String s = toString(multiply1);
      //  stopM = System.currentTimeMillis();
      //  multiplyS += stopM - startM;

        MyBigInteger z = new MyBigInteger(Main.toIntArray(s.toCharArray()));

      //  long end = System.currentTimeMillis();
      //  multiply += end - start;
        return z;
    }

    MyBigInteger shiftLeft(int n) {
    //    long start = System.currentTimeMillis();
        final MyBigInteger myBigInteger = new MyBigInteger(Arrays.copyOf(value, value.length + n));
     //   long end = System.currentTimeMillis();
    //    shiftLeft += end - start;
        return myBigInteger;
    }

    MyBigInteger shiftRight(int n) {
     //   long start = System.currentTimeMillis();
        final int newLength = value.length - n;
        final MyBigInteger myBigInteger;
        if(newLength <= 0 ){
             myBigInteger = new MyBigInteger(new int[1]);
        } else {
            myBigInteger = new MyBigInteger(Arrays.copyOf(value, newLength));
        }

     //   long end = System.currentTimeMillis();
     //   shiftRight += end - start;
        return myBigInteger;
    }

    MyBigInteger subtract(MyBigInteger y) {
      //  final long start = System.currentTimeMillis();
        final int zLength = Math.max(value.length, y.value.length);
        final MyBigInteger z = new MyBigInteger(new int[zLength]);
        int mod = 0;
        for (int zIndex = z.value.length - 1,
                 xIndex = value.length - 1,
                 yIndex =y.value.length - 1; zIndex >= 0; --zIndex, --xIndex , --yIndex) {
            final int a = xIndex >= 0 ? value[xIndex] : 0;
            final int b = yIndex >= 0 ? y.value[yIndex] : 0;
            z.value[zIndex] = a - b + mod;
            mod = z.value[zIndex] / 10;
            z.value[zIndex] = z.value[zIndex] - mod * 10;
            if(z.value[zIndex] < 0){
                z.value[zIndex] +=10;
                mod--;
            }
        }
    //    final long end = System.currentTimeMillis();
     //   subtract += end - start;
        z.normalize();
        return z;
    }

    MyBigInteger add(MyBigInteger y) {
     //   long start = System.currentTimeMillis();
        int zLength = Math.max(value.length, y.value.length) + 1;
        MyBigInteger z = new MyBigInteger(new int[zLength]);
        int mod = 0;
        for (int zIndex = z.value.length - 1,
                     xIndex = value.length - 1,
                     yIndex =y.value.length - 1; zIndex >= 0; --zIndex, --xIndex , --yIndex) {
            int a = xIndex >= 0 ? value[xIndex] : 0;
            int b = yIndex >= 0 ? y.value[yIndex] : 0;
            z.value[zIndex] = mod + a + b;
            mod = z.value[zIndex] / 10;
            z.value[zIndex] = z.value[zIndex] % 10;
        }
     //   long end = System.currentTimeMillis();
     //   add += end - start;
        z.normalize();
        return z;
    }

    public void normalize() {
      //  long start = System.currentTimeMillis();
        while (value[offset] == 0 && offset < value.length - 1) {
            offset++;
        }
        value = Arrays.copyOfRange(value, offset, value.length);
        offset = 0;
     //   long end = System.currentTimeMillis();
     //   normalize += end - start;
    }

    public char[] print() {
        final char[] chars = new char[value.length - offset];
        for (int i = 0; i < value.length - offset; ++i) {
            chars[i] = (char) (value[i + offset] + '0');
        }
        return chars;
    }

    BigInteger toBigInteger(){
     //   long start = System.currentTimeMillis();
        final BigInteger bigInteger = new BigInteger(new String(print()));
     //   long end = System.currentTimeMillis();
    //    toBigInt += end - start;
        return bigInteger;
    }

    public static String toString(BigInteger bi) {
        StringBuilder sb = new StringBuilder();
        int i = 16;
        while (bi.compareTo(powerOfTen(i)) > 0)
            i *= 2;
        toString(bi, sb, i);
        int start = 0;
        while (sb.charAt(start) == '0' && sb.length() - 1 > start)
            start++;
        return sb.substring(start);
    }

    private static void toString(BigInteger bi, StringBuilder sb, int digits) {
        if (digits < 18) {
            int start = sb.length();
            for (int i = 0; i < digits; i++)
                sb.append('0');
            long l = bi.longValue();
            for (int i = digits - 1; i >= 0; i--, l /= 10)
                sb.setCharAt(start + i, (char) ('0' + l % 10));
        } else {
            int digits2 = digits / 2;
            BigInteger[] parts = bi.divideAndRemainder(powerOfTen(digits2));
            toString(parts[0], sb, digits - digits2);
            toString(parts[1], sb, digits2);
        }
    }

    private static final Map<Integer, BigInteger> powersOfTen = new HashMap<Integer, BigInteger>();

    private static BigInteger powerOfTen(int digits2) {
        BigInteger tens = powersOfTen.get(digits2);
        if (tens == null)
            powersOfTen.put(digits2, tens = BigInteger.TEN.pow(digits2));
        return tens;
    }
}