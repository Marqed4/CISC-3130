import java.util.Arrays;

public class SearchAndSortingAlgo {
    void main() {
        System.out.println(binarySearch(
                new int[]{-5, -1, 0, 3, 9, 14, 19, 24, 33, 41, 56, 62, 70, 88, 99}, 18));

        int[] n = new int[]{29, 17, 3, 94, 46, 8, -4, 12};
        selectionSort(n);
        for (int e : n) {
            System.out.print(e + " ");
        } IO.println();

        n = new int[]{-1, 0, -7, -5, 6};
        insertionSort(n);
        for (int e : n) {
            System.out.print(e + " ");
        } IO.println();

        n = new int[]{-1, 0, -7, -5, 6};
        mergeSort(n);
        for (int e : n) {
            System.out.print(e + " ");
        } IO.println();
    }

    private int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;

        // Outer loop iterates through
        // the unsorted portion of the array
        for (int i = 0; i < n - 1; i++) {
            // Assume the current
            // index is the minimum
            int minIndex = i;

            // Inner loop finds the actual minimum
            // element in the remaining unsorted part
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first
            // element of the unsorted part (at index i)
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

/*
//1
After First Pass: 20, 25, 15, 10
After Pass: 20, 25, 10, 15
After Pass: 20, 10, 25, 15
After Pass: 10, 20, 25, 15
After Pass: 10, 20, 15, 25
After Last Pass: 10, 15, 20, 25
*/

/*
First Pass: 20, 15, 10, 5, 25
Second Pass: 15, 10, 5, 20, 25
Third Pass: 10, 5, 15, 20, 25
Fourth Pass: 5, 10, 15, 20, 25
*/

public static void insertionSort(int[] a) {
    for (int i = 1; i < a.length; i++) {
        for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
            swap(a, j, j - 1);
        }

        // for debugging:
        // IO.println("After pass " + i + ": " + Arrays.toString(a));
    }
}
    private static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static void mergeSort(int[] a) {
        // for debugging:
        // IO.println("sorting " + Arrays.toString(a));\

        // implicit base case:
        // if a.length <= 1, do nothing (already sorted)

        if (a.length > 1) { // if we are not in the base case
            // split array into roughly two halves
            int[] left = Arrays.copyOfRange(a, 0, a.length / 2);
            int[] right = Arrays.copyOfRange(a, a.length / 2, a.length);

            // recursively sort the two halves
            mergeSort(left);
            mergeSort(right);

            // merge the sorted halves into a
            merge(left, right, a);
        }
    }

    //              *                       *
    // {5, 7, 7, 10} {2, 3, 5, 6, 8, 11, 12}
    //
    //                                    *
    // {2, 3, 5, 5, 6, 7, 7, 8, 10, 11, 12}

    // merge sorted arrays arr1 and arr2 together,
    // putting the elements in the result array in sorted order
    private static void merge(int[] arr1, int[] arr2, int[] result) {
        // for debugging:
        // IO.println("merging " + Arrays.toString(arr1) + " and " + Arrays.toString(arr2));

        int index1 = 0;      // current index in arr1
        int index2 = 0;      // current index in arr2
        int resultIndex = 0; // current index in result

        // loop until we reach the end of arr1 or arr2
        while (index1 < arr1.length && index2 < arr2.length) {
            if (arr1[index1] < arr2[index2]) {
                result[resultIndex++] = arr1[index1++];
            } else {
                result[resultIndex++] = arr2[index2++];
            }
        }

        // at this point, we've reached the end of either arr1 or arr2,
        // but we don't know which array has the remaining elements

        // get any remaining elements from arr1
        while (index1 < arr1.length) {
            result[resultIndex++] = arr1[index1++];
        }

        // get any remaining elements from arr2
        while (index2 < arr2.length) {
            result[resultIndex++] = arr2[index2++];
        }
    }
}