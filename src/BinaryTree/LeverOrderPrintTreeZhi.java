package BinaryTree;

import java.util.*;

/**
 * @PackageName:BinaryTree
 * @NAME:LeverOrderPrintTreeZhi
 * @Description: 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 * <p>
 *  
 * <p>
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: yizhichangyuan
 * @date:2021/3/2 16:13
 */
public class LeverOrderPrintTreeZhi {
    /**
     * 首先解决如何保证一层遍历完后，才加这一层的结果放入到遍历结果中
     * 当同一层的第一个节点准备开始访问其子节点时，此时队列中的节点一定都是同一层的结点
     * 记录下此时队列的大小就是该层所有结点的个数，for不断的poll出来加入到临时列表中同时将子节点加入
     * 当for循环结束后，临时列表一定都是同一层的数据
     * 第二个问题：如何保证是之字形，遍历完一层后，层数加一，如果层数为奇数则直接加入，如果层数为偶数则reverse后加入
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        if (root == null) {
            return resultList;
        }
        int layerCount = 1; // 统计层数，便于加入是否逆序
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> tempList = new ArrayList<>();
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode tempNode = queue.poll();
                tempList.add(tempNode.val);
                if (tempNode.left != null) {
                    queue.add(tempNode.left);
                }
                if (tempNode.right != null) {
                    queue.add(tempNode.right);
                }
            }
            if ((layerCount) % 2 == 0) {
                Collections.reverse(tempList);
            }
            layerCount++;
            resultList.add(tempList);
        }
        return resultList;
    }

    /**
     * 第二个问题：如何保证是之字形，利用双端队列，如果为奇数层则从尾部加入，偶数层从头部加入
     * 缺点：每次都要判断奇偶层数
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        if (root == null) {
            return resultList;
        }
        int layerCount = 1; // 统计层数，便于加入是否逆序
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            LinkedList<Integer> tempList = new LinkedList<>(); // 利用双端队列
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode tempNode = queue.poll();
                if(layerCount % 2 == 1){
                    tempList.addLast(tempNode.val);
                }else{
                    tempList.addFirst(tempNode.val);
                }
                if (tempNode.left != null) {
                    queue.add(tempNode.left);
                }
                if (tempNode.right != null) {
                    queue.add(tempNode.right);
                }
            }
            layerCount++;
            resultList.add(tempList);
        }
        return resultList;
    }
}
