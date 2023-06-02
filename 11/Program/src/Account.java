import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private final AtomicInteger value;
    private final ReentrantLock lock = new ReentrantLock();

    public Account(int value){
        this.value = new AtomicInteger(value);
    }

    public int getValue(){
        return value.get();
    }

    public int update(int delta){
        return value.getAndAdd(delta);
    }

    public ReentrantLock getLock(){
        return lock;
    }
}
