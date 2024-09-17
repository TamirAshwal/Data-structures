public class LinkedList {
    private LinkedListNode head;
    private int length;
    public LinkedList(LinkedListNode node){
        this.head = node;
        this.length = 1;
    }
    public LinkedListNode getStart(){
        return this.head;
    }
    public void InsertElement(LinkedListNode node){

        while(head.getNext() != null){
            head = head.getNext();
        }
        head.setNext(node);
        node.setPrevious(head);
        this.length++;
    }
    public LinkedList deleteElement (LinkedListNode node) {
        LinkedListNode h = this.head;
        int data = node.getData();
        boolean found = false;
        while (h.getNext() != null) {
            if(h.getData() == data){
                if
            }
            h = h.getNext();
        }
    }
    public void searchData(LinkedList head, int data){
        int counter  = 0;
        while(head.next != null){
            if(head.data !=data){
                counter++;
                head = head.next;
                continue;
            }
            System.out.println("the node with the value " + data + " in the " + counter + " node in the list");
        }

    }




}
