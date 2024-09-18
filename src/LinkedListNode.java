public class LinkedListNode {
    private int data;
    private LinkedListNode next;
    private LinkedListNode previous;

    public LinkedListNode (int data) {
        this.data = data;
    }
    public void setNext(LinkedListNode node) {
        this.next = node;
    }
    public void setPrevious(LinkedListNode node) {
        this.previous = node;
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
}
