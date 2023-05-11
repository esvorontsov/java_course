import java.util.Arrays;
import java.util.Collections;

/**
 * Сортировка методом quick sort
 */
public class QuickSort implements SortingAlgorithm {
    public void sort(String[] array) {
        if (array.length == 0) {
            return;
        }
        execute(array, 0, array.length - 1);
    }

    public void reverseSort (String[] array){
        sort(array);
        Collections.reverse(Arrays.asList(array));
    }

    private void execute(String[] arr, int low, int high) {
        //завершить,если уже нечего делить
        if (low >= high) return;

        //опорное значение
        int middle = low + (high - low) / 2;
        String border = arr[low + (high - low) / 2];

        //меняем элементы местами
        int i = low, j = high;
        while (i <= j) {
            while (arr[i].compareToIgnoreCase(border) < 0) i++;
            while (arr[j].compareToIgnoreCase(border) > 0) j--;
            if (i <= j) {
                String swap = arr[i];
                arr[i] = arr[j];
                arr[j] = swap;
                i++;
                j--;
            }
        }

        //рекурсия для сортировки левой и правой части
        if (low < j) execute(arr, low, j);
        if (high > i) execute(arr, i, high);
    }
}
