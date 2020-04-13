package interview.sort;

import com.google.gson.Gson;

/**
 * Created by cxq on 2020-02-17 14:31
 */
public class AlgSort {
    public void quickSort(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int base = a[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (a[j] >= base && i < j) {
                j--;
            }
            while (a[i] <= base && i < j) {
                i++;
            }
            if (i < j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        a[left] = a[i];
        a[i] = base;

        quickSort(a, left, i - 1);
        quickSort(a, i + 1, right);
    }

    public static void main(String[] args) {
        Gson gson = new Gson();
        AlgSort algSort = new AlgSort();
        int[] a = {5, 6, 2, 4, 1, 9, 8, 3, 7, 0};
        algSort.quickSort(a, 0, 9);

        System.out.println(gson.toJson(a));

    }
}
