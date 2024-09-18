//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//public class LinkedListTest {
//    private LinkedList list;
//
//    @Before
//    public void setUp() {
//        list = new LinkedList(new LinkedListNode(1));
//    }
//
//    @Test
//    public void testInsertNode() {
//        list.InsertNode(new LinkedListNode(2));
//        list.InsertNode(new LinkedListNode(3));
//        assertEquals(3, list.getStart().getNext().getNext().getData());
//    }
//
//    @Test
//    public void testSearchNode() {
//        list.InsertNode(new LinkedListNode(2));
//        list.InsertNode(new LinkedListNode(3));
//        LinkedListNode foundNode = list.searchNode(2);
//        assertNotNull(foundNode);
//        assertEquals(2, foundNode.getData());
//    }
//
//    @Test
//    public void testSearchNodeNotFound() {
//        assertNull(list.searchNode(5));
//    }
//
//    @Test
//    public void testDeleteNodeMiddle() {
//        list.InsertNode(new LinkedListNode(2));
//        list.InsertNode(new LinkedListNode(3));
//        list.deleteNode(2);
//        assertEquals(3, list.getStart().getNext().getData());
//    }
//
//    @Test
//    public void testDeleteNodeLast() {
//        list.InsertNode(new LinkedListNode(2));
//        list.InsertNode(new LinkedListNode(3));
//        list.deleteNode(3);
//        assertNull(list.getStart().getNext().getNext());
//    }
//
//    @Test
//    public void testDeleteNodeOnly() {
//        list.deleteNode(1);
//        assertNull(list.getStart());
//    }
//
//    @Test
//    public void testDeleteNodeNotFound() {
//        list.InsertNode(new LinkedListNode(2));
//        list.deleteNode(3);
//        assertEquals(2, list.getStart().getNext().getData());
//    }
//}