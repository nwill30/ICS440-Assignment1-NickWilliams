import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class RequestProcessor implements Runnable {

    private Queue<Integer> collection;
    private ReentrantLock collectionLock = new ReentrantLock();
    private ReentrantLock threadStatisticsLock = new ReentrantLock();
    private ThreadStatisticsSetup threadStatistics;
    private ThreadLocal<Queue> threadPrivate;


    @Override
    public void run() {
        ThreadID.get();
        threadStatisticsLock.lock();
        try{
            threadPrivate.set(threadStatistics.createThreadPrivate());
        }finally {
            threadStatisticsLock.unlock();
        }
        ProcessCollection();
    }

    public RequestProcessor(Queue<Integer> collection) {
        this.collection = collection;
        threadStatistics = new ThreadStatisticsSetup();
    }


    private void ProcessCollection() {
        Integer processUnit;
        collectionLock.lock();
        try {
            if(this.collection.getHead()==null){
                processUnit = -1;
            }else{
                processUnit =  (Integer) this.collection.returnHead();
            }
        }finally {
            collectionLock.unlock();
        }
        if(processUnit >0){
            CountUnit(processUnit);
        }else{
            //complete thread
        }
    }

    private void CountUnit(Integer processUnit) {
        threadStatisticsLock.lock();
        try{
            threadPrivate.get().set(processUnit, (Integer) threadPrivate.get().get(processUnit) + 1);
        }finally {
            threadStatisticsLock.unlock();
        }
    }
}
