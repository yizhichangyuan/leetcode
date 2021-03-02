package BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @PackageName:BinaryTree
 * @NAME:PreOrder
 * @Description:
 * @author: yizhichangyuan
 * @date:2021/3/2 20:03
 */
public class PreOrder {

    /**
     * 非递归写法，注意是先入栈右节点，再入栈左节点，这样下次弹出就弹出左节点
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if(root != null){
            stack.push(root);
        }
        while(!stack.isEmpty()){
            TreeNode temp = stack.pop();
            list.add(temp.val);
            if(temp.right != null){
                stack.push(temp.right);
            }
            if(temp.left != null){
                stack.push(temp.left);
            }
        }
        return list;
    }


    List<Integer> list = new ArrayList<>();
    public List<Integer> preorderTraversal2(TreeNode root) {
        preOrder(root);
        return list;
    }

    /**
     * 递归写法，这里是递归进入左子树，然后跳出进入右子树
     */
    private void preOrder(TreeNode root){
        if(root == null){
            return;
        }
        list.add(root.val);
        if(root.left != null){
            preOrder(root.left);
        }
        if(root.right != null){
            preOrder(root.right);
        }
    }
}
