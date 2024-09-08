public class tester {
    public static void main (String [] args) {
        Object[] listInt = {2, 46, 4, 67, 2};
        SinglyLinkedList testList = new SinglyLinkedList(listInt);

        //testing getHead()
        System.out.println (testList.getHead());
        System.out.println (testList.getTail());
    }
}
