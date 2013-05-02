
/**
 A class to multiply two polynomials using the Fast
 Fourier Transform
 */

import java.io.*;

public class MainFFT  {

    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {
        InputStream inputStream = args.length > 0 ? new FileInputStream(args[0]) : System.in;
        final InputStreamReader in = new InputStreamReader(inputStream);
        final BufferedReader bufferedReader = new BufferedReader(in);
        int n = Integer.parseInt(bufferedReader.readLine());
        while (n > 0) {
            String[] s = bufferedReader.readLine().split(" ");
            final int[] multiply = FFT.multiply(toIntArray(s[0].toCharArray()), toIntArray(s[1].toCharArray()));
            boolean offset = true;
            for(int i = multiply.length - 1; i >= 0;--i){
                if(offset && multiply[i] == 0){
                    continue;
                }
                if(offset){
                    offset = false;
                }
                System.out.print(multiply[i]);
            }
            if(offset){
                System.out.println(0);
            }else{
                System.out.println();
            }
            n--;
        }
    }

    public static int[] toIntArray(char[] chars) {
        final int[] ints = new int[chars.length];
        for (int i = 0; i < chars.length; ++i) {
            ints[i] = chars[chars.length - 1 - i] - '0';
        }
        return ints;
    }
}

class FFT {


    /**
     * A class for complex numbers
     */
    private static class Complex {
        // the real and imaginary parts
        private double real;
        private double im;

        /**
         * Constructs a complex number given the real
         * and imaginary parts.
         *
         * @param real the real part
         * @param im   the imaginary part
         */

        public Complex(double real, double im) {
            this.real = real;
            this.im = im;
        }


        /**
         * Finds the sum of the complex number and another
         * complex number
         *
         * @param c the other complex number
         * @return the sum
         */

        public Complex add(Complex c) {
            return new Complex(real + c.real, im + c.im);
        }


        /**
         * Finds the difference of the complex number and another
         * complex number
         *
         * @param c the other complex number
         * @return the result of subtracting the other complex
         *         number from the complex number
         */

        public Complex subtract(Complex c) {
            return new Complex(real - c.real, im - c.im);
        }


        /**
         * Finds the product of the complex number and another
         * complex number
         *
         * @param c the other complex number
         * @return the product
         */

        public void multiplySE(Complex c) {
            double a = real;
            double b = im;
            this.real = a * c.real - b * c.im;
            this.im = a * c.im + b * c.real;
        }

        public Complex multiply(Complex c) {
            return new Complex(real * c.real - im * c.im,
                    real * c.im + im * c.real);
        }

    }  // end of Complex inner class

    static int[] multiply (int[] a, int[] b) {
        int n = 1;
        while (n < Math.max(a.length, b.length)) {
            n <<= 1;
        }
        n <<= 1;
        Complex[] fa  = convertToComplex(a, n);
        Complex[] fb  = convertToComplex(b, n);

        fft (fa, false);
        fft (fb, false);
        for (int i=0; i < n; ++i){
            fa[i].multiplySE(fb[i]);
        }
        fft (fa, true);

        int[] res = new int[n];
        int carry = 0;
        for (int i = 0; i < n; ++i){
            res[i] = ((int) (fa[i].real + 0.5)) + carry;
            carry = res[i] / 10;
            res[i] %= 10;
        }
        return res;
    }

    private static Complex[] convertToComplex(int[] a, int n) {
        Complex[] result = new Complex[n];
        for (int i=0; i < n; ++i) {
            result[i] = i < a.length ? new Complex(a[i], 0) : new Complex(0, 0);
        }
        return result;
    }

    static void  fft (Complex[] a, boolean invert) {
        int n = a.length;
        if (n == 1)  return;

        Complex[] a0 = new Complex[n/2];
        Complex[] a1 = new Complex[n/2];
        for (int i=0, j=0; i<n; i+=2, ++j) {
            a0[j] = a[i];
            a1[j] = a[i+1];
        }
        fft (a0, invert);
        fft (a1, invert);

        double ang = 2 * Math.PI / n * (invert ? -1 : 1);
        Complex w = new Complex (1, 0);
        Complex wn = new Complex (Math.cos(ang), Math.sin(ang));
        for (int i=0; i<n/2; ++i) {
            final Complex multiply = w.multiply(a1[i]);
            a[i] = a0[i].add(multiply);
            a[i+n/2] = a0[i].subtract(multiply);
            if (invert){
                a[i].real /= 2;
                a[i].im /=2;
                a[i+n/2].real /= 2;
                a[i+n/2].im /= 2;
            }
            w.multiplySE(wn);
        }
    }
}