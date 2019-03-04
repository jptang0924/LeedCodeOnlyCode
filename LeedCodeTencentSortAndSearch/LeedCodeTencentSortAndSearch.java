package LeedCodeTencentSortAndSearch;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LeedCodeTencentSortAndSearch {

	/**
	 * 搜索旋转排序数组
	 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
	 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
	 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
	 * 你可以假设数组中不存在重复的元素。
	 * 你的算法时间复杂度必须是 O(log n) 级别。
	 * 示例 1:
	 * 输入: nums = [4,5,6,7,0,1,2], target = 0
	 * 输出: 4
	 * 示例 2:
	 * 输入: nums = [4,5,6,7,0,1,2], target = 3
	 * 输出: -1
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int search(int[] nums, int target) {
		int l=0, r=nums.length-1;
        while(l<=r){
        	if(target==nums[l]) return l;
        	if(target==nums[r]) return r;
        	int mid = (l+r)/2;
        	if(target==nums[mid]) return mid;
        	if(nums[l]<nums[mid]){
        		if(target<nums[mid] && target>nums[l]){
        			r = mid-1;
        		} else{
        			l = mid+1;
        		}
        	} else{
        		if(target>nums[mid] && target<nums[l]){
        			l = mid+1;
        		} else{
        			r = mid-1;
        		}
        	}
        }
		return -1;
    }
	//序列为两个升序，给后面的升序（都小于第一个元素）加一个权值，使序列变为一个升序，然后二分查找
	public static int search2(int[] nums, int target) {
		int l=0, r=nums.length-1;
		while(l<=r){
			int mid = (l+r) >> 1;
			if(target==nums[mid])	return mid;
			if(val(nums[mid], nums[0]) > val(target, nums[0])) r = mid-1;
			else	l = mid+1;
		}
		return -1;
    }
	private static int val(int x, int l){
		return x < l ? x + 0x33ffffff : x;
	}
	
	/**
	 * 数组中的第K个最大元素
	 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
	 * 示例 1:
	 * 输入: [3,2,1,5,6,4] 和 k = 2
	 * 输出: 5
	 * 示例 2:
	 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
	 * 输出: 4
	 * 说明:你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
	 * @param nums
	 * @param k
	 * @return
	 */
	//排序 ：时间复杂度 O(NlogN)，空间复杂度 O(1)
	public static int findKthLargest(int[] nums, int k) {
		if(nums.length<k) return 0;
        Arrays.sort(nums);
		return nums[nums.length-k];
    }
	//堆排序 ：时间复杂度 O(NlogK)，空间复杂度 O(K)。
	public static int findKthLargest2(int[] nums, int k) {
		PriorityQueue<Integer> que = new PriorityQueue();//小顶堆
		for(int val : nums){
			que.add(val);
			if(que.size()>k){//只保留最大的k个元素
				que.poll();
			}
		}
		return que.peek();
    }
	//快速选择 (快排变体)：时间复杂度 O(N)，空间复杂度 O(1)
	public static int findKthLargest3(int[] nums, int k) {
		int l=0, r=nums.length-1;
		while(l<r){
			int mid = partition(nums, l, r);
			if(mid==k-1)	break;
			else if(mid>k-1)	r = mid-1;
			else	l = mid+1;
		}
		return nums[k-1];
    }
	private static int partition(int[] nums, int left, int right){
		int index = left;
		int base = nums[index];
		swap(nums, index, left);
		int l = left;
		int r = right;
		while(l<r){
			while(l<r && nums[l]>=base)	l++;
			if(l<r){
				swap(nums, l, r);
				r--;
			}
			while(l<r && nums[r]<=base)	r--;
			if(l<r){
				swap(nums, l, r);
				l++;
			}
		}
		if(nums[l]<base)	l--;	
		swap(nums, index, l);
		return l;
	}
	private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

	
	public static void test(){
		Integer k = new Integer(4);
		a(k);
		System.out.println(k);
	}
	private static void a(Integer k){
		k = k-1;
	}
	
}
