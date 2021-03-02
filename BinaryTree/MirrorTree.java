package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @PackageName:BinaryTree
 * @NAME:MirrorTree
 * @Description:
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 *
 * 例如输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 镜像输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 *  
 *
 * 示例 1：
 *
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: yizhichangyuan
 * @date:2021/3/2 17:24
 */
public class MirrorTree {
    /**
     * 递归写法，前序遍历
     * 先访问根节点，建立一个结点，然后将该节点的
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if(root == null){
            return null;
        }
        TreeNode temp = new TreeNode(root.val);
        temp.right = mirrorTree(root.left);
        temp.left = mirrorTree(root.right);
        return temp;
    }

    /**
     * 非递归写法，先访问根节点
     * @param root
     * @return
     */
    public TreeNode mirrorTree2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        if(root == null){
            return null;
        }
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode temp = stack.pop();
            if(temp.left != null){
                stack.add(temp.left);
            }
            if(temp.right != null){
                stack.add(temp.right);
            }
            TreeNode node = temp.left;
            temp.left = temp.right;
            temp.right = node;
        }
        return root;
    }


}
