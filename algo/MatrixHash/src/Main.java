import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = args.length > 0 ? new FileInputStream(args[0]) : System.in;
        final InputStreamReader in = new InputStreamReader(inputStream);
        final BufferedReader bufferedReader = new BufferedReader(in);
        String[] hw = bufferedReader.readLine().split(" ");
        int h = Integer.parseInt(hw[0]);
        int w = Integer.parseInt(hw[1]);
        int[][] A = new int[ h ][ w + 1 ];
        for(int  i = 0; i < h; ++i){
            String[] s = bufferedReader.readLine().split(" ");
            for(int j = 0; j < w; j++){
                A[i][j] = Integer.parseInt(s[j]);
            }
            A[i][w] = 1;
        }
        Gauss.gauss(A);
    }
}


class Gauss {

    public static void swap(int[] a1, int[] a2) {
        for (int i = 0; i < a1.length; ++i) {
            int t = a1[i];
            a1[i] = a2[i];
            a1[i] = t;
        }
    }

    public static void xor(int[] a1, int[] a2) {
        for (int i = 0; i < a1.length; ++i) {
            a1[i] ^= a2[i];
        }
    }

    public static int take(int[] a1, int v) {
        int r = -1;
        for (int i = 0; i < a1.length; ++i) {
            if(a1[i] == v ){
                r = i;
                break;
            }
        }
        return r;
    }

    public static int[] gauss(int[][] a) {
        int n = a.length;
        int m = a[0].length - 1;
        int[] where = new int[m];
        Arrays.fill(where, -1);
        for (int col = 0, row = 0; col < m && row < n; ++col) {
            for (int i = row; i < n; ++i) {
                if (a[i][col] == 1) {
                    swap(a[i], a[row]);
                    break;
                }
            }
            if (a[row][col] != 1) {
                continue;
            }
            where[col] = row;

            for (int i = 0; i < n; ++i) {
                if (i != row && a[i][col] == 1) {
                    xor(a[i], a[row]);
                }
            }
            ++row;
        }
        int[] ans1 = new int[m];
        int[] ans2 = new int[m];
/*        for (int col : where) {
            System.out.print(col + " ");
        }
        System.out.println("");*/
        Arrays.fill(ans1, -1);
        Arrays.fill(ans2, -1);
        boolean find = false;
        for(int i = m - 1; i >= 0; --i){
            if(where[i] == -1){
                if(!find){
                    ans1[i] = 0;
                    ans2[i] = 1;
                    for(int j = 0; j < n; j++){
                        if(a[j][i] != 0 ){
                            int t = take(where, j);
                            if(t != -1){
                                int[] ints = a[where[t]];
                                int r1 = ints[m];
                                for(int k = 0;k < n; k++){
                                    if(k != t){
                                        r1 ^= ints[k];
                                    }
                                }
                                ans1[t] = r1 ^ ints[t];
                                ans2[t] = r1 ^ 1 ^ ints[t];
                            }
                        }
                    }
                    find = true;
                } else {
                    ans1[i] = 0;
                    ans2[i] = 0;
                }
            } else {
                if(ans1[i] == -1){
                    ans1[i] = a[where[i]][m] ^ a[where[i]][i];
                }
                if(ans2[i] == -1){
                    ans2[i] = a[where[i]][m] ^ a[where[i]][i];
                }
            }
        }
        //System.out.println("ans1");
        for (int aX : ans1) {
            System.out.println(aX);
        }
        //System.out.println("");
        //System.out.println("ans2");
        for (int aX : ans2) {
            System.out.println(aX);
        }
        //System.out.println("");
        return ans1;
    }
}

