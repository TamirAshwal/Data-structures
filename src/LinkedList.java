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
    public void InsertNode(LinkedListNode node){
        LinkedListNode n = this.head;
        // go to the last element
        while(n.getNext() != null){
            n = n.getNext();
        }
        n.setNext(node);
        node.setPrevious(n);
        this.length++;
    }
    public LinkedListNode searchNode(int data){
        LinkedListNode h = this.head;
        for(int i = 0; i < this.length; i++){
            if(h.getData() == data){
                return h;
            }
            h = h.getNext();
        }
        System.out.println("the node is not in the linked list returning the head of the list");
        return null;
    }
    public void deleteNode(int data){
        LinkedListNode nodeToDelete = searchNode(data);
        if(nodeToDelete == null){
            System.out.println("the node is not in the list");
            return;
        }
        else{
            // check if it is the only node
            if(length == 1){
                this.head = null;
                length--;
                return;
            }
            // check if it is the last node
            else if(nodeToDelete.getNext() == null){
                LinkedListNode tmp = nodeToDelete.getPrevious();
                tmp.setNext(null);
                nodeToDelete.setPrevious(null);
                length--;
                return;
            }
            else{
                LinkedListNode tmp = nodeToDelete.getPrevious();
                tmp.setNext(nodeToDelete.getNext());
                nodeToDelete.getNext().setPrevious(tmp);
                length--;
                return;
            }
        }
    }
}
