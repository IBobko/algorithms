package ru.company.algorithms.sort;

public class InsertionSort {
    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 2, 3, 6, 3, 7, 98};


        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0 && key > a[j]) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }


        System.out.println();
    }
}
