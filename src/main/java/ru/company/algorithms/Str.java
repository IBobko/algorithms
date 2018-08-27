package ru.company.algorithms;

public class Str {

    public static void main(String[] args) {
        String X = "CATCGA";
        String Y = "GTACCGTCA";

        int[][] out = new int[X.length()+1][Y.length()+1];

        for (int i = 0; i <= X.length(); i++) {
            out[i][0] = 0;
        }

        for (int j = 0; j <= Y.length(); j++) {
            out[0][j] = 0;
        }

        for (int i = 1; i <= X.length(); i++) {
            for (int j = 1; j <= Y.length(); j++) {
                if (X.charAt(i-1) == Y.charAt(j-1)){
                    out[i][j] = out[i-1][j-1] + 1;
                } else {
                    out[i][j] = Math.max(out[i-1][j],out[i][j-1]);
                }
            }
        }


        for (int i = 0; i <= X.length(); i++) {
            for (int j = 0; j <= Y.length(); j++) {
                System.out.print(out[i][j] + "\t");
            }
            System.out.println("");
        }

        System.out.println(t(X,Y,out,X.length(),Y.length()));

    }



    static String t(String X,String Y,int[][] out, int i, int j) {
        if (out[i][j] == 0) return "";
        if (X.charAt(i-1) == Y.charAt(j-1)) return t(X,Y,out,i-1,j-1) + X.charAt(i-1);
        if (out[i][j-1] > out[i-1][j]) return t(X,Y,out,i,j-1);
        return t(X,Y,out,i-1,j);

    }
}
