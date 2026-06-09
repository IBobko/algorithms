package ru.company.algorithms.sort;

public class SelectionSort {
    static void sort(int[] a) {
        for (int j = 0; j < a.length-1; j++) {
            for (int i = j+1; i < a.length; i++) { // Время сравнение этого, это сумма убывающей арифметической прогрессии
                if (a[i] < a[j]) {
                    int m = a[j];
                    a[j] = a[i];
                    a[i] = m;
                }
            }
        }
    }
}
