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
     * ����֮��
     ����һ���������� nums ��һ��Ŀ��ֵ target�������ڸ��������ҳ���ΪĿ��ֵ���� ���� ���������������ǵ������±ꡣ
     ����Լ���ÿ������ֻ���Ӧһ���𰸡����ǣ��㲻���ظ��������������ͬ����Ԫ�ء�
     ʾ��:
     ���� nums = [2, 7, 11, 15], target = 9
     ��Ϊ nums[0] + nums[1] = 2 + 7 = 9
     ���Է��� [0, 1]
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
     *Ѱ�����������������λ��
     ����������СΪ m �� n ���������� nums1 �� nums2��
     �����ҳ������������������λ��������Ҫ���㷨��ʱ�临�Ӷ�Ϊ O(log(m + n))��
     ����Լ��� nums1 �� nums2 ����ͬʱΪ�ա�
     ʾ�� 1:
     nums1 = [1, 3]
     nums2 = [2]
     ����λ���� 2.0
     ʾ�� 2:
     nums1 = [1, 2]
     nums2 = [3, 4]
     ����λ���� (2 + 3)/2 = 2.5
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
                //i����
                iMin = i+1;
            } else if(i>iMin && N[i-1] > M[j]){
                //i��С
                iMax = i-1;
            } else{
                //��������
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
        //P[i][i]һ���ǻ��Ĵ�, P(i,i+1)=(S(i)==S(i+1))
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
     * ����һ������ n ������������ nums���ж� nums ���Ƿ��������Ԫ�� a��b��c ��ʹ�� a + b + c = 0 ���ҳ��������������Ҳ��ظ�����Ԫ�顣
	 * ע�⣺���в����԰����ظ�����Ԫ�顣
	 * ����, �������� nums = [-1, 0, 1, 2, -1, -4]��
	 * ����Ҫ�����Ԫ�鼯��Ϊ��
	 * [
  	 * 	[-1, 0, 1],
  	 * 	[-1, -1, 2]
	 * ]
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums); //��������˫ָ��б�
        for(int i=0;i<nums.length-2;i++){
        	if(nums[i]>0) break; //����������0�޽�
        	if(i>0 && nums[i]==nums[i-1]) continue; //ȥ��
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
        			//ʹ�����ڽ��Ŀ��н⣬��֤�������ظ���
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
     * ����һ������ n ������������ nums �� һ��Ŀ��ֵ target���ҳ� nums �е�����������
     * ʹ�����ǵĺ��� target ��ӽ����������������ĺ͡��ٶ�ÿ������ֻ����Ψһ�𰸡�
	 * ���磬�������� nums = [-1��2��1��-4], �� target = 1.�� target ��ӽ����������ĺ�Ϊ 2. (-1 + 2 + 1 = 2).
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
        	int closestI = Integer.MAX_VALUE;//ʹ�õ�i��ֵʱ��ӽ��Ľ�
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
     * ����һ��ֻ���� '('��')'��'{'��'}'��'['��']' ���ַ������ж��ַ����Ƿ���Ч��
	 * ��Ч�ַ��������㣺
	 * �����ű�������ͬ���͵������űպϡ�
	 * �����ű�������ȷ��˳��պϡ�
	 * ע����ַ����ɱ���Ϊ����Ч�ַ�����
	 * ʾ�� 1:
	 * ����: "()"
	 * ���: true
	 * ʾ�� 2:
	 * ����: "(]"
	 * ���: false
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
     * ɾ�����������е��ظ���
	 * ����һ���������飬����Ҫ��ԭ��ɾ���ظ����ֵ�Ԫ�أ�ʹ��ÿ��Ԫ��ֻ����һ�Σ������Ƴ���������³��ȡ�
	 * ��Ҫʹ�ö��������ռ䣬�������ԭ���޸��������鲢��ʹ�� O(1) ����ռ����������ɡ�
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
     * ���� n ���Ǹ����� a1��a2��...��an��ÿ�������������е�һ���� (i, ai) ���������ڻ� n ����ֱ�ߣ���ֱ�� i �������˵�ֱ�Ϊ (i, ai) �� (i, 0)��
     * �ҳ����е������ߣ�ʹ�������� x �Ṳͬ���ɵ�����������������ˮ��
	 * ˵�����㲻����б�������� n ��ֵ����Ϊ 2��
	 * ͼ�д�ֱ�ߴ����������� [1,8,6,2,5,4,8,3,7]���ڴ�����£������ܹ�����ˮ����ʾΪ��ɫ���֣������ֵΪ 49��
	 * ʾ��:
	 * ����: [1,8,6,2,5,4,8,3,7]
	 * ���: 49
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
     * �����������ַ�����ʽ��ʾ�ķǸ����� num1 �� num2������ num1 �� num2 �ĳ˻������ǵĳ˻�Ҳ��ʾΪ�ַ�����ʽ��
     * ʾ�� 1:
     * ����: num1 = "2", num2 = "3"
     * ���: "6"
     * ʾ�� 2:
     * ����: num1 = "123", num2 = "456"
     * ���: "56088"
     * ˵����
     * num1 �� num2 �ĳ���С��110��
     * num1 �� num2 ֻ�������� 0-9��
     * num1 �� num2 �������㿪ͷ������������ 0 ����
     * ����ʹ���κα�׼��Ĵ������ͣ����� BigInteger����ֱ�ӽ�����ת��Ϊ����������
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply(String num1, String num2) {
    	//ģ��˷��ۼ� - ����λ
    	if(num1.equals("0") || num2.equals("0")) return "0";
    	int[] ans = new int[num1.length()+num2.length()];  //�м���͵Ľ��
    	//�Ȳ����ǽ�λ���⣬������ʽ�ĳ˷�����
    	for(int i=0;i<num1.length();i++){
    		for(int j=0;j<num2.length();j++){
    			ans[i+j+1] += Character.digit(num1.charAt(i), 10) * Character.digit(num2.charAt(j), 10);
    		}
    	}
    	//�����λ����
    	for(int i=ans.length-1;i>0;i--){
    		ans[i-1] += ans[i]/10;
    		ans[i] = ans[i]%10;
    	}
    	//�������λ��λ
    	if(ans[0]/10>0){
    		ans[0] += ans[0]/10;
    		ans[1] = ans[1]%10;
    		
    	}
    	//תΪchar����
    	int noZeroPos = 0;
    	while(ans[noZeroPos]==0) noZeroPos++;
    	char[] ansChar = new char[ans.length-noZeroPos];
    	for(int i=noZeroPos;i<ans.length;i++){
    		ansChar[i-noZeroPos] = (char) (ans[i] + '0');
    	}
    	String ansStr = new String(ansChar);
    	return ansStr;
    	//�˽���� - Karatsuba�㷨
    }
    
    /**
     * ��дһ���������������ǽ�������ַ�����ת�����������ַ������ַ����� char[] ����ʽ������
     * ��Ҫ�����������������Ŀռ䣬�����ԭ���޸��������顢ʹ�� O(1) �Ķ���ռ�����һ���⡣
     * ����Լ��������е������ַ����� ASCII ����еĿɴ�ӡ�ַ���
     * ʾ�� 1��
     * ���룺["h","e","l","l","o"]
     * �����["o","l","l","e","h"]
     * ʾ�� 2��
     * ���룺["H","a","n","n","a","h"]
     * �����["h","a","n","n","a","H"]
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
     * ����һ���ַ���������Ҫ��ת�ַ�����ÿ�����ʵ��ַ�˳��ͬʱ�Ա����ո�͵��ʵĳ�ʼ˳��
     * ʾ�� 1:
     * ����: "Let's take LeetCode contest"
     * ���: "s'teL ekat edoCteeL tsetnoc" 
     * ע�⣺���ַ����У�ÿ�������ɵ����ո�ָ��������ַ����в������κζ���Ŀո�
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
     *  ��������������ĳ˻�
     *  ��������Ϊ n ���������� nums������ n > 1������������� output ������ output[i] ���� nums �г� nums[i] ֮�������Ԫ�صĳ˻���
     *  ʾ��:
     *  ����: [1,2,3,4]
     *  ���: [24,12,8,6]
     *  ˵��: �벻Ҫʹ�ó��������� O(n) ʱ�临�Ӷ�����ɴ��⡣
     *  ���ף�
     *  ������ڳ����ռ临�Ӷ�����������Ŀ�𣿣� ���ڶԿռ临�Ӷȷ�����Ŀ�ģ�������鲻����Ϊ����ռ䡣��
     * @param nums
     * @return
     */
    public static int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        int mul = 1;
        //����i��ߵ��۳�
        for(int i=0;i<nums.length;i++){
        	ans[i] = mul;
        	mul *= nums[i];
        }
        //����i�ұߵ��۳�
        mul = 1;
        for(int i=nums.length-1;i>=0;i--){
        	ans[i] *= mul;
        	mul *= nums[i];
        }
    	return ans;
    }
    
    /**
     * �����ظ�Ԫ��
     * ����һ���������飬�ж��Ƿ�����ظ�Ԫ�ء�
     * ����κ�ֵ�������г����������Σ��������� true�����������ÿ��Ԫ�ض�����ͬ���򷵻� false��
     * ʾ�� 1:
     * ����: [1,2,3,1]
     * ���: true
     * ʾ�� 2:
     * ����: [1,2,3,4]
     * ���: false
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
     * ��������
     * ����һ������ m x n ��Ԫ�صľ���m ��, n �У����밴��˳ʱ������˳�򣬷��ؾ����е�����Ԫ�ء�
     * ʾ�� 1:
     * ����:
     * [
     * [ 1, 2, 3 ],
     * [ 4, 5, 6 ],
     * [ 7, 8, 9 ]
     * ]
     * ���: [1,2,3,6,9,8,7,4,5]
     * @param matrix
     * @return
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
    	List<Integer> ans = new ArrayList<>();
        if(matrix.length==0 || matrix[0].length==0) return ans;
        int m = matrix.length, n = matrix[0].length;
        //��������ϵ��ȷ�����Ϻ����µ�����
        int x1=0, y1=0, x2=n-1, y2=m-1;
        while(x1<=x2 && y1<=y2){
        	//�����ϱ�
        	for(int i=x1;i<=x2;i++)	ans.add(matrix[y1][i]);
        	//�����ұ�
        	for(int i=y1+1;i<=y2;i++)	ans.add(matrix[i][x2]);
        	if(x1<x2 && y1<y2){
	        	//�����±�
	        	for(int i=x2-1;i>=x1;i--)	ans.add(matrix[y2][i]);
	        	//�������
	        	for(int i=y2-1;i>y1;i--)	ans.add(matrix[i][x1]);
        	}
        	x1++; y1++; x2--; y2--;
        }
    	return ans;
    }
    
    /**
     * �������� II
     * ����һ�������� n������һ������ 1 �� n2 ����Ԫ�أ���Ԫ�ذ�˳ʱ��˳���������е������ξ���
     * ʾ��:
     * ����: 3
     * ���:
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
     * �ϲ�������������
     * �������������������� nums1 �� nums2���� nums2 �ϲ��� nums1 �У�ʹ�� num1 ��Ϊһ���������顣
     * ˵��:
     * ��ʼ�� nums1 �� nums2 ��Ԫ�������ֱ�Ϊ m �� n��
     * ����Լ��� nums1 ���㹻�Ŀռ䣨�ռ��С���ڻ���� m + n�������� nums2 �е�Ԫ�ء�
     * ʾ��:
     * ����:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     * ���: [1,2,2,3,5,6]
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
