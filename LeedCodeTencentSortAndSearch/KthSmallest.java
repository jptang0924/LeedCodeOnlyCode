package LeedCodeTencentSortAndSearch;

/**
 * �����������е�KС��Ԫ��
 * ����һ����������������дһ������ kthSmallest ���������е� k ����С��Ԫ�ء�
 * ˵����
 * ����Լ��� k ������Ч�ģ�1 �� k �� ����������Ԫ�ظ�����
 * ʾ�� 1:
 * ����: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * ���: 1
 * ʾ�� 2:
 * ����: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * ���: 3
 * ���ף�
 * ��������������������޸ģ�����/ɾ����������������ҪƵ���ز��ҵ� k С��ֵ���㽫����Ż� kthSmallest ������
 * @author jptang
 *
 */
public class KthSmallest {
	
	 //Definition for a binary tree node.
	 public class TreeNode {
	     int val;
	     TreeNode left;
	     TreeNode right;
	     TreeNode(int x) { val = x; }
	 }
	 
	 public static int kthSmallest(TreeNode root, int k) {
		 TreeNode ans = new TreeNode(0);
		 inOrder(root, k, ans);
		 return ans.val;
	 }
	 
	 //�������
	 private static int inOrder(TreeNode root, int count, TreeNode ans){
		 if(root!=null && count>0){
			 count = inOrder(root.left, count, ans);
			 count--;
			 if(count==0){
				 ans.val = root.val;
				 return count;
			 }
			 count = inOrder(root.right, count, ans);
		 }
		 return count;
	 }
	 
	 //�����������
	 private static void inOrder2(TreeNode root, int count, TreeNode ans){
		 
	 }
}
