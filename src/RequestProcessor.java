import java.util.concurrent.locks.ReentrantLock;

public class RequestProcessor implements Runnable {

    private static Queue<Integer> collection;
    private static ReentrantLock collectionLock = new ReentrantLock();
    private static ReentrantLock threadStatisticsCreateQueueLock = new ReentrantLock();
    private static ReentrantLock threadStatisticsUpdateQueueLock = new ReentrantLock();
    private static ReentrantLock threadStatisticsOutputQueueLock = new ReentrantLock();
    private static ThreadStatisticsSetup threadStatistics = new ThreadStatisticsSetup();
    private static ThreadLocal<Queue> threadPrivate = new ThreadLocal<>();
    private static ThreadLocal<Integer> processUnit = new ThreadLocal<>();



    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        setThreadPrivate();
        processUnit.set(0);

        ProcessCollection();
    }

    private void setThreadPrivate(){
        Queue newThreadLocalQueue = new Queue();
        for(int collectionSize = 0; collectionSize <= 5; collectionSize++){
            newThreadLocalQueue.enqueue(0);
        }
        threadPrivate.set(newThreadLocalQueue);
        threadStatisticsCreateQueueLock.lock();
        try {
            threadStatistics.add(Thread.currentThread().getName());
        }finally {
            threadStatisticsCreateQueueLock.unlock();
        }

    }


    public RequestProcessor(Queue<Integer> collection) {
        this.collection = collection;
    }

    private void ProcessCollection() {

        while (this.processUnit.get()>=0) {
            collectionLock.lock();
            try {
                if (collection.getHead() == null) {
                    processUnit.set(-1);
                } else {
                    this.processUnit.set((Integer) collection.getHeadAndDequeue().getNode());
                }
            } finally {
                collectionLock.unlock();
            }
            if (this.processUnit.get() >= 0) {
//                System.out.println(Thread.currentThread().getName() + " count : " + processUnit);
                CountUnit();
            }else{
                setStatisticsOutput();
            }
        }
    }

    private void setStatisticsOutput() {
        threadStatisticsOutputQueueLock.lock();
        try{
            threadStatistics.replaceNode(Thread.currentThread().getName(),this.threadPrivate.get());
        }finally {
            threadStatisticsOutputQueueLock.unlock();
        }
    }

    private void CountUnit() {

        threadStatisticsUpdateQueueLock.lock();
        try {
            int index = this.processUnit.get();
            int oldValue = (Integer) this.threadPrivate.get().getIndexValue(index).getNode();
            int oldTotal = (Integer) this.threadPrivate.get().getTail().getNode();
            this.threadPrivate.get().setIndexValue(index, oldValue + 1);
            this.threadPrivate.get().setIndexValue(5, oldTotal + 1);
        }finally {
            threadStatisticsUpdateQueueLock.unlock();
        }

    }
}
