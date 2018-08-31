package ru.company.algorithms.sort;

public class MergeSorting {
    public static void sort(int a[]) {
        merge_sort(a, 0, a.length);
    }

    /**
     * Merge sort.
     *
     * @param a Массив, который требуется отсортировать.
     * @param p
     * @param q
     */
    public static void merge_sort(int a[], int p, int r) {
        if (p < r-1) {
            int q = (int) Math.floor((r + p) / 2.); // q =
            merge_sort(a, p, q);
            merge_sort(a, q, r);
            merge(a, p, q, r);
        }
    }

    public static void merge(int a[], int p, int q, int r) {
        int n1 = q - p;
        int n2 = r - q;
        int L[] = new int[n1 + 1];
        int R[] = new int[n2 + 1];

        for (int i = 0; i < n1; i++) {
            L[i] = a[p + i];
        }

        for (int j = 0; j < n2; j++) {
            R[j] = a[q + j];
        }

        L[L.length - 1] = Integer.MAX_VALUE;
        R[R.length - 1] = Integer.MAX_VALUE;


        int i = 0;
        int j = 0;

        for (int k = p; k < r; k++) {
             if (L[i] <= R[j]) {
                a[k] = L[i];
                i = i + 1;
            } else {
                a[k] = R[j];
                j = j + 1;
            }
        }
    }
}
