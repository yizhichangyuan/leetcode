package AssistStackOrQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @PackageName:AssistStackOrQueue
 * @NAME:FindTopMin
 * @Description:
 * 剑指 Offer 40. 最小的k个数
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 *
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *
 *
 * 限制：
 *
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 * @author: yizhichangyuan
 * @date:2021/3/16 20:34
 */
public class FindTopMin {
    /**
     * 题目只要求前几个最小的元素，而不是关心其他元素的顺序或位置，如果采用排序取前几个，如果输入数组特别大，那么就不适合排序的方法
     * 因为排序将所有元素都进行了从小到大的排序，而实际上我们只关心前几个元素，后面元素的排序关系我们是不关心的
     * 由于我们只关系前几个最小元素，这里面关键是前几个，因此我们这里使用优先队列，优先队列就是只关心前几个最小或最大的元素，其他的元素莫不关系
     * 优先队列的实现原理其实就是二叉树，这里的二叉树是始终保证根节点是最大或最小元素的，
     * 二叉树插入元素在叶子结点插入然后进行上浮操作，删除从头部结点删除，然后将最后一个叶子结点放置在根节点位置，
     * 然后进行下沉操作，上浮和下沉的目的都在于插入元素和删除元素后都能保证头部的结点是最大的元素
     * java中对应的实现类为PriorityQueue，自己也实现了一个对应PrioritizedQueue
     * 这里用PriorityQueue始终维持当前数组的前k个最小元素，PriorityQueue始终保持头部为当前k个元素的最大值，所以用了Comparator进行逆序，默认为最小值在头部
     * 每进来一个元素就和头部的最大值对比，按照从大到小排序的话，我们想找到的k个最小元素肯定始终是最后的一部分，进来的元素如果比头部值大
     * 则说明是在k个元素之前，那么不在k个之内，如果比头部值小，那么头部值就不应该再处于k个元素之内，而是在之外，所以将删除头部元素，将新进入的元素进入队列
     * 然后这个队列通过上浮和下沉操作，再次保证头部元素为最大值，因为我们每次只与根部的最大值比较
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] result = new int[k];
        int index = 0;
        if(k == 0){
            return result;
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for(int i = 0; i < k; i++){
            priorityQueue.add(arr[i]);
        }
        for(int i = k; i < arr.length; i++){
            if(priorityQueue.peek() > arr[i]){
                priorityQueue.remove();
                priorityQueue.add(arr[i]);
            }
        }
        while(!priorityQueue.isEmpty())
            result[index++] = priorityQueue.poll();
        return result;
    }
}
