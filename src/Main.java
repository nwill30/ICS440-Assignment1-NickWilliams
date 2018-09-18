/**
 * ICS40 Assignment 1 Multi-thread M&M counter with supplied driver main class
 * Creates collection of n integers 0-5 representing M&M colors (Red, Brown, Yellow, Green, Blue)
 * Passes collection to RequestProcessor to calculate the occurrence of each color
 * The RequestProcessor class can run n threads to complete the computations on the single collection
 *
 * @author  Nick Williams
 * @since   2018-09-16
 */
public class Main {

    public static void main(String[] args) {
        /**
         * Supplied driver method by assignment, no edits made...
         * */
        Queue<Integer> collection = new Queue<Integer>();
        for (int index = 0; index < 10000; index++) {
            int candidate = ((int) (Math.random() * 10000)) % 5;
            Integer integ = new Integer(candidate);
            collection.enqueue(integ);
        }
        RequestProcessor processor = new RequestProcessor(collection);
        int numberOfThreads = 5;
        Thread[] threads = new Thread[numberOfThreads];
        for (int index = 0; index < threads.length; index++) {
            threads[index] = new Thread(processor);
            threads[index].start();
        }
        try {
            for (int index = 0; index < threads.length; index++) {
                threads[index].join();
            }
        } catch(InterruptedException ie) {
            ie.printStackTrace();
        }
        ThreadStatisticsSetup.print();
    }

}
