package acm;

import java.util.Scanner;

/**
 * Created by Administrator on 2016/9/27.
 */
public class SortListProblem implements Problem {

    private ListNode list;

    public SortListProblem() {
        this.list = createList();
    }

    public ListNode mergeList(ListNode head1, ListNode head2) {
        ListNode head = new ListNode(-1);
        ListNode newtail = head;
        while (head1 != null&& head2 != null) {
            if(head1.val < head2.val){
                newtail.next = head1;
                head1 = head1.next;
            }else {
                newtail.next = head2;
                head2 = head2.next;
            }
            newtail = newtail.next;
        }
        if(head1 == null)
            newtail.next = head2;
        if(head2 == null)
            newtail.next = head1;
        return head.next;

    }

    public ListNode getMiddle(ListNode head) {
        ListNode fast = head,slow=head;
        while (fast.next != null && fast.next.next != null) {
            fast=fast.next.next;
            slow = slow.next;
        }

        fast = slow.next;
        slow.next = null;
        return fast;
    }

    public ListNode sortList(ListNode head) {
        if(head == null|| head.next == null)
            return head;
        ListNode mid;
        mid = getMiddle(head);

        head = sortList(head);
        mid = sortList(mid);

        head = mergeList(head,mid);

        return head;
    }

    @Override
    public void solve() {
        list = sortList(list);
    }

    @Override
    public void printResult() {
        while (list != null) {
            System.out.print(list.val+" ");
            list= list.next;
        }
        System.out.println();
    }

    public ListNode createList() {
        ListNode head = new ListNode(-1);
        ListNode tail = head;
        Scanner scanner = new Scanner(System.in);
        int val;
        System.out.println("Please Input Number Seq:");
        while (scanner.hasNextInt()) {
            val = scanner.nextInt();
            ListNode node = new ListNode(val);
            tail.next = node;
            tail= tail.next;
        }
        return head.next;

    }
}
