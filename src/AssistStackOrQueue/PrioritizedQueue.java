package AssistStackOrQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @PackageName:sort
 * @NAME:PrioritizedQueue
 * @Description:
 * 优先队列，优先的含义只关注当前队列的最大值，也就是头部结点是最大值，队列后面元素是否有序并不关心
 * 只关心队首元素始终是最大元素就可以了，核心就是在于插入操作以及删除最大值操作
 * 优先队列背后的思想就是一个完全二叉树，这个二叉树的特点就是根节点是最大值或最小值，这个完全二叉树可以使用数组来表示
 * 插入一个元素是防止到数组末尾，然后执行上浮操作，删除根节点后就将数组最后一个元素放置在头部结点然后执行下沉操作
 * @author: yizhichangyuan
 * @date:2021/3/16 18:04
 */
public class PrioritizedQueue {
    int[] nums;
    int index = 1;

    public PrioritizedQueue(int k){
        this.nums = new int[k + 1];
    }

    /**
     * 寻插入的元素，放置在叶子结点处，然后执行上浮操作
     * 上浮就是如果比父节点大的话，就不断与其父节点交换
     * 直到父节点 >= 该节点
     * @param num
     */
    public void insert(int num){
        nums[index++] = num;
        swim(index - 1);
    }

    public int deleteMax(){
        int temp = nums[1];
        nums[1] = nums[index - 1];
        index--;
        sink(1);
        return temp;
    }

    /**
     * 之所以是k+1数组大小，是因为如果是k的话，则k/2>=0，但是k/2始终>=0所以
     * 上浮条件就是如果该节点比其父节点都大，那么就与父节点交换
     * 终止条件就是其比父节点小
     * @param k
     */
    public void swim(int k){
        while(k / 2 >= 1 && nums[k / 2] < nums[k]){
            exch(k, k/2);
            k = k / 2;
        }
    }

    private void exch(int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 下沉条件就是如果发现该节点比两个孩子节点的最大值要小，也就是孩子结点有比其大的就与该最大的孩子结点发生交换
     * 终止条件就是该节点比其孩子结点最大的都要大
     * @param k
     */
    private void sink(int k){
        while(2 * k <= index - 1){ // 这里是2*k，而不是2*k+1，因为不能保证有两个儿子
            int temp = 2 * k;
            if(nums[2*k] < nums[2*k+1]){
                temp++;
            }
            if(nums[k] >= nums[temp])
                break;
            exch(k, temp);
            k = temp;
        }
    }

    private int max(int i, int j){
        return nums[i] > nums[j] ? nums[i] : nums[j];
    }

    public static void main(String[] args) {
        int[] temp = new int[]{3,4,1,2,5,6};
        PrioritizedQueue prioritizedQueue = new PrioritizedQueue(temp.length);
        for(int i = 0; i < temp.length; i++)
            prioritizedQueue.insert(temp[i]);
        for(int i = 0; i < temp.length; i++)
            System.out.println(prioritizedQueue.deleteMax());
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        }
    );
        for(int i = 0; i < temp.length; i++)
            queue.add(temp[i]);
        for(int i = 0; i < temp.length; i++)
            System.out.println(queue.remove());
    }

}
