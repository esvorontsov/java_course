public class DayPrinter {
    private int monthNumber = 1;
    private boolean isFinished = false;

    public synchronized void finish(){
        System.out.println("поступила команда на остановку потоков");
        isFinished = true;
    }

    public synchronized void Execute(int monthNum) throws InterruptedException {
        // завершение вывода
        if (isFinished) {
            if (monthNumber == monthNum) {
                System.out.println(String.format("завершаем %s", Thread.currentThread().toString()));
                Thread.currentThread().interrupt();
                nextMonth();
            }
            return;
        }

        // проверка потока на соответствие месяца
        if (monthNumber != monthNum){
            return;
        }

        // вывод дней месяца в консоль, считаем что в каждом месяце 30 дней
        System.out.print(String.format("месяц %d: ", monthNumber));
        for (int i = 1; i < 31; i++){
            System.out.print(String.format(" %d", i));
            try {
                Thread.currentThread().sleep(50);
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();

        // переходим на следущий месяц
        nextMonth();
    }

    private void nextMonth(){
        monthNumber = monthNumber == 12 ? 1 : monthNumber + 1;
    }
}

