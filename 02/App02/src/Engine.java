import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.lang.StringBuilder;

public class Engine {

    private void Log(String str){
        System.out.println(str);
    }

    /**
     * Выводит в консоль числа от 1 до 100, которые делятся на 3 без остатка
     */
    public void Method1() {
        Log("Method1: numbers from 1 to 100 that are divisible by 3 without remainder:");

        StringBuilder str = new StringBuilder();

        for (int number = 1; number <= 100; number++) {
            if (number % 3 == 0){
                str.append(" " + number);
            }
        }
        Log(str.toString());
        Log("end");
    }

    /**
     * Принимает из консоли натуральные числа N и K, выводит в консоль первые N натуральных чисел,
     * делящиеся на K без остатка
     * @param scanner
     */
    public void Method2(Scanner scanner) {
        Log("Method2: the first n natural numbers divisible by k without remainder:");

        Log("enter N > 0 - the number of natural numbers");
        int n = GetPositiveIntValue(scanner);

        Log("enter K > 0 - natural divisor");
        int k = GetPositiveIntValue(scanner);

        System.out.print("result numbers:");
        for (int ind = 1; ind <= n; ind++) {
            System.out.print(" " + ind * k);
        }
        Log("");
        Log("end");
    }

    /**
     * Проверка работы методов нахождения в целочисленном массиве позиции элемента с наибольшим и с наименьшим значением
     */
    public void Method3() {
        Log("Method3: demonstration of the search for the maximum and minimum element in the array:");

        // генерация массива целых чисел
        int[] intArray = GetRandomArray(10);

        // вывод массива в консоль
        Log("array of int: " + Arrays.toString(intArray));

        // вызов методов поиска и вывод результатов в консоль
        Log("minNumberPosition=" + GetMinElementPosition(intArray) +
                " maxNumberPosition=" + GetMaxElementPosition(intArray));
        Log("end");
    }

    /**
     * Определяет позицию наибольшего элемента в массиве целых чисел
     * @param numbers массив целых чисел
     * @return позиция наибольшего элемента
     */
    public int GetMaxElementPosition(int[] numbers){
        if (numbers.length == 0){
            return -1;
        }

        int number = numbers[0];
        int position = 0;
        for(int ind = 1; ind < numbers.length; ind++){
            if(numbers[ind] > number){
                number = numbers[ind];
                position = ind;
            }
        }
        return position;
    }

    /**
     * Определяет позицию наименьшего элемента в массиве целых чисел
     * @param numbers массив целых чисел
     * @return позиция наименьшего элемента
     */
    public int GetMinElementPosition(int[] numbers){
        if (numbers.length == 0){
            return -1;
        }

        int number = numbers[0];
        int position = 0;
        for(int ind = 1; ind < numbers.length; ind++){
            if(numbers[ind] < number){
                number = numbers[ind];
                position = ind;
            }
        }
        return position;
    }

    /**
     * Выводит в консоль результаты приближения экспоненты рядом Макларена N-oй степени
     * @param scanner
     */
    public void Method4(Scanner scanner) {
        Log("Method4: approximation of the exponential function value by the Maclaurin series of degree N:");

        Log("enter X  - the double number");
        double x = GetDoubleValue(scanner);

        Log("enter N > 0  - the natural number");
        int n = GetPositiveIntValue(scanner);

        System.out.printf("Math.exp(x) = %.0f \n", Math.exp(x));

        double[] degrees = GetArrayOfDegrees(x, n);
        Log("array of degrees: " + Arrays.toString(degrees));
        int[] factorials = GetArrayOfFactorials(n);
        Log("array of factorials: " + Arrays.toString(factorials));
        Log("Note: forming an array of powers and an array of factorials separately is a very bad approach to programming exponential calculations, because they lead to an overflow of numbers!!!");

        double result = 0;
        for(int ind = 0; ind <= n; ind++){
            result += degrees[ind]/factorials[ind];
        }

        System.out.printf("result of approximation = %.0f \n", result);
        Log("end");
    }

    /**
     * Формирует массив indicator + 1 элементов, состоящий из степеней числа basis
     * @param basis основание степени
     * @param indicator наибольший показатель степени в массиве
     * @return
     */
    public double[] GetArrayOfDegrees(double basis, int indicator){
        ArrayList<Double> list = new ArrayList<Double>();
        for (int ind = 0; ind <= indicator; ind++){
            list.add(Math.pow(basis, ind));
        }
        double[] result = list.stream().mapToDouble(d -> d).toArray();
        return result;
    }

    /**
     * Формирует массив indicator + 1 элементов, состоящий из факториалов,
     * в позиции 0 указан 1!
     * @param indicator наибольший показатель степени в массиве
     * @return
     */
    public int[] GetArrayOfFactorials(int indicator){
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        int number = 1;
        for (int ind = 1; ind <= indicator; ind++){
            number = number * ind;
            list.add(number);
        }
        int[] result = list.stream().mapToInt(n -> n).toArray();
        return result;
    }

    /**
     * Проверка работы метода соединения отсортированных массивов
     */
    public void Method5() {
        Log("Method5: merging sorted arrays");

        // генерим массивы
        Random r = new Random();
        int[] arr1 = GetRandomArray(r.nextInt(10) + 1);
        Arrays.sort(arr1);
        Log("array1: " + Arrays.toString(arr1));
        int[] arr2 = GetRandomArray(r.nextInt(10) + 1);
        Arrays.sort(arr2);
        Log("array2: " + Arrays.toString(arr2));

        // вызываем метод слияния, выводим результат
        int[] result = ConnectSortedArrays(arr1, arr2);
        Log("result array: " + Arrays.toString(result));

        Log("end");
    }

    private int[] GetRandomArray(int count){
        ArrayList<Integer> intList = new ArrayList<Integer>();
        Random r = new Random();
        for (int ind = 0; ind < count; ind++){
            intList.add(r.nextInt(100));
        }
        int[] result = intList.stream().mapToInt(i -> i).toArray();
        return result;
    }

    /**
     * Соединяет два отсортированных массива в отсортированный массив
     * @param arr1 первый отсортированный массив
     * @param arr2 второй отсортированный массив
     * @return
     */
    public int[] ConnectSortedArrays(int[] arr1, int[] arr2) {
        if (arr1.length == 0){
            return arr2;
        }
        if (arr2.length == 0){
            return arr1;
        }

        ArrayList<Integer> list = new ArrayList<Integer>();

        int ind1 = 0;
        int ind2 = 0;

        while (ind1 < arr1.length || ind2 < arr2.length){
            int number1 = ind1 < arr1.length ? arr1[ind1] : Integer.MAX_VALUE;
            int number2 = ind2 < arr2.length ? arr2[ind2] : Integer.MAX_VALUE;

            if (number1 < number2){
                list.add(number1);
                ind1++;
            } else {
                list.add(number2);
                ind2++;
            }
        }

        int[] result = list.stream().mapToInt(i -> i).toArray();
        return result;
    }

    private int GetPositiveIntValue(Scanner scanner) {
        int result;
        while (true) {
            if (scanner.hasNextInt()) {
                result = scanner.nextInt();
            } else {
                scanner.next();
                Log("this is not a natural number, please try again");
                continue;
            }
            if (result > 0){
                return result;
            } else {
                Log("number <= 0, please try again");
            }
        }
    }

    private double GetDoubleValue(Scanner scanner){
        double result;
        while (true) {
            if (scanner.hasNextDouble()) {
                result = scanner.nextDouble();
                return result;
            } else {
                scanner.next();
                Log("this is not a double number, please try again");
            }
        }
    }
}

