
public class PrintDaysThread extends Thread {
    private int monthNumber;
    private DayPrinter dayPrinter;

    public PrintDaysThread(int monthNumber, DayPrinter dayPrinter){
        this.monthNumber = monthNumber;
        this.dayPrinter = dayPrinter;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                dayPrinter.Execute(monthNumber);
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public String toString(){
        return String.format("поток %d", monthNumber);
    }
}

