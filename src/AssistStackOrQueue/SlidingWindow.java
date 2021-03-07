package AssistStackOrQueue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @PackageName:AssistStackOrQueue
 * @NAME:SlidingWindow
 * @Description:
 * @author: yizhichangyuan
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *  
 *
 * 提示：
 *
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date:2021/3/7 14:03
 */
public class SlidingWindow {
    /**
     * 以第一个窗口[0,2]为例，如果找到的最大值max_one索引在[1-2]之间，那么将[0]删除，添加[3]号索引位置变成窗口[1-3]时，其最大值时只需要比较
     * max_one和[3]的大小，这样的复杂度是o(1)，但是如果第一个窗口最大值max_one在[0]号位置，那么新窗口[1-3]就要重新比较，复杂度o(k)
     * 所以采用暴力法的复杂度是o(nk)，其中n是窗口的总数length - k + 1
     * 本题关键在于在窗口滑动拆解成两步：在删除尾部元素添加头部元素的过程中，找到新窗口的最大值的复杂度由o(k)下降到o(1)
     * 所以只能通过空间换时间策略，观察窗口的移动是移除首部元素，然后在尾部进入元素，结构和队列是一模一样
     * 所以可以为其构造一个保存窗口可能的最大值数据的结构，其结构应该也采用队列
     * 队列需要时刻保持队首是窗口最大值，尾部是下一步可能的最大值，保持从大到小的队列，从队首到队尾也表示成为窗口最大值的权重是不断减小的
     * 为什么说可能的最大值数据，因为如果新窗口加入值会很大，从队尾塞入，会将队列尾部比它小的元素冲掉，然后放入相应位置上，表示这个元素在接下来窗口中成为最大值的权重是十分高的
     * 如果新加入元素比队尾元素都小，那么它成为最大值权重是很小的
     * 冲下来的数据是不可能成为最大值的，因为最新加入窗口中的值比他们都大，而且新加入的窗口值待在队列中的时间也是最长，所以直接删除掉冲下来的数据，不需要保存
     * 因为窗口是不断的移除和添加元素的，窗口移除首部元素的同时，需要往看队首的最大值是否为该位置元素，如果是，队首也应该删除
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0){
            return new int[]{};
        }
        int length = nums.length;
        Deque<Integer> queue = new LinkedList<>(); // 双向辅助队列Deque，保存可能成为窗口最大值的数据队列
        int[] result = new int[length - k + 1];
        // 对于第一个窗口，首先找出可能最大值队列，这样接下来才能进行不断滑动操作
        for(int i = 0; i < k; i++){
            // 窗口新加入的数据将队列从尾部开始将不可能成为最大值的数据删除，然后插入
            while(!queue.isEmpty() && queue.peekLast() < nums[i]){
                queue.removeLast();
            }
            // 如果比尾部元素还小，就直接加入，表示其权重很小
            queue.addLast(nums[i]);
        }
        int index = 0;
        result[index++] = queue.peekFirst();
        for(int i = k; i < length; i++){
            // 窗口滑动拆解成两步
            // 删除首部元素，查看删除元素是否为最大值，如果是，则删除
            if(nums[i - k] == queue.peekFirst()){
                queue.removeFirst();
            }
            // 窗口新加入元素，查看权重，将不会成为最大值的元素删除，因为新加入的元素待得很久
            while(!queue.isEmpty() && nums[i] > queue.peekLast()){
                queue.removeLast();
            }
            // 如果比尾部元素小，则表示其权重很小，直接放入尾部，在接下来也可能成为窗口最大值
            queue.addLast(nums[i]);
            result[index++] = queue.peekFirst();
        }
        return result;
    }
}
