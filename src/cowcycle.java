/*
ID: zhm05251
LANG: JAVA
TASK: cowcycle
*/

import java.io.*;
import java.util.*;

public class cowcycle {

    private static final String INPUT_FILE_NAME = "cowcycle.in";
    private static final String OUTPUT_FILE_NAME = "cowcycle.out";

    private static int m, n, xm, ym, xn, yn;
    private static int[] a, b, sa, sb;
    private static double min = Double.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        xm = Integer.parseInt(st.nextToken());
        ym = Integer.parseInt(st.nextToken());
        xn = Integer.parseInt(st.nextToken());
        yn = Integer.parseInt(st.nextToken());

        a = new int[m];
        b = new int[n];
        sa = new int[m];
        sb = new int[n];
        search(0);

        out.print("" + sa[0]);
        for (int i = 1; i < sa.length; i++) {
            out.print(" " + sa[i]);
        }
        out.println();

        out.print("" + sb[0]);
        for (int i = 1; i < sb.length; i++) {
            out.print(" " + sb[i]);
        }
        out.println();
        out.close();
    }

    private static void search(int x) {
        if (x >= m + n) {
            if ((1d * a[m - 1] / b[0]) / (1d * a[0] / b[n - 1]) < 3d) {
                return;
            }
            double[] s = getArray(a, b);
            Arrays.sort(s);
            double v = getVariance(getDifference(s));
            if (v < min) {
                min = v;
                for (int i = 0; i < a.length; i++) {
                    sa[i] = a[i];
                }
                for (int i = 0; i < b.length; i++) {
                    sb[i] = b[i];
                }
            }
        } else if (x < m) {
            int start = x == 0 ? xm : a[x - 1];
            for (int i = start; i <= ym; i++) {
                a[x] = i;
                search(x + 1);
            }
        } else if (x - m < n) {
            int start = x - m == 0 ? xn : b[x - m - 1];
            for (int i = start; i <= yn; i++) {
                b[x - m] = i;
                search(x + 1);
            }
        }
    }

    private static double[] getArray(int[] a, int[] b) {
        int l = a.length * b.length;
        double[] s = new double[l];
        int x = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                s[x++] = 1d * a[i] / b[j];
            }
        }
        return s;
    }

    private static double[] getDifference(double[] a) {
        double[] s = new double[a.length - 1];
        for (int i = 0; i < a.length - 1; i++) {
            s[i] = a[i + 1] - a[i];
        }
        return s;
    }

    private static double getVariance(double[] a) {
        double sum = 0d;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        double mean = sum / a.length;
        double vSum = 0d;
        for (int i = 0; i < a.length; i++) {
            vSum += (a[i] - mean) * (a[i] - mean);
        }
        return vSum / a.length;
    }
}
