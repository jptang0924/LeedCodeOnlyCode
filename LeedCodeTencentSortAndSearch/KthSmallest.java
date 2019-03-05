package LeedCodeTencentSortAndSearch;

import java.util.Stack;

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
	 public static class TreeNode {
	     int val;
	     TreeNode left;
	     TreeNode right;
	     TreeNode(int x) { val = x; }
	 }
	 
	 public static int kthSmallest(TreeNode root, int k) {
		 TreeNode ans = new TreeNode(0);
		 inOrderRecursive(root, k, ans);
		 return ans.val;
	 }
	 
	 //�������
	 private static int inOrderRecursive(TreeNode root, int count, TreeNode ans){
		 if(root!=null && count>0){
			 count = inOrderRecursive(root.left, count, ans);
			 count--;
			 if(count==0){
				 ans.val = root.val;
				 return count;
			 }
			 count = inOrderRecursive(root.right, count, ans);
		 }
		 return count;
	 }
	 
	 //�����������
	 private static int inOrderTraversal(TreeNode root, int k){
         Stack<TreeNode> stack = new Stack<>();
         TreeNode temp = root;
	 	 int count = 0;
	 	 while(temp!=null || !stack.isEmpty()){
	 	     while (temp!=null){
	 	         stack.push(temp);
	 	         temp = temp.left;
             }
	 	     if(!stack.isEmpty()){
                 TreeNode pop = stack.pop();
                 count++;
                 if(count==k){
                     return pop.val;
                 }
	 	         temp = pop.right;
             }
		 }
	 	 return 0;
	 }
}
