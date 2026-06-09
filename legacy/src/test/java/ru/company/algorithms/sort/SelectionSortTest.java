package ru.company.algorithms.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SelectionSortTest {

    @Test
    public void sort() {
        int[] a = new int[]{10, 15, 1, 2, 3, 60, 38, 73, 98};
        int[] a2 = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2,1,0};
        SelectionSort.sort(a2);
        //Assert.assertArrayEquals( new int[]{1, 2, 3, 10, 15, 38, 60, 73, 98}, a );
        Assert.assertArrayEquals( new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8,9,10}, a2 );
    }
}