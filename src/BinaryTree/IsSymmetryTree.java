package BinaryTree;

/**
 * @PackageName:BinaryTree
 * @NAME:IsSymmetryTree
 * @Description:
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 *  
 *
 * 示例 1：
 *
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: yizhichangyuan
 * @date:2021/3/6 13:38
 */
public class IsSymmetryTree {
    /**
     * 首先明确递归函数，传入的参数是判断两个子树结点是否为对称的，返回值只有一个
     * 查看一颗树是否为对称的
     * 就是先查看其两个左右子节点是否一致，如果不一致或者有一方为空就直接返回false
     * 如果确实两者都为空，说明两者都已经超过叶子结点了，匹配完成返回true；
     * 如果左右两个子节点确实一致，则继续查看左节点的右子树和右节点的左子树是否一致 && 右节点的左子树和左节点的右子树是否一致
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode A, TreeNode B){
        if(B == null && A == null){
            return true;
        }
        if(A == null || B == null || A.val != B.val){
            return false;
        }
        return isMirror(A.left, B.right) && isMirror(A.right, B.left);
    }
}
