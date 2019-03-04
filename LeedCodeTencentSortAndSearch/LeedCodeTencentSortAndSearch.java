package LeedCodeTencentSortAndSearch;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LeedCodeTencentSortAndSearch {

	/**
	 * ������ת��������
	 * ���谴�����������������Ԥ��δ֪��ĳ�����Ͻ�������ת��
	 * ( ���磬���� [0,1,2,4,5,6,7] ���ܱ�Ϊ [4,5,6,7,0,1,2] )��
	 * ����һ��������Ŀ��ֵ����������д������Ŀ��ֵ���򷵻��������������򷵻� -1 ��
	 * ����Լ��������в������ظ���Ԫ�ء�
	 * ����㷨ʱ�临�Ӷȱ����� O(log n) ����
	 * ʾ�� 1:
	 * ����: nums = [4,5,6,7,0,1,2], target = 0
	 * ���: 4
	 * ʾ�� 2:
	 * ����: nums = [4,5,6,7,0,1,2], target = 3
	 * ���: -1
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
	//����Ϊ�������򣬸���������򣨶�С�ڵ�һ��Ԫ�أ���һ��Ȩֵ��ʹ���б�Ϊһ������Ȼ����ֲ���
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
	 * �����еĵ�K�����Ԫ��
	 * ��δ������������ҵ��� k ������Ԫ�ء���ע�⣬����Ҫ�ҵ������������ĵ� k ������Ԫ�أ������ǵ� k ����ͬ��Ԫ�ء�
	 * ʾ�� 1:
	 * ����: [3,2,1,5,6,4] �� k = 2
	 * ���: 5
	 * ʾ�� 2:
	 * ����: [3,2,3,1,2,4,5,5,6] �� k = 4
	 * ���: 4
	 * ˵��:����Լ��� k ������Ч�ģ��� 1 �� k �� ����ĳ��ȡ�
	 * @param nums
	 * @param k
	 * @return
	 */
	//���� ��ʱ�临�Ӷ� O(NlogN)���ռ临�Ӷ� O(1)
	public static int findKthLargest(int[] nums, int k) {
		if(nums.length<k) return 0;
        Arrays.sort(nums);
		return nums[nums.length-k];
    }
	//������ ��ʱ�临�Ӷ� O(NlogK)���ռ临�Ӷ� O(K)��
	public static int findKthLargest2(int[] nums, int k) {
		PriorityQueue<Integer> que = new PriorityQueue();//С����
		for(int val : nums){
			que.add(val);
			if(que.size()>k){//ֻ��������k��Ԫ��
				que.poll();
			}
		}
		return que.peek();
    }
	//����ѡ�� (���ű���)��ʱ�临�Ӷ� O(N)���ռ临�Ӷ� O(1)
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
