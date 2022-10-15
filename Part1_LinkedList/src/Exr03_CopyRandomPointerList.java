import java.util.HashMap;

/*
    3.  138.复制带随机指针的链表
    https://leetcode.cn/problems/copy-list-with-random-pointer/
 */
public class Exr03_CopyRandomPointerList {
    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }
    public static Node copyRandPointerList1(Node head){
        HashMap<Node, Node> hashMap = new HashMap<>();
        Node p = head;
        while(p != null){
            hashMap.put(p, new Node(p.value));
            p = p.next;
        }
        Node cur = head;
        for (int i = 0; i < hashMap.size(); i++){
            hashMap.get(cur).next = hashMap.get(cur.next);
            hashMap.get(cur).rand = hashMap.get(cur.rand);
            cur = cur.next;
        }
        return hashMap.get(head);
    }
    public static Node copyRandPointerList2(Node head){
        if (head == null){return null;}
        Node p = head.next;
        Node cur = head;
        // create node with a->a'->b->b'->c->c'->null;
        while (cur != null){
            cur.next = new Node(cur.value);
            cur.next.next = p;
            cur = p;
            p = p == null ? null : p.next;
        }
        // set random nodes for a', b', c';
        p = head; cur = head.next;
        while (cur != null){
            cur.rand = p.rand == null ? null : p.rand.next;
            cur = cur.next != null ? cur.next.next : null;
            p = p.next.next;
        }
        // split two lists;
        Node copyList = head.next;
        p = copyList;
        cur = head;
        while (cur != null){
            cur.next = cur.next == null? null : cur.next.next;
            cur = cur.next;
            p.next = cur == null ? null : cur.next;
            p = p.next;
        }
        return copyList;
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
        res1 = copyRandPointerList1(head);
        printRandLinkedList(res1);
        res2 = copyRandPointerList2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================1");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        System.out.println("=========================origin");
        printRandLinkedList(head);
        System.out.println("=========================method1");
        res1 = copyRandPointerList1(head);
        printRandLinkedList(res1);
        System.out.println("=========================method2");
        res2 = copyRandPointerList2(head);
        printRandLinkedList(res2);
        System.out.println("=========================final head");
        printRandLinkedList(head);
        System.out.println("=========================2");

    }


}
