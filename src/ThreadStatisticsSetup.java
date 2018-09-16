public class ThreadStatisticsSetup {

    private static Queue<Queue> queueCollection;

    public ThreadStatisticsSetup(){
        queueCollection = new Queue<>();
    }

    public void add(String name) {
        queueCollection.enqueue(name);
    }

    public void replaceNode(String name, Queue queue) {
        queueCollection.setValue(name, queue);
    }

    private static double CalculateStatistics(Integer colorQueue, Integer totalQueue) {
        double numbrator = colorQueue;
        double denominator = totalQueue;
        double returnStats = numbrator / denominator;
        return returnStats * 100;
    }

    public static void print() {
        Integer totalCount = 0;
        Integer[] colorTotals = {0,0,0,0,0};
        String[] colors = {"Red","Brown","Yellow","Green","Blue"};
        for (int i = 0; i < queueCollection.getSize(); i++) {
            Queue<Integer> privateQueue = (Queue)queueCollection.getHeadAndDequeue().getNode();
            totalCount = totalCount + (Integer) privateQueue.getTail().getNode();
            for (int j = 0; j < privateQueue.getSize()-1; j++) {
                Integer colorCount = (Integer) privateQueue.getHeadAndDequeue().getNode();
                double colorStats = CalculateStatistics(colorCount, (Integer) privateQueue.getTail().getNode());
                System.out.println(String.format("Tabulator: %s Count %s for color %s = %s",i, colorCount.toString(),colors[j], colorStats+""));
                colorTotals[j] = colorTotals[j] + colorCount;
            }
        }

        System.out.println("Total Count " + totalCount );
        System.out.println("Color "+colors[0]+" composes " + CalculateStatistics(colorTotals[0], totalCount) + "% of the total");
        System.out.println("Color "+colors[1]+" composes " + CalculateStatistics(colorTotals[1], totalCount) + "% of the total");
        System.out.println("Color "+colors[2]+" composes " + CalculateStatistics(colorTotals[2], totalCount) + "% of the total");
        System.out.println("Color "+colors[3]+" composes " + CalculateStatistics(colorTotals[3], totalCount) + "% of the total");
        System.out.println("Color "+colors[4]+" composes " + CalculateStatistics(colorTotals[4], totalCount) + "% of the total");

    }



}
