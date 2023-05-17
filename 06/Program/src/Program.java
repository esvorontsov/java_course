import ru.isys.trainings.task6.UnsupportedArraySizeException;
import ru.isys.trainings.task6.WrongArrayDataException;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        System.out.println("run check array:");
        try
        {
            checkArray(new String[][] {{"1", "2", "3"}, {"1", "2", "3"}, {"1", "2", "3"}});
        }
        catch (WrongArrayDataException ex){
            System.out.println(ex.getMessage());
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.println();

        // играем
        System.out.println("now let's play!");
        Scanner scanner = new Scanner(System.in);
        Player player = new Player(scanner);

        while (true)
        {
            player.play();

            System.out.println("let's play again? (y/n)");
            if (scanner.nextLine().toLowerCase() != "y"){
                return;
            }
        }
    }

    private static void checkArray(String[][] array) throws WrongArrayDataException {
        int n = array.length;
        int summa = 0;

        for (int i = 0; i < array.length; i++){
            // проверяем совпадение размерностей
            if (array[i].length != n){
                throw new UnsupportedArraySizeException(n, array[i].length);
            }

            // суммируем
            for(int j = 0; j < array[i].length; j++){
                try
                {
                    int number = Integer.parseInt(array[i][j]);
                    summa += number;
                }
                catch (Exception ex)
                {
                    throw new WrongArrayDataException(i, j, array[i][j]);
                }
            }
        }
        System.out.println(String.format("summa =  %d", summa));
    }
}
