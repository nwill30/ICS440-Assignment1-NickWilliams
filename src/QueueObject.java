public class QueueObject {

    private QueueObject head;

    public void setNode(Object node) {
        this.node = node;
    }

    private Object node;
    private QueueObject tail;

    public QueueObject(Object node){
        this.node = node;
        this.head = null;
        this.tail = null;
    }

    public QueueObject(Object node, QueueObject head){
        this.node = node;
        this.head = head;
        this.tail = null;    }


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

}
