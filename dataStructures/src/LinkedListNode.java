public class LinkedListNode {
    private int data;
    private LinkedListNode next;
    private LinkedListNode previous;

    public LinkedListNode (int data) {
        this.data = data;
    }
    public LinkedListNode(int data, LinkedListNode next, LinkedListNode previous){
        this.data = data;
        this.next = next;
        this.previous = previous;
    }
    public int getData(){
        return this.data;
    }

    public LinkedListNode getNext() {
        return next;
    }

    public LinkedListNode getPrevious() {
        return previous;
    }

    public void setNext(LinkedListNode node) {
        this.next = node;
    }
    public void setPrevious(LinkedListNode node) {
        this.previous = node;
    }
}
