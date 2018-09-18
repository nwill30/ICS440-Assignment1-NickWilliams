public class QueueObject {

    private QueueObject head;
    private Object node;
    private QueueObject tail;

    public void setNode(Object node) {
        this.node = node;
    }

    /**
     * Queue object constructs with a node generic object to contain any supplied object data
     * No supplied head or tail will result in a single unassociated queue object
     * @param node contains the data to be stored in QueueObject
     * */
    public QueueObject(Object node){
        this.node = node;
        this.head = null;
        this.tail = null;
    }

    /**
     * Queue object constructs with a node generic object to contain any supplied object data
     * No supplied tail will result in a singly linked object
     * @param node contains teh data to be stored in the QueueObject
     *             @param head contains a reference to the preceding object in the queue
     * */
    public QueueObject(Object node, QueueObject head){
        this.node = node;
        this.head = head;
        this.tail = null;    }

    /**
     * Queue object constructs with a node generic object to contain any supplied object data
     * @param node contains teh data to be stored in the QueueObject
     *             @param head contains a reference to theteh preceding object in the queue
     *                         @param tail contains a reference to the object following this.QueueObject in the queue
     * */
    public QueueObject(Object node, QueueObject head, QueueObject tail){
        this.node = node;
        this.head = head;
        this.tail = tail;
    }

    public QueueObject getHead() {
        return head;
    }

    public void setHead(QueueObject head) {
        this.head = head;
    }

    public QueueObject getTail() {
        return tail;
    }

    public void setTail(QueueObject tail) {
        this.tail = tail;
    }

    public Object getNode() {
        return node;
    }

    public boolean hasTail(){
        boolean check = true;
        if(tail == null){
            check = false;
        }
        return check;
    }

}
