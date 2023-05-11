import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        // студенты
        StudentsToConsole(new Student[]{
              new Student(1, "s s s", BigDecimal.valueOf(100.01)) ,
              new Student(2, "v v v", BigDecimal.valueOf(200.02)),
              new Student(3, "d d d", BigDecimal.valueOf(300.03))
        });

        // сортировка
        TestSorting();
    }

    /**
     * Вывод на консоль массива студентов
     * @param students массив студентов
     */
    private static void StudentsToConsole(Student[] students){
        if (students == null) {
            return;
        }

        System.out.println("студенты:");
        for (Student s: students){
            System.out.println(s.toString());
        }
        System.out.println("");
    }

    /**
     * Демонстрация методов сортировки
     */
    private static void TestSorting() {
        // готовим массивы
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < 1000000; i++){
            list.add(UUID.randomUUID().toString());
        }
        String[] arr1 = list.toArray(new String[0]);
        String[] arr2 = arr1.clone();
        String[] arr3 = arr1.clone();
        String[] arr4 = arr1.clone();

        // вывод исходного массива
        System.out.println("start array:");
        printArray(arr1, 5);
        System.out.println("");

        // замеры времени выполнения сортировок
        getExecutionTimeBySystem(arr1, (a) -> execQuickSorting(a));
        getExecutionTimeBySystem(arr2, (a) -> execDefaultSorting(a));
        getExecutionTimeByLocalDateTime(arr3, (a) -> execQuickSorting(a));
        getExecutionTimeByLocalDateTime(arr4, (a) -> execDefaultSorting(a));
    }

    /**
     * quick сортировка
     * @param arr
     */
    private static void execQuickSorting(String[] arr){
        System.out.println("quick sorting:");
        QuickSort quickSorter = new QuickSort();
        quickSorter.sort(arr);
        printArray(arr, 5);
    }

    /**
     * default сортировка
     * @param arr
     */
    private static void execDefaultSorting(String[] arr){
        System.out.println("default sorting:");
        DefaultSort defSorter = new DefaultSort();
        defSorter.sort(arr);
        printArray(arr, 5);
    }

    /**
     * замер времени выполнения через System.currentTimeMillis
     */
    private static void getExecutionTimeBySystem(String[] arr, Sorting sorting){
        long start = System.currentTimeMillis();

        // вызов сортировки
        sorting.sort(arr);

        long delta = System.currentTimeMillis() - start;
        System.out.println(String.format("execution time by system: %d ms", delta));
        System.out.println("");
    }

    /**
     * замер времени выполнения через LocalDateTime
     */
    private static void getExecutionTimeByLocalDateTime(String[] arr, Sorting sorting){
        LocalDateTime start = LocalDateTime.now();

        // вызов сортировки
        sorting.sort(arr);

        Duration delta = Duration.between(start, LocalDateTime.now());
        System.out.println(String.format("execution time by localdatetime: %d ms", delta.toMillis()));
        System.out.println("");
    }

    /**
     * вывод на кончоль массива
     * @param arr массив
     * @param printedLength количество выводимых элементов от начала массива
     */
    private static void printArray(String[] arr, int printedLength) {
        int len = arr.length > printedLength ? printedLength : arr.length;
        for(int i = 0; i < len; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }

    @FunctionalInterface
    private interface Sorting {
        void sort(String[] arr);
    }
}
