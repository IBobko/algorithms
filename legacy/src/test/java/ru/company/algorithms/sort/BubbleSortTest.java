package ru.company.algorithms.sort;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BubbleSortTest {

    @Test
    public void sort() {
        int[] a = new int[]{10, 15, 1, 2, 3, 60, 38, 73, 98};
        BubbleSort.sort(a);
        Assert.assertArrayEquals( new int[]{1, 2, 3, 10, 15, 38, 60, 73, 98}, a );
    }
}