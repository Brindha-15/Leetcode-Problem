/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int[] ans = {-1, -1};

        ListNode prev = head;
        ListNode curr = head.next;

        int index = 1;
        int first = -1;
        int last = -1;
        int min = Integer.MAX_VALUE;

        while (curr.next != null) {
            // Check if current node is a critical point
            if ((curr.val > prev.val && curr.val > curr.next.val) ||
                (curr.val < prev.val && curr.val < curr.next.val)) {

                if (first == -1) {
                    first = index;
                } else {
                    min = Math.min(min, index - last);
                }

                last = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        if (first == last) {
            return ans;
        }

        ans[0] = min;
        ans[1] = last - first;

        return ans;
    }
}