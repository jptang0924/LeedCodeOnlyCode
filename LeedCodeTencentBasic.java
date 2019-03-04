import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.List;

public class LeedCodeTencentBasic {
	 /**
     * 两数之和
     给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     示例:
     给定 nums = [2, 7, 11, 15], target = 9
     因为 nums[0] + nums[1] = 2 + 7 = 9
     所以返回 [0, 1]
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(target - nums[i])){
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{0,0};
    }

    /**
     *寻找两个有序数组的中位数
     给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     你可以假设 nums1 和 nums2 不会同时为空。
     示例 1:
     nums1 = [1, 3]
     nums2 = [2]
     则中位数是 2.0
     示例 2:
     nums1 = [1, 2]
     nums2 = [3, 4]
     则中位数是 (2 + 3)/2 = 2.5
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size1 = nums1.length;
        int size2 = nums2.length;
        int[] M;
        int[] N;
        if(size1>=size2){
            M = nums1;
            N = nums2;
        } else {
            M = nums2;
            N = nums1;
        }
        int iMin = 0, iMax = N.length;
        int i, j;
        while (iMin<=iMax){
            i = (iMin+iMax)/2;
            j = (N.length+M.length+1)/2 - i;
            if(i<iMax && M[j-1] > N[i]){
                //i增加
                iMin = i+1;
            } else if(i>iMin && N[i-1] > M[j]){
                //i减小
                iMax = i-1;
            } else{
                //符合条件
                int leftMax;
                int rightMin;
                if(i==0){
                	leftMax = M[j-1]; 
                } else if(j==0){
                	leftMax = N[i-1];
                } else{
                	leftMax = Math.max(N[i-1], M[j-1]);
                }
                if((N.length+M.length)%2!=0){
                    return  leftMax;
                }
                
                if(i==N.length){
                    rightMin = M[j];
                } else if(j==M.length){
                    rightMin = N[i];
                } else{
                    rightMin = Math.min(N[i],M[j]);
                }
                return  (leftMax+rightMin)/2.0;
            }
        }
        return 0.0;
    }
    
    public static String longestPalindrome(String s) {
    	if(s.length()<=0)	return "";
        int len = s.length();
        boolean P[][] = new boolean[len][len];
        int maxLen = 1;
        int startPos = 0;
        //P[i][i]一定是回文串, P(i,i+1)=(S(i)==S(i+1))
    	for(int i=0;i<len-1;i++){
    		P[i][i] = true;
    		if(s.charAt(i) == s.charAt(i+1)){
    			P[i][i+1] = true;
    			maxLen = 2;
    			startPos = i;
    		}
    	}
    	P[len-1][len-1] = true;
    	
    	for(int lens=3;lens<=len;lens++){
    		for(int start=0;start+lens-1<len;start++){
    			int end = start+lens-1;
    			//P(i,j)=(P(i+1,j-1) and S(i)==S(j))
    			if(P[start+1][end-1] && s.charAt(start)==s.charAt(end)){
    				P[start][end] = true;
    				startPos = start;
    				maxLen = lens;
    			}
    		}
    	}
    	
    	return s.substring(startPos, startPos+maxLen);
    }
    
    
    public static int myAtoi(String str) {
    	int startPos = -1;
    	boolean isNeg = false;
        for(int i=0; i<str.length(); i++){
        	if(str.charAt(i) == ' '){
        		continue;
        	}
        	if(str.charAt(i) == '-' || str.charAt(i) == '+'){
        		if(i+1 < str.length() && Character.isDigit(str.charAt(i+1))){
        			if(str.charAt(i) == '-' ){
        				isNeg = true;
        			}
        			startPos = i+1;
            		break;
        		}
        		break;
        	}
        	if(Character.isDigit(str.charAt(i))){
        		startPos = i;
        		break;
        	} else{
        		break;
        	}
        }
        if(startPos==-1){
        	return 0;
        }
        int endPos = startPos;
        while(endPos+1<str.length()){
        	if(Character.isDigit(str.charAt(endPos+1))){
        		endPos++;
        		continue;
        	}
        	break;
        }
        while(str.charAt(startPos)=='0' && startPos<endPos){
        	startPos++;
        }
        int len = endPos-startPos+1;
        if(len>10){
        	return isNeg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        long ans = 0;
        long rate = 1;
    	for(int i=endPos;i>=startPos;i--){
    		ans += Character.digit(str.charAt(i), 10) * rate;
    		rate *= 10;
    	}
    	if(ans > Integer.MAX_VALUE){
    		return isNeg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    	} else{
    		return (int) (isNeg ? 0-ans : ans);
    	}
    }
    
    
    public static String longestCommonPrefix(String[] strs) {
    	if(strs.length==0){
    		return "";
    	}
    	if(strs.length==1){
    		return strs[0];
    	}
    	String firstStr = strs[0];
    	boolean isSame = true;
    	int len = 0;
        while(isSame){
        	for(int i=1;i<strs.length;i++){
        		if(len>=strs[i].length() || len>=firstStr.length() || strs[i].charAt(len)!=firstStr.charAt(len)){
        			isSame = false;
        		}
        	}
        	len++;
        }
        if(len==0)	return "";
    	return firstStr.substring(0, len-1);
    }
    
    /**
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
	 * 注意：答案中不可以包含重复的三元组。
	 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
	 * 满足要求的三元组集合为：
	 * [
  	 * 	[-1, 0, 1],
  	 * 	[-1, -1, 2]
	 * ]
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums); //先排序，再双指针夹逼
        for(int i=0;i<nums.length-2;i++){
        	if(nums[i]>0) break; //三数都大于0无解
        	if(i>0 && nums[i]==nums[i-1]) continue; //去重
        	int target = 0 - nums[i];
        	int start = i+1, end = nums.length-1;
        	while(start<end){
        		int sum = nums[start]+nums[end];
        		if(sum < target){
        			start++;
        		} else if(sum > target){
        			end--;
        		} else{
        			ans.add(Arrays.asList(nums[i],nums[start],nums[end]));
        			//使用最邻近的可行解，保证不出现重复解
        			while(start<end && nums[start]==nums[start+1]) start++;
        			while(start<end && nums[end]==nums[end-1]) end--;
        			start++;
        			end--;
        		}
        	}
        }
    	return ans;
    }
    
    /**
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，
     * 使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
	 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
    	int ans = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
        	int rem = target - nums[i];
        	int start = i+1, end = nums.length-1;
        	int closestI = Integer.MAX_VALUE;//使用第i个值时最接近的解
        	while(start<end){
        		int sum = nums[start] + nums[end];
        		int diff = target-nums[i]-sum;
        		if(Math.abs(diff) < Math.abs(closestI)) closestI = diff;
        		if(sum < rem){
        			start++;
        		} else if(sum > rem){
        			end--;
        		} else{
        			break;
        		}
        	}
        	if(Math.abs(closestI) < Math.abs(ans)) ans = closestI;
        }
    	return target-ans;
    }
    
    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
	 * 有效字符串需满足：
	 * 左括号必须用相同类型的右括号闭合。
	 * 左括号必须以正确的顺序闭合。
	 * 注意空字符串可被认为是有效字符串。
	 * 示例 1:
	 * 输入: "()"
	 * 输出: true
	 * 示例 2:
	 * 输入: "(]"
	 * 输出: false
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
    	Stack<Character> st = new Stack<Character>();
    	for(int i=0;i<s.length();i++){
    		if(s.charAt(i)=='(' || s.charAt(i)=='[' || s.charAt(i)=='{'){
    			st.push(s.charAt(i));
    		} else{
	    		if(st.isEmpty())	return false;
	    		char c = st.pop();
    			if(!isMatch(s.charAt(i), c)) return false;
    		}
    	}
    	if(st.isEmpty())	return true;
    	return false;
    }
    private static boolean isMatch(char a, char b){
    	if(a=='[' && b==']')	return true;
    	if(a=='(' && b==')')	return true;
    	if(a=='{' && b=='}')	return true;
    	if(b=='[' && a==']')	return true;
    	if(b=='(' && a==')')	return true;
    	if(b=='{' && a=='}')	return true;
    	return false;
    }
    
    /**
     * 删除排序数组中的重复项
	 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
	 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
    	if(nums.length<=1) return nums.length;
    	int curPos = 1;
        for(int i=0;i<nums.length;){
        	int j = i+1;
        	while(j<nums.length && nums[i]==nums[j])	j++;
        	if(j<nums.length && i!=j){
        		nums[curPos] = nums[j];
        		curPos++;
        	}
        	i = j;
        }
    	return curPos;
    }
    
    /**
     * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
	 * 说明：你不能倾斜容器，且 n 的值至少为 2。
	 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
	 * 示例:
	 * 输入: [1,8,6,2,5,4,8,3,7]
	 * 输出: 49
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int maxArea = 0;
    	for(int i=0;i<height.length;i++){
    		for(int j=i+1;j<height.length;j++){
    			int areaIJ = Math.min(height[i], height[j]) * (j-i); 
    			if(areaIJ > maxArea) maxArea = areaIJ;
    		}
    	}
    	return maxArea;
    }
    
    /**
     * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     * 示例 1:
     * 输入: num1 = "2", num2 = "3"
     * 输出: "6"
     * 示例 2:
     * 输入: num1 = "123", num2 = "456"
     * 输出: "56088"
     * 说明：
     * num1 和 num2 的长度小于110。
     * num1 和 num2 只包含数字 0-9。
     * num1 和 num2 均不以零开头，除非是数字 0 本身。
     * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply(String num1, String num2) {
    	//模拟乘法累加 - 最后进位
    	if(num1.equals("0") || num2.equals("0")) return "0";
    	int[] ans = new int[num1.length()+num2.length()];  //中间求和的结果
    	//先不考虑进位问题，根据竖式的乘法运算
    	for(int i=0;i<num1.length();i++){
    		for(int j=0;j<num2.length();j++){
    			ans[i+j+1] += Character.digit(num1.charAt(i), 10) * Character.digit(num2.charAt(j), 10);
    		}
    	}
    	//处理进位问题
    	for(int i=ans.length-1;i>0;i--){
    		ans[i-1] += ans[i]/10;
    		ans[i] = ans[i]%10;
    	}
    	//处理最高位进位
    	if(ans[0]/10>0){
    		ans[0] += ans[0]/10;
    		ans[1] = ans[1]%10;
    		
    	}
    	//转为char数组
    	int noZeroPos = 0;
    	while(ans[noZeroPos]==0) noZeroPos++;
    	char[] ansChar = new char[ans.length-noZeroPos];
    	for(int i=noZeroPos;i<ans.length;i++){
    		ansChar[i-noZeroPos] = (char) (ans[i] + '0');
    	}
    	String ansStr = new String(ansChar);
    	return ansStr;
    	//了解分治 - Karatsuba算法
    }
    
    /**
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     * 示例 1：
     * 输入：["h","e","l","l","o"]
     * 输出：["o","l","l","e","h"]
     * 示例 2：
     * 输入：["H","a","n","n","a","h"]
     * 输出：["h","a","n","n","a","H"]
     * @param s
     */
    public static void reverseString(char[] s) {
    	char swap;
        for(int i=0;i<s.length/2;i++){
        	swap = s[i];
        	s[i] = s[s.length-1-i];
        	s[s.length-1-i] = swap;
        }
    }
    
