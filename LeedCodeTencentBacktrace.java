import java.util.ArrayList;
import java.util.List;

public class LeedCodeTencentBacktrace {

    /**
     * 括号生成
     * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
     * 例如，给出 n = 3，生成结果为：
     * [
     *   "((()))",
     *   "(()())",
     *   "(())()",
     *   "()(())",
     *   "()()()"
     * ]
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        generate(n, n, ans, "");
        return ans;
    }
    private void generate(int left, int right,  List<String> ans, String cur){
        if(left==0 && right==0) {
            ans.add(cur);
            return;
        }
        if(left>0){
            generate(left-1, right, ans, cur+"(");
        }
        if(right>left){
            generate(left, right-1, ans, cur+")");
        }
    }


    /**
     * 子集
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     * 说明：解集不能包含重复的子集。
     * 示例:
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> one = new ArrayList<>();
        sub(nums, 0, ans, one);
        return ans;
    }
    private void sub(int[] nums, int begin, List<List<Integer>> ans, List<Integer> one){
        ans.add(new ArrayList<>(one));
        for(int i=begin;i<nums.length;i++){
            one.add(nums[i]);
            sub(nums, i+1, ans, one);
            one.remove(one.size()-1);
        }
    }

    /**
     * 全排列
     * 给定一个没有重复数字的序列，返回其所有可能的全排列。
     * 示例:
     * 输入: [1,2,3]
     * 输出:
     * [
     *   [1,2,3],
     *   [1,3,2],
     *   [2,1,3],
     *   [2,3,1],
     *   [3,1,2],
     *   [3,2,1]
     * ]
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        return ans;
    }

    /**
     * 格雷编码
     * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
     * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。
     * 示例 1:
     * 输入: 2
     * 输出: [0,1,3,2]
     * 解释:
     * 00 - 0
     * 01 - 1
     * 11 - 3
     * 10 - 2
     * 对于给定的 n，其格雷编码序列并不唯一。
     * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
     * 00 - 0
     * 10 - 2
     * 11 - 3
     * 01 - 1
     * 示例 2:
     * 输入: 0
     * 输出: [0]
     * 解释: 我们定义格雷编码序列必须以 0 开头。
     *      给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
     *      因此，当 n = 0 时，其格雷编码序列为 [0]。
     * @param n
     * @return
     */
    public static List<Integer> grayCode(int n) {
        if(n==0) return  new ArrayList<>((0));
        List<Integer> ans = new ArrayList<>();
        int[] bi = new int[n];
        return null;
    }
}

