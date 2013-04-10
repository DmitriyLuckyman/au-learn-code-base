import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = args.length > 0 ? new FileInputStream(args[0]) : System.in;
        final InputStreamReader in = new InputStreamReader(inputStream);
        final BufferedReader bufferedReader = new BufferedReader(in);
        int n = Integer.parseInt(bufferedReader.readLine());
        final RMQColorTree tree = new RMQColorTree(n);
        int q = Integer.parseInt(bufferedReader.readLine());
        while (q > 0) {
            String[] s = bufferedReader.readLine().split(" ");
            tree.update(1, 0, n - 1,
                    Integer.parseInt(s[0]) - 1,
                    Integer.parseInt(s[1]) - 1,
                    Integer.parseInt(s[2])
            );
            --q;
        }
        tree.print();
    }
}

class RMQColorTree {
    private int[] t;
    private int n;

    RMQColorTree(int n) {
        this.n = n;
        int z =  n - 1 == 0 ? 1 :  n - 1;
        double ceil = Math.floor(Math.log(z) / Math.log(2) + 1);
        int k = (int) Math.pow(2, ceil);
        t = new int[2 * k];
        Arrays.fill(t, 0);
    }

    void update(int v, int tl, int tr, int l, int r, int color) {
        if (l > r)
            return;
        if (l == tl && tr == r)
            t[v] = color;
        else {
            push(v);
            int tm = (tl + tr) / 2;
            update(v * 2, tl, tm, l, Math.min(r, tm), color);
            update(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r, color);
        }
    }

    void push(int v) {
        if (t[v] != -1) {
            t[v * 2] = t[v * 2 + 1] = t[v];
            t[v] = -1;
        }
    }

    int get (int v, int tl, int tr, int pos) {
        if (tl == tr)
            return t[v];
        push (v);
        int tm = (tl + tr) / 2;
        if (pos <= tm)
            return get (v*2, tl, tm, pos);
        else
            return get (v*2+1, tm+1, tr, pos);
    }

    void print() {
        for (int i = 0 ; i < n; ++i) {
            System.out.print(get(1, 0, n-1, i) + " ");
        }
    }
}
