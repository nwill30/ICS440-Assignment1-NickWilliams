import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Code referenced from https://docs.oracl.com/javase/7/docs/api/java/lang/ThreadLocal.html
 * */
public class ThreadID {

    private static final AtomicInteger nextID = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>() {
        @Override protected Integer initialValue(){
            return nextID.getAndIncrement();
        }
    };

    public static int get(){
        return threadId.get();
    }
}
