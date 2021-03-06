package BinaryTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @PackageName:BinaryTree
 * @NAME:NTreePreOrder
 * @Description:
 * 给定一个 N 叉树，返回其节点值的 前序遍历 。
 *
 * N 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 *
 *  
 *
 * 进阶：
 *
 * 递归法很简单，你可以使用迭代法完成此题吗?
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[1,3,5,6,2,4]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: yizhichangyuan
 * @date:2021/3/2 20:26
 */
public class NTreePreOrder {
    List<Integer> list = new ArrayList<>();

    /**
     * 递归写法，前序遍历
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        if(root == null){
            return list;
        }
        preFind(root);
        return list;
    }

    private void preFind(Node root){
        if(root == null){
            return;
        }
        list.add(root.val);
        for(Node child : root.children){
            preFind(child);
        }
    }

    /**
     * 非递归写法，注意这里需要对children逆序再入栈，保证先弹出的是左子节点后弹出右子节点才是前序遍历
     * @param root
     * @return
     */
    public List<Integer> preorder2(Node root) {
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node temp = stack.pop();
            list.add(temp.val);
            Collections.reverse(temp.children);
            for(Node child : temp.children){
                stack.push(child);
            }
        }
        return list;
    }
}
