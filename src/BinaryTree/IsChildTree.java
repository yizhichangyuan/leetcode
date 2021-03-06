package BinaryTree;

/**
 * @PackageName:BinaryTree
 * @NAME:IsChildTree
 * @Description:
 *
    输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)

    B是A的子结构， 即 A中有出现和B相同的结构和节点值。

    例如:
    给定的树 A:

    3
    / \
    4   5
    / \
    1   2
    给定的树 B：

    4
    /
    1
    返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。

    示例 1：

    输入：A = [1,2,3], B = [3,1]
    输出：false
    示例 2：

    输入：A = [3,4,5,1,2], B = [4,1]
    输出：true
 * @author: yizhichangyuan
 * @date:2021/3/6 12:39
 */
public class IsChildTree {
    boolean res = false;
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(B == null || A == null){
            return false;
        }
        if(A != null && B != null){
            // 如果两个树根节点相同，查看是否为完整相同结构
            if(A.val == B.val){
                res = recur(A, B);
            }
            // 如果不是完整相同结构，查看是否为B是否为A左子树子结构
            if(!res){
                res = isSubStructure(A.left, B);
            }
            // 如果不是左子树子结构，就看看右子树的结果
            if(!res){
                res = isSubStructure(A.right, B);
            }
        }
        return res;
    }

    // 判断A和B是否为完整相同结构，而不是子树结构
    private boolean recur(TreeNode A, TreeNode B){
        // B为null，说明B已经越过叶子结点，说明匹配完成了，此时B为A子结构或完整结构，返回true
        if(B == null){
            return true;
        }
        // 如果A为空，说明A越过叶子结点，B都没空，说明不是相同的两棵树，或者中途两个点的val不相同也提前终止
        if(A == null || A.val != B.val){
            return false;
        }
        // 如果确实根节点相同，就比较左子树和右子树是否为完全相同的两棵树
        return recur(A.left, B.left) && recur(A.right, B.right);
    }
}
