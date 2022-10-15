/*
    2. （非LC) 将单向链表按某值划分为左边小，中间相等，右边大的形式
*/
public class Exr02_LinkedListPartition {
    public static class Node {
        public int value;
        public Node next;

        public Node(int v) {
            this.value = v;
        }
    }

    public static void swap(Node[] n, int i, int j) {
        Node temp = n[i];
        n[i] = n[j];
        n[j] = temp;
    }

    public static void QuickSort(Node[] nl, int m) {
        int len = nl.length;
        int ll = -1;
        int br = len;
        int i = 0;
        while (i < br) {
            if (nl[i].value < m) {
                ll++;
                swap(nl, ll, i);
                i++;
            } else if (nl[i].value > m) {
                br--;
                swap(nl, i, br);
            } else if (nl[i].value == m) {
                i++;
            }
        }
    }

    public static Node listPartition1(Node head, int m) {
        int size = 0;
        Node p = head;
        while (p != null) {
            size++;
            p = p.next;
        }
        Node[] nodeList = new Node[size];
        int j = 0;
        while (head != null) {
            nodeList[j++] = head;
            head = head.next;
        }
        QuickSort(nodeList, m);
        for (int i = 0; i < size - 1; i++) {
            nodeList[i].next = nodeList[i + 1];
        }
        nodeList[size - 1].next = null;
        return nodeList[0];
    }

    public static Node listPartition2(Node head, int m) {
        Node SH = null;
        Node ST = null;
        Node EH = null;
        Node ET = null;
        Node LH = null;
        Node LT = null;
        Node p1 = head;
        while (p1 != null) {
            if (p1.value < m) {
                if (SH == null) {
                    SH = p1;
                    ST = p1;
                } else {
                    ST.next = p1;
                    ST = p1;
                }
            } else if (p1.value == m) {
                if (EH == null) {
                    EH = p1;
                    ET = p1;
                } else {
                    ET.next = p1;
                    ET = p1;
                }
            } else if (p1.value > m) {
                if (LH == null) {
                    LH = p1;
                    LT = p1;
                } else {
                    LT.next = p1;
                    LT = p1;
                }
            }
            p1 = p1.next;
        }
        if (ST != null) {
            ST.next = EH;
            ET = ET != null ? ET : ST;
        }
        if (ET != null){
            ET.next = LH;
        }
        LT.next = null;
        return SH != null ? SH : EH != null ? EH : LH;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        head1 = listPartition2(head1, 4);
//        head1 = listPartition1(head1, 5);
        printLinkedList(head1);
    }
}
