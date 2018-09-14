
public class Main {

    public static void main(String[] args) {
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

//    String input = "This is a string list";
//        String[] inputLIst = input.split(" ");
//        Queue list = new Queue(inputLIst[0]);
//        for(int i = 1;i<5;i++){
//            list.enqueue(inputLIst[i]);
//        }


}
