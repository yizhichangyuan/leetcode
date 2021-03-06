package BinaryTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @PackageName:BinaryTree
 * @NAME:FindKthLargest
 * @Description:
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 4
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: yizhichangyuan
 * @date:2021/3/2 15:09
 */
public class FindKthLargest {
    int y;
    List<Integer> list = new ArrayList<>();

    /**
     * 没有利用到二叉搜索数优良已经排序好的特点
     * 二叉搜索树深度优先探索后，加入到队列中然后排序
     * @param root
     * @param k
     * @return
     */
    public int kthLargest1(TreeNode root, int k) {
        dfs(root);
        Collections.sort(list, Collections.reverseOrder());
        return list.get(k);
    }

    private void dfs(TreeNode root){
        if(root == null){
            return;
        }
        list.add(root.val);
        if(root.left != null){
            dfs(root.left);
        }
        if(root.right != null){
            dfs(root.right);
        }
    }


    /**
     * 题目给定的是二叉搜索树
     * 首先建立好一个概念，二叉搜索树中序遍历：左子树 -》根节点 -》右子树，是升序的排列
     * 题目是想找k大的数，想像一个场景就是一个降序排列寻找第k大的数，指针指向第一个数，将k = k-1，如果结果为0就是我们想找的数
     * 如果k-1不是0那么就指针接着调到第二位，知道k-1结果为0那个数就是我们要寻找的第k大的数
     * 所以我们在搜索过程中，要以降序的方式访问每一个结点，这样每过一个结点就会把k-1，如果结果为0那么当前的结点就是我们要找的数
     * 中序遍历是升序排列，那么降序排列把中序排列倒过来不就可以了，先访问右子树 -> 根节点 ->左子树
     * 在访问根节点对k-1，如果结果为0，那么这个根节点就是我们要寻找的数，我们用res全局变量保存下来
     * 这里要求是每过一个结点就对k进行减1操作，所以这个k是全局的，所以递归过程不是每个递归都带一个k（这样每个栈都有一个k）
     * 所以k设置为全局变量
     * @param root
     * @param k
     * @return
     */
    int k;
    int result = 0;
    public int kthLargest2(TreeNode root, int k){
        this.k = k;
        find(root);
        return result;
    }

    private void find(TreeNode root){
        if(root == null){
            return;
        }
        find(root.right);
        k--;
        if(k == 0){
           result = root.val;
           // 找到就直接返回
           return;
        }
        find(root.left);
    }







}
