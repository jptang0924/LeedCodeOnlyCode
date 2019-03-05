package LeedCodeTencentSortAndSearch;

import java.util.Stack;

/**
 * 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 1
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 3
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
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
	 
	 //中序遍历
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
	 
	 //中序遍历迭代
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
