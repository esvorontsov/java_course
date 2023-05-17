import java.util.Arrays;
import java.util.Scanner;

public class Player {
    private int min;
    private int max;
    private Scanner scanner;

    public Player(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Угадывание числа
     */
    public void play() {
        setRangeLimits();

        // игровой цикл
        while (true){
            if (min == max){
                System.out.println(String.format("your number = %d, game over, Thanks for the game!", min));
                System.out.println();
                return;
            }

            System.out.println(String.format("current search range[%d, %d]", min, max));
            int value = (min + max) / 2;
            System.out.println(String.format("your number = %d?", value));

            switch (getSignValue()) {
                case less:
                    max = value - 1 > min ? value - 1 : min;
                    break;
                case more:
                    min = value + 1 < max ? value + 1 : max;
                    break;
                default:
                    System.out.println(String.format("your number = %d, game over, Thanks for the game!", value));
                    System.out.println();
                    return;
            }
        }
    }

    /**
     * Запрашивает границы диапазона
     */
    private void setRangeLimits(){
        String str = "";

        // min
        while (true) {
            System.out.println("enter the lower bound of the range of natural numbers");
            try {
                str = scanner.nextLine();
                min = Integer.parseInt(str);
                break;
            } catch (Exception ex) {
                System.out.println(String.format("%s - invalid value, please try again", str));
            }
        }

        // max
        while (true) {
            System.out.println("enter the upper bound of the range of natural numbers");
            try
            {
                str = scanner.nextLine();
                max = Integer.parseInt(str);
                if (max < min){
                    System.out.println(String.format("the upper bound must exceed %d, please try again", min));
                    continue;
                }
                break;
            }
            catch (Exception ex){
                System.out.println(String.format("%s - invalid value, please try again", str));
            }
        }

        System.out.println(String.format("now guess a number from the range [%d, %d], I will determine it", min, max));
    }

    /**
     * Запрашивает подсказку пользователя
     * @return
     */
    private Sign getSignValue(){
        String str = "";
        while (true){
            System.out.println(String.format("enter one of the three words: %s", Arrays.asList(Sign.values())));
            try
            {
                str = scanner.nextLine();
                Sign sign = Sign.valueOf(str);
                return sign;
            }
            catch(Exception ex){
                System.out.println(String.format("%s - invalid value, please try again", str));
            }
        }
    }

    private enum Sign { more, less, equal}
}
