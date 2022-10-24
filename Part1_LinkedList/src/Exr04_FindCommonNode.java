/*
    4.  可能有环的两个链表，判断其是否相交，若相交求其交点
    面试题 02.07 链表相交
    https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/
    剑指offer 52 两个链表的第一个公共节点
    https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
    142 环形链表2
    https://leetcode.cn/problems/linked-list-cycle-ii/
 */

public class Exr04_FindCommonNode {
    public static class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node hasLoop(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node f = head;
        Node s = head;
        f = f.next.next;
        s = s.next;
        while (f != s) {
            if (f.next == null || f.next.next == null || s.next == null) {
                return null; // no loop
            }
            f = f.next.next;
            s = s.next;
        }
        f = head;
        while (f != s) {
            f = f.next;
            s = s.next;
        }
        return f;
    }

    public static Node loopIntersect(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1 != loop1) {
            cur1 = cur1.next;
            n++;
        }
        while (cur2 != loop2) {
            cur2 = cur2.next;
            n--;
        }
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        int m = Math.abs(n);
        while(m>0){
            cur1 = cur1.next;
            m--;
        }
        while(cur1 != cur2 && cur1 != loop1 && cur2 != loop2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        if (cur1 == cur2){
            return cur1;
        }
        Node find = cur2.next;
        while(find != cur2 && find != cur1){
            find = find.next;
        }
        if (find == cur1){
            return cur1;
        }else{
            return null;
        }
    }

    public static Node noLoopIntersect(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1 != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2 != null) {
            n--;
            cur2 = cur2.next;
        }
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        int sub = Math.abs(n);
        while (sub > 0) {
            cur1 = cur1.next;
            sub--;
        }
        while (cur1 != cur2 && cur1 != null & cur2 != null) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        if (cur1 == null || cur2 == null) {
            return null;
        } else {
            return cur1;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        Node loop1 = hasLoop(head1);
        Node loop2 = hasLoop(head2);
        if (loop1 == null && loop2 == null){
            return noLoopIntersect(head1, head2);
        }
        if (loop1 != null && loop2 != null){
            return loopIntersect(head1, loop1, head2, loop2);
        }
        return null;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).val);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).val);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).val);

    }


}