    /**
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     * 示例 1:
     * 输入: "Let's take LeetCode contest"
     * 输出: "s'teL ekat edoCteeL tsetnoc" 
     * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        String[] strs = s.split(" ");
        StringBuilder builder =  new StringBuilder();
    	for(int i=0;i<strs.length;i++){
    		builder.append(strs[i]);
    		strs[i] = builder.reverse().toString();
    		builder.delete( 0, builder.length());
    	}
    	for(int i=0;i<strs.length;i++){
    		builder.append(strs[i]);
    		if(i<strs.length-1)	builder.append(" ");
    	}
    	return builder.toString();
    }
    
    /**
     *  除自身以外数组的乘积
     *  给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     *  示例:
     *  输入: [1,2,3,4]
     *  输出: [24,12,8,6]
     *  说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
     *  进阶：
     *  你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
     * @param nums
     * @return
     */
    public static int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        int mul = 1;
        //计算i左边的累乘
        for(int i=0;i<nums.length;i++){
        	ans[i] = mul;
        	mul *= nums[i];
        }
        //计算i右边的累乘
        mul = 1;
        for(int i=nums.length-1;i>=0;i--){
        	ans[i] *= mul;
        	mul *= nums[i];
        }
    	return ans;
    }
    
    /**
     * 存在重复元素
     * 给定一个整数数组，判断是否存在重复元素。
     * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
     * 示例 1:
     * 输入: [1,2,3,1]
     * 输出: true
     * 示例 2:
     * 输入: [1,2,3,4]
     * 输出: false
     * @param nums
     * @return
     */
    public static boolean containsDuplicate(int[] nums) {
        Map<Integer, Boolean> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
        	if(map.containsKey(nums[i])){
        		return true;
        	}
        	map.put(nums[i], true);
        }
    	return false;
    }
    
    /**
     * 螺旋矩阵
     * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     * 示例 1:
     * 输入:
     * [
     * [ 1, 2, 3 ],
     * [ 4, 5, 6 ],
     * [ 7, 8, 9 ]
     * ]
     * 输出: [1,2,3,6,9,8,7,4,5]
     * @param matrix
     * @return
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
    	List<Integer> ans = new ArrayList<>();
        if(matrix.length==0 || matrix[0].length==0) return ans;
        int m = matrix.length, n = matrix[0].length;
        //建立坐标系，确定左上和右下的坐标
        int x1=0, y1=0, x2=n-1, y2=m-1;
        while(x1<=x2 && y1<=y2){
        	//遍历上边
        	for(int i=x1;i<=x2;i++)	ans.add(matrix[y1][i]);
        	//遍历右边
        	for(int i=y1+1;i<=y2;i++)	ans.add(matrix[i][x2]);
        	if(x1<x2 && y1<y2){
	        	//遍历下边
	        	for(int i=x2-1;i>=x1;i--)	ans.add(matrix[y2][i]);
	        	//遍历左边
	        	for(int i=y2-1;i>y1;i--)	ans.add(matrix[i][x1]);
        	}
        	x1++; y1++; x2--; y2--;
        }
    	return ans;
    }
    
    /**
     * 螺旋矩阵 II
     * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
     * 示例:
     * 输入: 3
     * 输出:
     * [
     *  [ 1, 2, 3 ],
     *  [ 8, 9, 4 ],
     *  [ 7, 6, 5 ]
     * ]
     * @param n
     * @return
     */
    public static int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
    	int x1=0, y1=0, x2=n-1, y2=n-1;
    	int num=1;
    	while(x1<=x2 && y1<=y2){
    		for(int i=x1;i<=x2;i++)	ans[y1][i] = num++;
    		for(int i=y1+1;i<=y2;i++)	ans[i][x2] = num++;
    		if(x1<x2 && y1<y2){
	    		for(int i=x2-1;i>=x1;i--)	ans[y2][i] = num++;
	    		for(int i=y2-1;i>y1;i--)	ans[i][x1] = num++;
    		}
    		x1++; y1++; x2--; y2--;
    	}
    	return ans;
    }
    
    /**
     * 合并两个有序数组
     * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
     * 说明:
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     * 示例:
     * 输入:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     * 输出: [1,2,2,3,5,6]
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int pos1 = m-1, pos2 = n-1;
        int pos = m+n-1;
        while(pos1>=0 && pos2>=0 && pos>=0){
        	if(nums1[pos1]<nums2[pos2])	nums1[pos--] = nums2[pos2--];
        	else if(nums1[pos1]>=nums2[pos2])	nums1[pos--] = nums1[pos1--];
        }
        while(pos2>=0){
        	nums1[pos2] = nums2[pos2];
        	pos2--;
        }
    }
    
}
