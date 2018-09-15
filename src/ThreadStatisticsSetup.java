public class ThreadStatisticsSetup {

    private static Queue<Queue> threadPrivate;

    public ThreadStatisticsSetup(){

    }


    public Queue createThreadPrivate(){
        Queue privateQueue = new Queue();
        for(int collectionSize = 0; collectionSize <= 5; collectionSize++){
            privateQueue.enqueue(0);
        }
        threadPrivate.enqueue(privateQueue);
        return privateQueue;
    }

    private static Integer CalculateStatistics(Integer colorQueue, Integer totalQueue) {
        return colorQueue / totalQueue;
    }

    public static void print() {
        for(int i = 0;i < threadPrivate.getSize();i++){
            Queue privateQueue = (Queue) threadPrivate.returnHead();
            for(int j = 0; i < privateQueue.getSize();j++)
            System.out.println(String.format("Tabulator: %s Count %s for color Red = %s",i + 1, privateQueue.returnHead().toString() , CalculateStatistics(red.getSize(), total.getSize()).toString()));

        }
    }


}
