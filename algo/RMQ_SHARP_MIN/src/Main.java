import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = args.length > 0 ? new FileInputStream(args[0]) : System.in;
        final InputStreamReader in = new InputStreamReader(inputStream);
        final BufferedReader bufferedReader = new BufferedReader(in);
        final char[] temp = new char[1024];
        int readCount;
        final StringBuilder builder = new StringBuilder();
        while ((readCount = bufferedReader.read(temp)) != -1) {
            builder.append(temp, 0, readCount);
        }
        final String input = builder.toString();
        final StringTokenizer tokenizer = new StringTokenizer(input);
        int n = Integer.parseInt(tokenizer.nextToken());
        final RMQTree tree = new RMQTree(n);
        int q = Integer.parseInt(tokenizer.nextToken());
        int[] source = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            source[n - 1 - i] = Integer.parseInt(tokenizer.nextToken());
        }
        tree.build(source);
        while (q > 0) {
            String command = tokenizer.nextToken();
            if ("change".equals(command)) {
                tree.update(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
            } else if ("count".equals(command)) {
                int minCount = tree.getMinCount(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
                System.out.println(minCount);
            }
            --q;
        }
    }
}

class RMQTree {
    private int[] t;
    private int[] count;
    private final int k;

    RMQTree(int n) {
        int z =  n - 1 == 0 ? 1 :  n - 1;
        double ceil = Math.ceil(Math.log(z)/Math.log(2) + 1);
        k = (int) Math.pow(2, ceil);
        t = new int[2 * k];
        count = new int[2 * k];
    }

    void build(final int[] v) {
        for (int i = k; (i < 2 * k); ++i) {
            if(i - k < v.length){
                t[i] = v[i - k];
                count[i] = 1;
            } else {
                t[i] = Integer.MAX_VALUE;
                t[i] = 0;
            }
        }

        for (int i = k - 1; i > 0; --i) {
            int leftIndex = 2 * i;
            int rightIndex = 2 * i + 1;
            if (t[leftIndex] < t[rightIndex]) {
                t[i] = t[leftIndex];
                count[i] = count[leftIndex];
            } else if (t[leftIndex] > t[rightIndex]) {
                t[i] = t[rightIndex];
                count[i] = count[rightIndex];
            } else {
                t[i] = t[leftIndex];
                count[i] = count[leftIndex] + count[rightIndex];
            }
        }
    }

    int getMinCount(int l, int r) {
        long minValue = Long.MAX_VALUE;
        int minCount = 0;
        int n = t.length / 2;
        l += n - 1;
        r += n - 1;
        while (l <= r) {
            if (l % 2 != 0) {
                if (t[l] < minValue) {
                    minValue = t[l];
                    minCount = count[l];
                } else if (t[l] == minValue) {
                    minCount += count[l];
                }
            }

            if (r % 2 == 0) {
                if (t[r] < minValue) {
                    minValue = t[r];
                    minCount = count[r];
                } else if (t[r] == minValue) {
                    minCount += count[r];
                }
            }

            l = (l + 1) / 2;
            r = (r - 1) / 2;
        }
        return minCount;
    }

    void update(int i, int x) {
        int n = t.length / 2;
        i += n - 1;
        t[i] = x;
        while ((i /= 2) != 0) {
            int leftIndex = 2 * i;
            int rightIndex = 2 * i + 1;
            if (t[leftIndex] < t[rightIndex]) {
                t[i] = t[leftIndex];
                count[i] = count[leftIndex];
            } else if (t[leftIndex] > t[rightIndex]) {
                t[i] = t[rightIndex];
                count[i] = count[rightIndex];
            } else {
                t[i] = t[leftIndex];
                count[i] = count[leftIndex] + count[rightIndex];
            }
        }
    }

    void printTree(){
        for (int i = 0; i < t.length; ++i ) {
            System.out.print(t[i]+ ":" + count[i] + " ");
        }
        System.out.println("");
    }
}
