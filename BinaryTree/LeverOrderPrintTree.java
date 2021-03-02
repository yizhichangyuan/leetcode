package BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @PackageName:BinaryTree
 * @NAME:LeverOrderPrintTree
 * @Description:
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 *
 *  
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: yizhichangyuan
 * @date:2021/3/2 15:55
 */
public class LeverOrderPrintTree {
    Queue<TreeNode> queue = new LinkedList<>();

    /**
     * 如何保证一行遍历完后，才把一行的结果放入到遍历结果中
     * 当同一层的第一个节点访问子节点时，此时队列中所有的结点一定都是都是同一层的结点
     * 我们记录下此时队列的大小，然后不断poll出元素，加入子元素并对当前节点加入到临时数组中，结束后放回到遍历结果中即可
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if(root == null){
            return list;
        }

        queue.add(root);
        int n;
        TreeNode temp;
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        while(!queue.isEmpty()){
            n = queue.size();
            for(int i = 0; i < n; i++){
                temp = queue.poll();
                tempList.add(temp.val);
                if(temp.left != null){
                    queue.add(temp.left);
                }
                if(temp.right != null){
                    queue.add(temp.right);
                }
            }
            list.add(tempList);
            tempList = new ArrayList<>();
        }
        return list;
    }
}
