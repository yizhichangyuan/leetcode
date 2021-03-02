package BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @PackageName:BinaryTree
 * @NAME:InOrder
 * @Description:
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：[1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: yizhichangyuan
 * @date:2021/3/2 20:52
 */
public class InOrder {
    List<Integer> list = new ArrayList<>();

    /**
     * 中序遍历递归，先一次递归左子树，然后访问中间节点，最后递归右子树
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null){
            return list;
        }
        inOrder(root);
        return list;
    }

    private void inOrder(TreeNode node){
        if(node == null){
            return;
        }
        inOrder(node.left);
        list.add(node.val);
        inOrder(node.right);
    }

    /**
     * 非递归写法
     * 首先中序遍历递归法是如何入栈顺序的，就是不停的入栈左节点，直到左节点为空，就弹出当前节点加入list中（这就是中间节点了）
     * 加入list后，目光转向中间节点的右子节点，如果右子节点非空，就将右子节点入栈，然后从右子节点出发（设置为temp）一路向左入栈
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        if(root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        // temp游标主要是为了能够在弹出节点后，指向该节点的右子节点，是出发点
        TreeNode temp = root;
        stack.push(root);
        while(!stack.isEmpty()){
            // 主要是temp.left != null，为了保证访问到temp所以temp也不能为空
            if(temp != null && temp.left != null){
                stack.push(temp.left);
                temp = temp.left;
            }else{
                TreeNode tempNode = stack.pop();
                list.add(tempNode.val);
                // 弹出后目光转向右子节点，将右子节点入栈后，从右子节点出发（右子节点设置为temp）不断入栈
                temp = tempNode.right;
                if(temp != null){
                    stack.push(temp);
                }
            }
        }
        return list;
    }


}
