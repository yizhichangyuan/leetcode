package BinaryTree;

import java.util.HashMap;

/**
 * @PackageName:BinaryTree
 * @NAME:BuildTreeFromInOrderAndPreOrder
 * @Description:
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 *  
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *  
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: yizhichangyuan
 * @date:2021/3/3 21:26
 */
public class BuildTreeFromInOrderAndPreOrder {
    HashMap<Integer, Integer> map = new HashMap<>();

    /**
     * 中序遍历：左子树 -》根节点 -》右子树
     * 前序遍历：根节点 -》左子树 -》右子树
     * 前序遍历的第一个点一定是根节点，其在中序遍历的位置左边一定都是左子树，右边一定是右子树，
     * 前序遍历中左子树的数目和右子树的数目一定和中序遍历左子树以及右子树的数目相同
     * 所以可以确定前序遍历左子树起点位置、右子树起点位置、中序遍历左子树起点位置，右子树起点位置
     * 利用前序遍历递归的特点，先建立根节点，然后建立左子树、右子树
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0){
            return null;
        }
        for(int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);
        }
        return build(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    /**
     * 利用前序遍历的思想递归建立二叉树
     * @param preorder
     * @param inorder
     * @param preStart
     * @param preEnd
     * @param inStart
     * @param inEnd
     * @return
     */
    private TreeNode build(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd){
        if(preEnd < preStart && inEnd < inStart){
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int leftInEnd = map.get(preorder[preStart]) - 1;
        root.left = build(preorder, inorder, preStart + 1, preStart + 1 + leftInEnd - inStart, inStart, leftInEnd);
        root.right = build(preorder, inorder, preEnd - (inEnd - leftInEnd - 2) , preEnd, leftInEnd + 2, inEnd);
        return root;
    }
}
