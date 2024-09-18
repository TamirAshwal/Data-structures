import java.sql.Array;

public class TreeNode {
    private int data;
    private TreeNode right;
    private TreeNode left;
    private TreeNode parent;
    public TreeNode(int data){
        setTreeNode(data);
    }
    private void setTreeNode(int data){
        this.data = data;
        this.right = null;
        this.left = null;
        this.parent = null;
    }
    public void addTwoNodes(TreeNode r, TreeNode l){
        this.right = r;
        this.left = l;
    }
    public void addRightNode(TreeNode n){
        this.right = n;
    }
    public void addLeftNode(TreeNode n){
        this.left = n;
    }
    public void addParentNode(TreeNode n){
        this.parent = n;
    }
    public void printTreePreOrder(TreeNode root){
        if(root == null){
            return;
        }
        System.out.println(root.data);
        printTreePreOrder(root.left);
        printTreePreOrder(root.right);

    }
    public void printTreeInOrder(TreeNode root){
        if(root == null){
            return;
        }
        printTreeInOrder(root.left);
        System.out.println(root.data);
        printTreeInOrder(root.right);

    }
    public void printTreePostOrder(TreeNode root){
        if(root == null){
            return;
        }
        printTreePostOrder(root.left);
        printTreePostOrder(root.right);
        System.out.println(root.data);
    }
    public static int countNodes(TreeNode root){
        if (root == null){
            return 0;
        }
        else{
            return 1 + countNodes(root.right) + countNodes(root.left);
        }
    }
    public static void treeToArray(TreeNode root, int[] array, int[] index){
        if (root == null) {
            return;
        }
        // Traverse the left subtree
        treeToArray(root.left, array, index);
        // Store the current node value in the array
        array[index[0]++] = root.data;
        // Traverse the right subtree
        treeToArray(root.right, array, index);

    }
    public static void sortTree(TreeNode root){
        // first create an array with all the values in the tree using in order
        int numOfNodes = countNodes(root);
        int [] arr = new int[numOfNodes];
        if (root == null) {
            return;
        }
        int [] index = new int[1];
        treeToArray(root, arr, index);
        mergeSort(arr, numOfNodes);

    }
    private static void mergeSort(int[] arr, int size){
        if (size < 2) {
            return;
        }
        int mid = size / 2;
        int[] l = new int[mid];
        int[] r = new int[size - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = arr[i];
        }
        for (int i = mid; i < size; i++) {
            r[i - mid] = arr[i];
        }
        mergeSort(l, mid);
        mergeSort(r, size - mid);

        merge(arr, l, r, mid, size - mid);
    }
    public static void merge(int[] arr, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                arr[k++] = l[i++];
            }
            else {
                arr[k++] = r[j++];
            }
        }
        while (i < left) {
            arr[k++] = l[i++];
        }
        while (j < right) {
            arr[k++] = r[j++];
        }
    }
    public static void main(String[] args){
        // Create nodes
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        // Connect nodes to form a binary tree
        root.addLeftNode(node2);
        root.addRightNode(node3);

        node2.addLeftNode(node4);
        node2.addRightNode(node5);

        node3.addLeftNode(node6);
        node3.addRightNode(node7);

        // Set parent nodes
        node2.addParentNode(root);
        node3.addParentNode(root);
        node4.addParentNode(node2);
        node5.addParentNode(node2);
        node6.addParentNode(node3);
        node7.addParentNode(node3);

        // Print the tree structure
        System.out.println("Pre-order traversal of the binary tree:");
        root.printTreePreOrder(root);

        System.out.println("\nIn-order traversal of the binary tree:");
        root.printTreeInOrder(root);

        System.out.println("\nPost-order traversal of the binary tree:");
        root.printTreePostOrder(root);

        System.out.println("\nTotal number of nodes: " + root.countNodes(root));
        System.out.println("starting to sort the tree");
        sortTree(root);

    }

}
