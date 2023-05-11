/**
 * Интерфейс сортировки
 */
public interface SortingAlgorithm {

    /**
     * Сортирует по возрастанию
     * @param array
     */
    void sort(String[] array);

    /**
     * Сортирует по убыванию
     * @param array
     */
    void reverseSort(String[] array);
}
