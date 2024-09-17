public class Stack {
    private int[] arr;
    private int numberOfElements;
    public Stack(int []arr){

    }
    private void setStack(int[] arr){
        this.arr = arr;
        this.numberOfElements = 0;
    }
    public Stack(int size){
        int[] arr = new int[size];
        setStack(arr);
    }
    public boolean isFull(){
        return this.numberOfElements == this.arr.length;
        }

    public boolean isEmpty(){
        return this.numberOfElements == 0;
    }
    public int getSize() {
        return this.numberOfElements;
    }
    public void push(int x){
        if(isFull()){
            System.out.println("the stack is full");
            return;
        }
        System.out.println("pushing " + x + " to the stack ");
        this.arr[this.numberOfElements] = x;
        this.numberOfElements++;
    }
    public int pop(){
        if(isEmpty()){
            System.out.println("the stack is empty");
        }
        int x = this.arr[numberOfElements -1];
        numberOfElements--;
        return x;
    }
    public int topOfStack(){
        System.out.println("this is the top element in the stack: " + this.arr[numberOfElements - 1]);
        return this.arr[this.numberOfElements -1];
    }
    public void printStack(){
        int num = this.numberOfElements;
        if(num == 0){
            System.out.println("stack is empty");
            return;
        }
        while(numberOfElements > 0){
            System.out.println(this.arr[numberOfElements - 1]);
            this.numberOfElements--;
        }
    }
    public static void main(String[] arg){
        Stack s = new Stack(5);
        s.push(1);
        s.push(2);
        s.push(4);
        s.push(8);
        s.push(16);
        s.topOfStack();
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.getSize());
        s.push(32);
        s.push(64);
        s.push(128);
        s.push(256);
        s.printStack();
    }
}
