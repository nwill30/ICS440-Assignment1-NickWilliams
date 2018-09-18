public class ThreadStatisticsSetup {

    private static Queue<Queue> queueCollection;

    /**
     * Constructor creates a queue of queues
     * */
    public ThreadStatisticsSetup(){
        queueCollection = new Queue<>();
    }

    /**
     * Adds new objects to the collections of queues
     * @param newValue though a collection of queues, any object may be provieded
     * */
    public void add(Object newValue) {
        queueCollection.enqueue(newValue);
    }

    /**
     * Update existing queue value with Queue object
     * @param name the name of the queueCollection object to be replaced
     *             @param queue the new queue
     * */
    public void updateNode(String name, Queue queue) {
        queueCollection.setValue(name, queue);
    }

    /**
     * Converts Integer objects to double and calculates
     * */
    private static double calculateStatistics(Integer colorQueue, Integer totalQueue) {
        double numbrator = colorQueue;
        double denominator = totalQueue;
        double returnStats = numbrator / denominator;
        return returnStats * 100;
    }

    /**
     * Each list from the joined threads is processed individually
     * Their color counts added to teh colorTotals array and the totalCount increased by the sum of each list
     * While processing each threads individual results are calculated and displayed before being added to the total lists(s)
     * The overall results are dusplayed.
     * */
    public static void print() {
        Integer totalCount = 0;
        Integer[] colorTotals = {0,0,0,0,0};
        String[] colors = {"Red","Brown","Yellow","Green","Blue"};
        for (int i = 0; i < queueCollection.getSize(); i++) {
            Queue<Integer> privateQueue = (Queue)queueCollection.getHeadAndDequeue().getNode();
            totalCount = totalCount + (Integer) privateQueue.getTail().getNode();
            for (int j = 0; j < privateQueue.getSize()-1; j++) {
                Integer colorCount = (Integer) privateQueue.getHeadAndDequeue().getNode();
                double colorStats = calculateStatistics(colorCount, (Integer) privateQueue.getTail().getNode());
                System.out.println(String.format("Tabulator: %s Count %s for color %s = %s",i, colorCount.toString(),colors[j], colorStats+""));
                colorTotals[j] = colorTotals[j] + colorCount;
            }
        }

        System.out.println("Total Count " + totalCount );
        System.out.println("Color "+colors[0]+" composes " + calculateStatistics(colorTotals[0], totalCount) + "% of the total");
        System.out.println("Color "+colors[1]+" composes " + calculateStatistics(colorTotals[1], totalCount) + "% of the total");
        System.out.println("Color "+colors[2]+" composes " + calculateStatistics(colorTotals[2], totalCount) + "% of the total");
        System.out.println("Color "+colors[3]+" composes " + calculateStatistics(colorTotals[3], totalCount) + "% of the total");
        System.out.println("Color "+colors[4]+" composes " + calculateStatistics(colorTotals[4], totalCount) + "% of the total");

    }



}
