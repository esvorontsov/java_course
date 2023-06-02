import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class Bank {

    private final ArrayList<Account> accounts = new ArrayList<>();

    public Bank(){
        // 10 счетов, по 1000 единиц на каждом
        for (int ind = 0; ind < 10; ind++){
            accounts.add(new Account(1000));
        }
    }

    //
    public Account getAccount(int ind) throws ArrayIndexOutOfBoundsException{
        var maxIndex = accounts.size() - 1;
        if (ind < 0 || ind >  accounts.size() - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return accounts.get(ind);
    }

    public int getBankSumma() {
        return accounts.stream()
                .map(a -> a.getValue())
                .reduce(0, (a, b) -> a + b);
    }

    public Callable<Boolean> getTransferTask(int from, int to, int delta) throws Exception {
        // проверка суммы на не отрицательность
        if (delta < 0 || from < 0 || to < 0 || from == to || from > accounts.size() - 1 || to > accounts.size() - 1){
            throw new Exception();
        }

        return new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                // счета
                Account acntFrom = getAccount(from);
                Account acntTo = getAccount(to);

                // блокировки счетов
                Lock fromLock = acntFrom.getLock();
                Lock toLock = acntTo.getLock();

                while (true) {
                    if (fromLock.tryLock(100, TimeUnit.MILLISECONDS)){
                        try {
                            // проверка возможности списания
                            if (acntFrom.getValue() < delta) {
                                return false;
                            }

                            if (toLock.tryLock(100, TimeUnit.MILLISECONDS)) {
                                try {
                                    // меняем значения счетов
                                    int fromBefore = acntFrom.update(-delta);
                                    int toBefore = acntTo.update(delta);
                                    Thread.currentThread().sleep(100);

                                    System.out.println(String.format(
                                            "перевод суммы %d со счета %d: %d -> %d на счет %d: %d -> %d, итого по банку: %d",
                                            delta, from, fromBefore, acntFrom.getValue(),
                                            to, toBefore, acntTo.getValue(), getBankSumma()));
                                    return true;
                                }
                                finally {
                                    toLock.unlock();
                                }
                            }
                        }
                        finally {
                            fromLock.unlock();
                        }
                    }
                }
            }
        };
    }
}
