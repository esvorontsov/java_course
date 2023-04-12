import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        Engine engine = new Engine();

        engine.Method1();
        System.out.println("");
        engine.Method2(scanner);
        System.out.println("");
        engine.Method3();
        System.out.println("");
        engine.Method4(scanner);
        System.out.println("");
        engine.Method5();
        System.out.println("");

        scanner.close();
    }

}
