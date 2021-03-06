package BinaryTree;

import javafx.util.Pair;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @PackageName:BinaryTree
 * @NAME:CalMaxDepth 计算二叉树最大胜读
 * @Description:
 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
 *
 * 例如：
 *
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: yizhichangyuan
 * @date:2021/3/1 22:30
 */

public class CalMaxDepth {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    /**
     * 递归写法
     * 统计左子树和右子树的最大深度 + 1
     * @param root
     * @return
     */
    public int maxDepthRecr(TreeNode root){
        if(root == null){
            return 0;
        }
        return Math.max(maxDepthRecr(root.left), maxDepthRecr(root.right)) + 1;
    }


    /**
     * 就是在利用栈深度探索过程中，记录下每个节点及其对应的深度
     * 记录对应深度是为了其子节点的深度只需要在父节点深度加1即可
     * 遍历过程中不断与最大深度比较，如果超过就更新
     * @param root
     * @return
     */
    public int maxDepthNotRecr(TreeNode root){
        if(root == null){
            return 0;
        }
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(root, 1));
        Integer maxDepth = 0;
        while(!stack.isEmpty()){
            Pair<TreeNode, Integer> temp = stack.pop(); //取出父节点
            maxDepth = Math.max(temp.getValue(), maxDepth); // 统计最大深度
            if(temp.getKey().left != null){
                stack.push(new Pair<>(temp.getKey().left, temp.getValue() + 1));
            }
            if(temp.getKey().right != null){
                stack.push(new Pair<>(temp.getKey().right, temp.getValue() + 1));
            }
        }
        return maxDepth;
    }


    /**
     * 在一层都遍历完后，才加层数加一
     * 一层遍历完后也就是这一层的结点的子节点都加入了队列层数才加一
     * 怎么遍历完每一层的结点后才加一呢，那就是在添加该层第一个结点的子节点时，队列中保存的一定都是同一层的结点
     * 在遍历前统计下队列的长度，这就表示了该层结点总数，然后依次删除该层结点并添加子节点即可
     * @param root
     * @return
     */
    public int maxDepthRecrWidth(TreeNode root){
        if(root == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        while(!queue.isEmpty()){
            // 在该层第一个节点子节点加入前，此时队列中一定都是同一层的所有结点
            int n = queue.size();
            for(int i = 0; i < n; i++){
                TreeNode temp = queue.poll();
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if(temp.right != null){
                    queue.add(temp.right);
                }
            }
            depth++;
        }
        return depth;
    }




    /**
     * 深度优先递归写法
     * @param node
     */
    public void dfs(TreeNode node){
        if(node == null){
            return;
        }
        if(node.left != null){
            dfs(node.left);
        }
        if(node.right != null){
            dfs(node.right);
        }
    }

    /**
     * 广度优先探索非递归写法，层次遍历
     * 利用队列
     */
    public void bfs(TreeNode node){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()){
            TreeNode temp = queue.poll();
            // 业务逻辑
            if(temp.left != null){
                queue.add(temp.left);
            }
            if(temp.right != null){
                queue.add(temp.right);
            }
        }
    }






}
