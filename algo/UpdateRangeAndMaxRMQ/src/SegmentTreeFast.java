import java.io.*;
import java.util.*;

public class SegmentTreeFast {

    // specific code
    static final int NEUTRAL_VALUE = Integer.MIN_VALUE;
    static final int NEUTRAL_DELTA = 0;

    static int joinValues(int leftValue, int rightValue) {
        return Math.max(leftValue, rightValue);
    }

    static int joinDeltas(int oldDelta, int newDelta) {
        return oldDelta + newDelta;
    }

    static int joinValueWithDelta(int value, int delta) {
        return value + delta;
    }

    // generic code
    int[] value;
    int[] delta;

    public SegmentTreeFast(int n ,int[] source) {
        value = new int[2 * n];
        System.arraycopy(source, 0, value, n, source.length);
        for (int i = 2 * n - 1; i > 1; i -= 2)
            value[i >> 1] = joinValues(value[i], value[i ^ 1]);

        delta = new int[2 * n];
        Arrays.fill(delta, NEUTRAL_DELTA);
    }

    void applyDelta(int i, int delta) {
        value[i] = joinValueWithDelta(value[i], delta);
        this.delta[i] = joinDeltas(this.delta[i], delta);
    }

    void pushDelta(int i) {
        int d = 0;
        for (; (i >> d) > 0; d++)
            ;
        for (d -= 2; d >= 0; d--) {
            int x = i >> d;
            applyDelta(x, delta[x >> 1]);
            applyDelta(x ^ 1, delta[x >> 1]);
            delta[x >> 1] = NEUTRAL_DELTA;
        }
    }

    public void modify(int a, int b, int delta) {
        a += value.length >> 1;
        b += value.length >> 1;
        pushDelta(a);
        pushDelta(b);
        int ta = -1;
        int tb = -1;
        for (; a <= b; a = (a + 1) >> 1, b = (b - 1) >> 1) {
            if ((a & 1) != 0) {
                applyDelta(a, delta);
                if (ta == -1)
                    ta = a;
            }
            if ((b & 1) == 0) {
                applyDelta(b, delta);
                if (tb == -1)
                    tb = b;
            }
        }
        for (int i = ta; i > 1; i >>= 1)
            value[i >> 1] = joinValues(value[i], value[i ^ 1]);
        for (int i = tb; i > 1; i >>= 1)
            value[i >> 1] = joinValues(value[i], value[i ^ 1]);
    }

    public int query(int a, int b) {
        a += value.length >> 1;
        b += value.length >> 1;
        pushDelta(a);
        pushDelta(b);
        int res = NEUTRAL_VALUE;
        for (; a <= b; a = (a + 1) >> 1, b = (b - 1) >> 1) {
            if ((a & 1) != 0)
                res = joinValues(res, value[a]);
            if ((b & 1) == 0)
                res = joinValues(res, value[b]);
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        final long start = System.currentTimeMillis();
        InputStream inputStream = args.length > 0 ? new FileInputStream(args[0]) : System.in;
        final InputStreamReader in = new InputStreamReader(inputStream);
        final BufferedReader bufferedReader = new BufferedReader(in);

        final String[] inputParameters = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(inputParameters[0]);

        int q = Integer.parseInt(inputParameters[1]);
       final long startSplit = System.currentTimeMillis();
        final String initData = bufferedReader.readLine();

        int[] source = new int[n];
        int index = 0;
        int searchIndex = 0;
        while(index < n){
            int newIndex = initData.indexOf(" ", searchIndex);
            if(newIndex == -1){
                newIndex = initData.length();
            }
            source[index++] = Integer.parseInt(initData.substring(searchIndex, newIndex));
            searchIndex = newIndex + 1;
        }
        final SegmentTreeFast tree = new SegmentTreeFast(n, source);
        final long stopInit = System.currentTimeMillis();
        //  tree.print();
        // System.out.println();
        while (q > 0) {
            String[] s = bufferedReader.readLine().split(" ");
            //    tree.print();
            //    System.out.println();
            final int l = Integer.parseInt(s[1]) - 1;
            final int r = Integer.parseInt(s[2]) - 1;
            if ("add".equals(s[0])) {
                final int value = Integer.parseInt(s[3]);
                //System.out.printf("Update %d %d %d%n", l + 1, r + 1, value);
                tree.modify(l, r, value);

            } else if ("max".equals(s[0])) {
                //System.out.printf("Max %d %d = ", l + 1, r + 1);
                int maximum = tree.query(l, r);
                System.out.println(maximum);
            }
            --q;
        }
        final long stop = System.currentTimeMillis();
        System.out.println("(stop-start) = " + (stop - start));
        System.out.println("(stopInit - start) = " + (stopInit - start));
        System.out.println("(stop - stopInit) = " + (stop - stopInit));
        System.out.println("(stopInit - startSplit) = " + (stopInit - startSplit));
    }
}