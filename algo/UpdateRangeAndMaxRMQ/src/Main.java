import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
//        final long start = System.currentTimeMillis();
        InputStream inputStream = args.length > 0 ? new FileInputStream(args[0]) : System.in;
        final InputStreamReader in = new InputStreamReader(inputStream);
        final BufferedReader bufferedReader = new BufferedReader(in);

        final String[] inputParameters = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(inputParameters[0]);
        final RMQRangeTree tree = new RMQRangeTree(n);
        int q = Integer.parseInt(inputParameters[1]);
//        final long startSplit = System.currentTimeMillis();
        final String initData = bufferedReader.readLine();

        int index = 0;
        int searchIndex = 0;
        while(index < n){
            int newIndex = initData.indexOf(" ", searchIndex);
            if(newIndex == -1){
                newIndex = initData.length();
            }
            tree.update(1, 0, n-1, index, index, Integer.parseInt(initData.substring(searchIndex, newIndex)));
            ++index;
            searchIndex = newIndex + 1;
        }
        //tree.build(source, 1, 0, n - 1);
 //       final long stopInit = System.currentTimeMillis();
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
                tree.update(1, 0, n - 1, l, r, value);

            } else if ("max".equals(s[0])) {
                //System.out.printf("Max %d %d = ", l + 1, r + 1);
                int maximum = tree.getMaximum(1, 0, n - 1, l, r);
                System.out.println(maximum);
            } else if("print".equals(s[0])){
                tree.print();
            }
            --q;
        }
//        final long stop = System.currentTimeMillis();
/*        System.out.println("(stop-start) = " + (stop - start));
        System.out.println("(stopInit - start) = " + (stopInit - start));
        System.out.println("(stop - stopInit) = " + (stop - stopInit));
        System.out.println("(stopInit - startSplit) = " + (stopInit - startSplit));*/
    }
}

class RMQRangeTree {
    private int[] t;
    private int[] add;
    private final int n;

    RMQRangeTree(int n) {
        this.n = n;
        int z =  n - 1 == 0 ? 1 :  n - 1;
        double ceil = Math.floor(Math.log(z) / Math.log(2) + 1);
        int k = (int) Math.pow(2, ceil);
        t = new int[2 * k];
        add = new int[2 * k];
        //Arrays.fill(t, Integer.MIN_VALUE);
    }


    void build(final int[] initData, int v, int tl, int tr) {
        if (tl == tr) {
            t[v] = initData[tl];
            add[v] = initData[tl];
        } else {
            int tm = (tl + tr) >> 1;
            build (initData, v*2, tl, tm);
            build (initData, v*2+1, tm+1, tr);
            t[v] = Math.max(t[v*2], t[v*2+1]);
        }
    }

    void update(int v, int tl, int tr, int l, int r, int value) {
//        System.out.printf("l = %d, r = %d, tl = %d, tr = %d %n", l, r, tl, tr);
        push(v, tl, tr);
        if (l == tl && tr == r){
            add[v] += value;
            t[v] += value;
        } else {
            int tm = (tl + tr) >> 1;
            if(l <= tm){
                update(v * 2, tl, tm, l, Math.min(r, tm), value);
            }
            if(r > tm){
                update(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r, value);
            }
            t[v] = Math.max(t[v*2], t[v*2+1]);
        }
    }

    public int getMaximum(int v, int tl, int tr, int l, int r) {
        push(v, tl, tr);
        if (l == tl && r == tr){
            return t[v];
        }
        int max = Integer.MIN_VALUE;
        int tm = (tl + tr) >> 1;
        if(l <= tm){
            max = Math.max(max, getMaximum(v*2, tl, tm, l, Math.min(r, tm)));
        }

        if(r > tm){
            max = Math.max(max, getMaximum(v*2+1, tm+1, tr, Math.max(l, tm+1), r));
        }

        return max;
    }

    void push(int v, int tl, int tr){
        if(add[v] != 0 && tl != tr) {
            t[2 * v] += add[v];
            t[2 * v + 1] += add[v];
            add[2 * v] += add[v];
            add[2 * v + 1] += add[v];
            add[v] = 0;
        }
    }

    int get (int v, int tl, int tr, int pos) {
        if (tl == tr)
            return add[v];
        int tm = (tl + tr) >> 1;
        if (pos <= tm)
            return add[v] + get (v*2, tl, tm, pos);
        else
            return add[v] + get (v*2+1, tm+1, tr, pos);
    }

    void print() {
        System.out.println("Arrays.toString(t) = " + Arrays.toString(t));
        System.out.println("Arrays.toString(add) = " + Arrays.toString(add));
        for (int i = 0 ; i < n; ++i) {
            System.out.print(get(1, 0, n-1, i) + " ");
        }
        System.out.println();
    }
}
