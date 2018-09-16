import java.util.concurrent.locks.ReentrantLock;

public class RequestProcessor implements Runnable {

    private Queue<Integer> collection;
    private ReentrantLock collectionLock = new ReentrantLock();
    private ReentrantLock threadStatisticsLock = new ReentrantLock();
    private ThreadStatisticsSetup threadStatistics = new ThreadStatisticsSetup();
    private ThreadLocal<Queue> threadPrivate;


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        threadPrivate = new ThreadLocal<>();
        threadStatisticsLock.lock();
        try {
            threadPrivate.set(threadStatistics.createThreadPrivate());
        } finally {
            threadStatisticsLock.unlock();
        }

        ProcessCollection();
    }

    public RequestProcessor(Queue<Integer> collection) {
        this.collection = collection;

    }


    private synchronized void ProcessCollection() {
        Integer processUnit;
        boolean process = true;
        while (process) {
            collectionLock.lock();
            try {
                if (this.collection.getHead() == null) {
                    processUnit = -1;
                } else {
                    processUnit = (Integer) collection.getHeadAndDequeue().getNode();
                }
            } finally {
                collectionLock.unlock();
            }
            if (processUnit >= 0) {
//                System.out.println(Thread.currentThread().getName() + " count : " + processUnit);
                CountUnit(processUnit);

            } else {
                process = false;
            }
        }
    }

    private void CountUnit(Integer processUnit) {

        try {
            threadPrivate.get().setIndexValue(processUnit, (Integer) threadPrivate.get().getIndexValue(processUnit).getNode() + 1);
        } catch (NullPointerException e) {
            System.out.println("Thread: " + Thread.currentThread().getName() + "");
        }
        threadPrivate.get().setIndexValue(5, (Integer) threadPrivate.get().getIndexValue(5).getNode() + 1);
    }
}
