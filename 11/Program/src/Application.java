import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Application {
    public static void main(String[] args) throws Exception {
        Bank bank = new Bank();
        ExecutorService service = Executors.newFixedThreadPool(10);

        // создаем задачи
        ArrayList<Callable<Boolean>> tasks = new ArrayList<>();
        int count = 0;
        while (count < 10000){
            Random r = new Random();
            int from = r.nextInt(10);
            int to = r.nextInt(10);
            if (from != to){
                int delta = r.nextInt(11) + 10;
                tasks.add(bank.getTransferTask(from, to, delta));
                count++;
            }
        }

        // запускаем выполнение задач
        List<Future<Boolean>> results = service.invokeAll(tasks, 10, TimeUnit.SECONDS);

        // останавливаем сервис
        service.shutdown();
        System.out.println("сервис остановлен");
    }
}
