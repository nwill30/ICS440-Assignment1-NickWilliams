import java.util.concurrent.locks.ReentrantLock;

public class RequestProcessor implements Runnable {

    private static Queue<Integer> collection;
    private static ReentrantLock collectionLock = new ReentrantLock();
    private static ReentrantLock threadStatisticsCreateQueueLock = new ReentrantLock();
    private static ReentrantLock threadStatisticsOutputQueueLock = new ReentrantLock();
    private static ThreadStatisticsSetup threadStatistics = new ThreadStatisticsSetup();
    private static ThreadLocal<Queue> threadPrivate = new ThreadLocal<>();
    private static ThreadLocal<Integer> processUnit = new ThreadLocal<>();

    /**
     * Create the RequestProcessor object
     *
     * @param collection receives and sets the shared collection to be processed by n threads
     */
    public RequestProcessor(Queue<Integer> collection) {
        this.collection = collection;
    }

    /**
     * Begin RequestProcessor thread
     * Prints thread name and requests a threadPrivate data space
     * Once the private data space has been created the thread begins processing the shared collection
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        setThreadPrivate();
        ProcessCollection();
    }

    /**
     * Builds newThreadLocalQueue object to hold the count of each color + the total
     * Sets the newThreadLocalQueue to the ThreadLocal<Queue> object
     * Thread locks the threadStatistics (outputList) collection and adds itself to the queue
     */
    private void setThreadPrivate() {
        Queue newThreadLocalQueue = new Queue();
        for (int collectionSize = 0; collectionSize <= 5; collectionSize++) {
            newThreadLocalQueue.enqueue(0);
        }
        threadPrivate.set(newThreadLocalQueue);
        threadStatisticsCreateQueueLock.lock();
        try {
            threadStatistics.add(Thread.currentThread().getName());
        } finally {
            threadStatisticsCreateQueueLock.unlock();
        }

    }

    /**
     * ThreadLocal processUnit contains the count to be processed from the collection
     * ProcessUnit set to 0 to begin while loop.
     */
    private void ProcessCollection() {
        processUnit.set(0);
        while (this.processUnit.get() >= 0) {
            collectionLock.lock(); //Locks collection to check list and dequeue objects to process
            try {
                if (collection.getHead() == null) { //if list is empty set processUnit < 0 to exit loop
                    processUnit.set(-1);
                } else {
                    this.processUnit.set((Integer) collection.getHeadAndDequeue().getNode());
                }
            } finally {
                collectionLock.unlock();
            }
            if (this.processUnit.get() >= 0) {
                CountUnit();
            } else {
                setStatisticsOutput();//Once collection has been emptied, report results
            }
        }
    }

    /**
     * Use processUnit to determine the index to count
     * threadPrivateQueue contains 5 indexes each corrisponding with a color the 5 index is the total
     * OldValue = the previous color value of the specified index
     * OldTotal = the previous total value of the threads processed units
     * Each are incamented
     * */
    private void CountUnit() {

        int index = this.processUnit.get();
        int oldValue = (Integer) this.threadPrivate.get().getIndexValue(index).getNode();
        int oldTotal = (Integer) this.threadPrivate.get().getTail().getNode();
        this.threadPrivate.get().setIndexValue(index, oldValue + 1);
        this.threadPrivate.get().setIndexValue(5, oldTotal + 1);
    }

    /**
     * The thread requests the output lock and replaces the threadStatistic node containing matching it's name
     * The threadPrivate queue data is placed in the node previously containing the threads name
     * */
    private void setStatisticsOutput() {
        threadStatisticsOutputQueueLock.lock();
        try {
            threadStatistics.updateNode(Thread.currentThread().getName(), this.threadPrivate.get());
        } finally {
            threadStatisticsOutputQueueLock.unlock();
        }
    }

}
