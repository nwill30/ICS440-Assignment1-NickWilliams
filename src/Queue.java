
public class Queue<I extends Object> {

    private QueueObject head;
    private QueueObject tail;
    private Integer size;

    /**
     * A newNode (object) is provided and placed in a QueueObject
     * The head and tail are set to the new object
     * @param newNode the new object provided to create the queue
     * */
    public Queue(Object newNode) {
        this.head = new QueueObject(newNode, null);
        this.tail = this.head;
        this.size = 1;
    }

    /**
     * a null queue constructor
     * */
    public Queue() {
        this.size = 0;
    }

    /**
     * If an object is provided to an empty queue it creates a new QueueObject
     * The new object is set to the head/tail
     * If the queue is not empty the node is added and the queue size in increased
     * @param newNode the node to be added to the queue
     * */
    public void enqueue(Object newNode) {
        if (this.size == 0) {
            this.head = new QueueObject(newNode, null);
            this.tail = this.head;
            this.size++;
        } else {
            add(newNode, this.tail);
            this.size++;
        }

    }

    /**
     * A new QueueObject is created with the object data provided
     * the current queue tail is set to the head of the new QueueObject
     * The tail value of the tail node is set to the new object
     * The current tail is the replaced with the new object
     * */
    private void add(Object newNode, QueueObject head) {
        QueueObject node = new QueueObject(newNode, head);
        this.tail.setTail(node);
        this.tail = node;
    }

    /**
     * If object remain in queue, retrieve tail object for current head as newHead
     * Set the queue head value to newHead and remove reference to oldHead value from newHead
     * */
    public void dequeue() {

        if (head.hasTail()) { //Check remaining queue object
            QueueObject newHead = head.getTail();
            head = newHead;
            head.setHead(null);//Removing oldHead reference destroys object
        } else {
            head = null;
        }
    }

    /**
     * retrieves the head value before dequeuing
     * */
    public QueueObject getHeadAndDequeue() {
        QueueObject returnHead = head;
        dequeue();
        return returnHead;
    }

    /**
     * Locates the object at the provided queue index and replaces updates the QueueObject data
     * @param index the supplied queue index
     *              @param value the new QueueObject data value
     * */
    public void setIndexValue(Integer index, Integer value) {
        if (this.size == 0 || index >= this.size) { //if queue is empty or requested index is outside of queue size throw error
            throw new IndexOutOfBoundsException();
        } else {
            Object searchObject = this.head;
            for (int i = 0; i < this.size; i++) {
                if (i == index) {
                    ((QueueObject) searchObject).setNode(value);
                    break;
                } else {
                    searchObject = ((QueueObject) searchObject).getTail();
                }
            }
        }
    }

    /**
     * Locates and returns the data from the QueueObject at the supplied index location
     * @param index index location of desired data
     * */
    public QueueObject getIndexValue(Integer index) {
        if (this.size == 0 || index >= this.size) { //if queue is empty or requested index is outside of queue size throw error
            throw new IndexOutOfBoundsException();
        } else {
            QueueObject returnObject = null;
            QueueObject searchObject = this.head;
            for (int i = 0; i < this.size; i++) {
                if (i == index) {
                    returnObject = searchObject;
                    break;
                } else {
                    searchObject = ((QueueObject) searchObject).getTail();
                }
            }
            return returnObject;
        }
    }

    /**
     * Locates a queue object by it's stored data
     * Replaces QueueObjects data with queue object
     * @param name the String value of the QueueObject node
     *             @param queue the queue object to store in the QueueObject node
     */
    public void setValue(String name, I queue) {

        Object searchObject = this.head;
        for (int i = 0; i < this.size; i++) {
            if (((QueueObject) searchObject).getNode().equals(name)) {
                ((QueueObject) searchObject).setNode(queue);
                break;
            } else {
                searchObject = ((QueueObject) searchObject).getTail();
            }
        }

    }

    public QueueObject getHead() {
        return head;
    }

    public QueueObject getTail() {
        return tail;
    }

    public Integer getSize() {
        return size;
    }
}
