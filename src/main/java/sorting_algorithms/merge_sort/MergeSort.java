package sorting_algorithms.merge_sort;


public class MergeSort {

    public static void mergeSort(int[] array) {
        if (array == null) throw new NullPointerException("The array is null");
        mergeSort(array, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;

            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);

            merge(array, left, middle, right);
        }
    }

    private static void merge(int[] array, int left, int middle, int right) {
        int[] tempArray = new int[right - left + 1];
        int leftIndex = left;
        int rightIndex = middle + 1;
        int tempIndex = 0;

        while (leftIndex <= middle && rightIndex <= right) {
            if (array[leftIndex] <= array[rightIndex]) {
                tempArray[tempIndex] = array[leftIndex];
                leftIndex++;
            } else {
                tempArray[tempIndex] = array[rightIndex];
                rightIndex++;
            }
            tempIndex++;
        }

        while (leftIndex <= middle) {
            tempArray[tempIndex] = array[leftIndex];
            leftIndex++;
            tempIndex++;
        }

        while (rightIndex <= right) {
            tempArray[tempIndex] = array[rightIndex];
            rightIndex++;
            tempIndex++;
        }

        System.arraycopy(tempArray, 0, array, left, tempArray.length);
    }
}
