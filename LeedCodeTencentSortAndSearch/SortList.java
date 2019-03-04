package LeedCodeTencentSortAndSearch;

/**
 * 排序链表
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * 示例 1:
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 * @author jptang
 *
 */
public class SortList {
	
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public static ListNode sortList(ListNode head) {
		return head==null ? null : mergeSort(head);
	}
	
	private static ListNode mergeSort(ListNode head){
		if(head.next==null)	return head;
		ListNode mid = getMid(head);
		ListNode l = mergeSort(head);
		ListNode r = mergeSort(mid);
		return merge(l, r);
	}
	
	private static ListNode getMid(ListNode head){
		ListNode pre = null, p = head, q = head; 
		while(q!=null && q.next!=null){
			pre = p;
			p = p.next;
			q = q.next.next;
		}
		pre.next = null;//截断链表为左右两个部分
		return p;
	}
	
	private static ListNode merge(ListNode l, ListNode r){
		ListNode ans = new ListNode(0);
		ListNode cur = ans;
		while(l!=null && r!=null){
			if(l.val <= r.val){
				cur.next = l;
				cur = cur.next;
				l = l.next;
			} else{
				cur.next = r;
				cur = cur.next;
				r = r.next;
			}
		}
		if(l!=null)	cur.next = l;
		if(r!=null)	cur.next = r;
		return ans.next;
	}
}
