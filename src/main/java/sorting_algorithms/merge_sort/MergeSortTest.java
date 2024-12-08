package sorting_algorithms.merge_sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {

    @Test
    public void test_sortedAscending() {
        int[] array = {1, 2, 3, 4, 5};
        MergeSort.mergeSort(array);
        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, array);
    }

    @Test
    public void test_sortedDescending() {
        int[] array = {5, 4, 3, 2, 1};
        MergeSort.mergeSort(array);
        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, array);
    }

    @Test
    public void test_duplicateElements() {
        int[] array = {5, 2, 4, 2, 1};
        MergeSort.mergeSort(array);
        int[] expected = {1, 2, 2, 4, 5};
        assertArrayEquals(expected, array);
    }

    @Test
    public void test_nullArray() {
        assertThrows(NullPointerException.class, () -> MergeSort.mergeSort(null));
    }

    @Test
    public void test_emptyArray() {
        int[] array = {};
        MergeSort.mergeSort(array);
        assertEquals(0, array.length);
    }
}
