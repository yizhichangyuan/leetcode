package BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @PackageName:BinaryTree
 * @NAME:LeverOrderPrintTree0
 * @Description:
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
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
 * 返回：
 *
 * [3,9,20,15,7]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: yizhichangyuan
 * @date:2021/3/2 16:57
 */
public class LeverOrderPrintTree0 {
    public int[] levelOrder(TreeNode root) {
        if(root == null){
            return new int[0];
        }
        // 由于int[]必须制定长度，所以只能暂时用List代替
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode temp = queue.poll();
            result.add(temp.val);
            if(temp.left != null){
                queue.add(temp.left);
            }
            if(temp.right != null){
                queue.add(temp.right);
            }
        }
//        int[] finalResult = new int[result.size()];
//        for(int i = 0; i < result.size(); i++){
//            finalResult[i] = result.get(i);
//        }
        // list转换为int[]的方法
        int[] finalResult = result.stream().mapToInt(Integer::valueOf).toArray();
        return finalResult;
    }
}
