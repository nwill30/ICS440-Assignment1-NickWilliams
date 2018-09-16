
public class Queue<I extends Object> {

    private QueueObject head;
    private QueueObject tail;
    private Integer size;

    public Queue(Object newNode) {
        this.head = new QueueObject(newNode, null);
        this.tail = this.head;
        this.size = 0;
    }

    public Queue() {
        this.size = 0;
    }


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

    private void add(Object newNode, QueueObject head) {
        QueueObject node = new QueueObject(newNode, head);
        this.tail.setTail(node);
        this.tail = node;
    }


    public void dequeue() {

        if (head.hasTail()) {
            QueueObject newHead = head.getTail();
            head = newHead;
            head.setHead(null);
        } else {
            head = null;
        }
    }

    private void remove(QueueObject searchNode) {
        QueueObject tempHead = searchNode.getHead();
        QueueObject tempTail = searchNode.getTail();
        if (tempHead == null) {
            tempTail.setHead(null);
            this.head = tempTail;
        } else {
            tempHead.setTail(searchNode.getTail());
            tempTail.setHead(searchNode.getHead());
        }

    }

    public QueueObject getHeadAndDequeue() {
        QueueObject returnHead = head;
        dequeue();
        return returnHead;
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

    public void setIndexValue(Integer index, Integer value) {
        if (this.size == 0 || index >= this.size) {
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


    public QueueObject getIndexValue(Integer index) {
        if (this.size == 0 || index >= this.size) {
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
}
