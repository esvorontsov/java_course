import java.util.Arrays;
import java.util.Collections;

/**
 * Стандартная сортировка
 */
public class DefaultSort implements SortingAlgorithm {
    public void sort (String[] array){
        if (array.length == 0) {
            return;
        }

        Arrays.sort(array);
    }

    public void reverseSort (String[] array){
        if (array.length == 0) {
            return;
        }

        Arrays.sort(array, Collections.reverseOrder());
    }
}
