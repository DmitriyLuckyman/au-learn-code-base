import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        //final long start = System.currentTimeMillis();
        InputStream inputStream = args.length > 0 ? new FileInputStream(args[0]) : System.in;
        final InputStreamReader in = new InputStreamReader(inputStream);
        final BufferedReader bufferedReader = new BufferedReader(in);
        final char[] palindrom = bufferedReader.readLine().toCharArray();
        final int[] oddPalindroms = d1Palindroms(palindrom);
        final int[] evenPalindroms = d2Palindroms(palindrom);
        //System.out.println("d1Palindroms(palindrom) = " + Arrays.toString(oddPalindroms));
        //System.out.println("d2Palindroms(palindrom) = " + Arrays.toString(evenPalindroms));
        int[] answers = new int[palindrom.length + 1];
        int index = 0;
        for(int i = 0; i < oddPalindroms.length;++i){
            final int evenPosition = i * 2;
            final int oddPosition = evenPosition + 1;
            final int evenPLength = evenPalindroms[i] * 2;
            if(evenPLength == evenPosition){
                answers[index++] = palindrom.length - evenPLength;
            }
            final int oddPLength = oddPalindroms[i] * 2 - 1;
            if(oddPLength == oddPosition){
                answers[index++] = palindrom.length - oddPLength;
            }
            //System.out.printf("i = %d, evenP =%d, d2 = %d, oddP = %d, d1 = %d%n", i, evenPosition, evenPLength, oddPosition, oddPLength);
        }
        for(int i = index - 1; i >= 0; --i){
            System.out.print(answers[i] + " ");
        }
        final long stop = System.currentTimeMillis();
        //System.out.println();
        //System.out.println("(stop - start) = " + (stop - start));
    }

    static int[] d1Palindroms(char[] s){
        int n = s.length;
        final int maxN = n / 2 + 1;
        int[] d1 = new int[maxN];
        int l = 0, r = -1;
        for (int i = 0; i < maxN; ++i) {
            int k = (i > r ? 0 : Math.min(d1[l + r - i], r - i)) + 1;
            while (i + k < n && i - k >= 0 && s[i + k] == s[i - k]) ++k;
            d1[i] = k--;
            if (i + k > r) {
                l = i - k;
                r = i + k;
            }
        }
        return d1;
    }

    static int[] d2Palindroms(char[] s){
        int n = s.length;
        final int maxN = n / 2 + 1;
        int[] d2 = new int[maxN];
        int l = 0, r = -1;
        for (int i = 0; i < maxN; ++i) {
            int k = (i > r ? 0 : Math.min(d2[l + r - i + 1], r - i + 1)) + 1;
            while (i + k - 1 < n && i - k >= 0 && s[i + k - 1] == s[i - k]) ++k;
            d2[i] = --k;
            if (i + k - 1 > r) {
                l = i - k;
                r = i + k - 1;
            }
        }
        return d2;
    }
}
