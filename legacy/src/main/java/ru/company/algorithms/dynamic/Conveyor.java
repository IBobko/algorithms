package ru.company.algorithms.dynamic;

public class Conveyor {

    static int[] x = new int[2];

    static int[] e = new int[2];

    static int[][] a = new int[2][6];
    static int[][] t = new int[2][5];

    static int[][] l = new int[2][6];

    static int common_l;

    static {
        x[0] = 3;
        x[1] = 2;


        e[0] = 2;
        e[1] = 4;

        a[0][0] = 7;
        a[0][1] = 9;
        a[0][2] = 3;
        a[0][3] = 4;
        a[0][4] = 8;
        a[0][5] = 4;

        a[1][0] = 8;
        a[1][1] = 5;
        a[1][2] = 6;
        a[1][3] = 4;
        a[1][4] = 5;
        a[1][5] = 7;


        t[0][0] = 2;
        t[0][1] = 3;
        t[0][2] = 1;
        t[0][3] = 3;
        t[0][4] = 4;

        t[1][0] = 2;
        t[1][1] = 1;
        t[1][2] = 2;
        t[1][3] = 2;
        t[1][4] = 1;

    }


    public static void main(String[] args) {


        System.out.println(Math.min(f(0, 5) + x[0], f(1, 5) + x[1]));


        System.out.println(fastest_way());

        int i = common_l;
        System.out.println("Конвейер " + i + "рабочее место" + 6 );
        for (int j = 5; j >= 1; j--) {
            i = l[i - 1][j];
            System.out.println("Конвейер " + i + "рабочее место" + j);
        }

    }


    static int f(int i, int j) {

        if (i == 0) {
            if (j == 0) {
                return e[0] + a[0][0];
            } else {
                return Math.min(f(0, j - 1) + a[0][j], f(1, j - 1) + a[0][j] + t[1][j - 1]);
            }
        }


        if (i == 1) {
            if (j == 0) {
                return e[1] + a[1][0];
            } else {
                return Math.min(f(1, j - 1) + a[1][j], f(0, j - 1) + t[0][j - 1] + a[1][j]);
            }
        }
        return 0;
    }


    static int fastest_way() {
        int[][] f = new int[2][6];


        f[0][0] = e[0] + a[0][0];
        f[1][0] = e[1] + a[1][0];


        for (int j = 1; j < 6; j++) {
            if (f[0][j - 1] + a[0][j] <= f[1][j - 1] + t[1][j - 1] + a[0][j]) {
                f[0][j] = f[0][j - 1] + a[0][j];
                l[0][j] = 1;
            } else {
                f[0][j] = f[1][j - 1] + t[1][j - 1] + a[0][j];
                l[0][j] = 2;
            }

            if (f[1][j - 1] + a[1][j] <= f[0][j - 1] + t[0][j - 1] + a[1][j]) {
                f[1][j] = f[1][j - 1] + a[1][j];
                l[1][j] = 2;
            } else {
                f[1][j] = f[0][j - 1] + t[0][j - 1] + a[1][j];
                l[1][j] = 1;
            }

        }


        if (f[0][5] + x[0] <= f[1][5] + x[1]) {
            common_l = 1;
            return f[0][5] + x[0];
        } else {
            common_l = 2;
            return f[1][5] + x[1];
        }


    }

}
