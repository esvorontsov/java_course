import java.util.HashMap;
import java.util.Map;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        DayPrinter dayPrinter = new DayPrinter();

        System.out.println("Запускаем потоки");
        HashMap<Integer, PrintDaysThread> threads = new HashMap<>(12);
        for (int ind = 1; ind <= 12; ind++){
            PrintDaysThread th = new PrintDaysThread(ind, dayPrinter);
            threads.put(ind, th);
            th.start();
        }

        // спим 30 сек
        Thread.sleep(30000);

        // запускаем завершение потоков
        dayPrinter.finish();

        // ждем завершения потоков
        for (int ind = 1; ind <= 12; ind++){
            threads.get(ind).join();
        }

        System.out.println("Все потоки завершены");
    }
}
