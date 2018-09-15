
public class Queue<I extends Object> {

    private QueueObject head;
    private QueueObject tail;
    private Integer size;

    public Queue( Object newNode){
        this.head = new QueueObject(newNode,null);
        this.tail = this.head;
        this.size = 0;
    }

    public Queue() {
        this.size = 0;
    }


    public void enqueue(Object newNode){
        if(this.size == 0){
            this.head = new QueueObject(newNode,null);
            this.tail = this.head;
            this.size++;
        }else{
            add(newNode, this.tail);
            this.size++;
        }

    }

    private void add(Object newNode, QueueObject head){
        QueueObject node = new QueueObject(newNode, head);
        this.tail.setTail(node);
        this.tail = node;
    }


    public void dequeue(Object node ){
        QueueObject searchNode = this.head;
        for(int i = 0;i<this.size;i++){
            if(searchNode.getNode() == node){
                remove(searchNode);
                break;
            }else{
                searchNode = searchNode.getTail();
            }
        }
    }

    private void remove(QueueObject searchNode) {
        QueueObject tempHead = searchNode.getHead();
        QueueObject tempTail = searchNode.getTail();
        tempHead.setTail(searchNode.getTail());
        tempTail.setHead(searchNode.getHead());

    }

    public Object returnHead() {
        Object returnHead = head.getNode();
        dequeue(head);
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

    public void set(Integer index, Integer value) {
        if(this.size == 0 || index >= this.size){
            throw new IndexOutOfBoundsException();
        }else{
            Object searchObject = this.head;
            for(int i = 0; i < this.size;i++){
                if(i == index){
                    ((QueueObject) searchObject).setNode(value);
                }else{
                    searchObject = ((QueueObject) searchObject).getTail();
                }
            }

        }
    }


    public Object get(Integer index) {
        if(this.size == 0 || index >= this.size){
            throw new IndexOutOfBoundsException();
        }else{
            Object returnObject = null;
            Object searchObject = this.head;
            for(int i = 0; i < this.size;i++){
                if(i == index){
                    returnObject = searchObject;
                }else{
                    searchObject = ((QueueObject) searchObject).getTail();
                }
            }
            return returnObject;
        }
    }
}
