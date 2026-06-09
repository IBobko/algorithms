package ru.company.algorithms.sort;

import org.junit.Assert;
import org.junit.Test;

public class MergeSortingTest {
    @Test
    public void sort() {
        int[] a = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2,1,0};
        MergeSorting.sort(a);
        Assert.assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, a);
        ;
    }
}