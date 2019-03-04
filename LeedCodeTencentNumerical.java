import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeedCodeTencentNumerical {

	/**
	 * 整数反转
	 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
	 * 示例 1:
	 * 输入: 123
	 * 输出: 321
	 * 示例 2:
	 * 输入: -123
	 * 输出: -321
	 * 示例 3:
	 * 输入: 120
	 * 输出: 21
	 * 注意:假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。
	 * 请根据这个假设，如果反转后整数溢出那么就返回 0。
	 * @param x
	 * @return
	 */
	public static int reverse(int x) {
        int ans = 0;
        while(x!=0){
        	int rem = x % 10;
        	x /= 10;
        	if(ans > 0 && ans > (Integer.MAX_VALUE-rem)/10) return 0;
        	if(ans < 0 && ans < (Integer.MIN_VALUE-rem)/10) return 0;
        	ans = ans * 10 + rem;
        }
		return ans;
    }
	
	/**
	 * 回文数
	 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
	 * 示例 1:
	 * 输入: 121
	 * 输出: true
	 * 示例 2:
	 * 输入: -121
	 * 输出: false
	 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
	 * 进阶:你能不将整数转为字符串来解决这个问题吗？
	 * @param x
	 * @return
	 */
	public static boolean isPalindrome(int x) {
        if(x<0)	return false;
        if(reverse(x) == x) return true;
		return false;
    }
	public static boolean isPalindrome2(int x) {
        if(x<0)	return false;
        String str = String.valueOf(x);
        String reverse = new StringBuilder(str).reverse().toString();
        if(str.equals(reverse))	return true;
		return false;
    }
	
	/**
	 * 只出现一次的数字
	 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
	 * 说明：
	 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
	 * 示例 1:
	 * 输入: [2,2,1]
	 * 输出: 1
	 * 示例 2:
	 * 输入: [4,1,2,1,2]
	 * 输出: 4
	 * @param nums
	 * @return
	 */
	public static int singleNumber(int[] nums) {
        //相同元素异或结果等于0
		int ans = 0;
		for(int i=0;i<nums.length;i++){
			ans ^= nums[i];
		}
		return ans;
    }
	
	/**
	 * 求众数
	 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
	 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
	 * 示例 1:
	 * 输入: [3,2,3]
	 * 输出: 3
	 * 示例 2:
	 * 输入: [2,2,1,1,1,2,2]
	 * 输出: 2
	 * @param nums
	 * @return
	 */
	public static int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int num = nums[0], numCount = 1;
        for(int i=1;i<nums.length;i++){
        	int temp=nums[i], tempCount = 1;
        	while(i<nums.length && nums[i]==nums[i-1]){
        		tempCount++;
        		i++;
        	}
        	if(tempCount>numCount){
        		numCount = tempCount;
        		num = temp;
        	}
        }
		return num;
    }
	public static int majorityElement2(int[] nums) {
        int num = 0, numCount=0;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
        	map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
        }
        for(Integer i :map.keySet()){
        	if(map.get(i)>numCount) {
        		num = i;
        		numCount = map.get(i);
        	}
        }
		return num;
    }
	
	/**
	 * 2的幂
	 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
	 * @param n
	 * @return
	 */
	public static boolean isPowerOfTwo(int n) {
		if(n<=0)	return false;
        while(n>1){
        	if(n%2!=0)	return false;
        	n/=2;
        }
		return true;
    }
	
}
