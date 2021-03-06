package BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName:BinaryTree
 * @NAME:FindPathSumEqualsNumber
 * @Description:
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 *
 *  
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: yizhichangyuan
 * @date:2021/3/4 21:51
 */
public class FindPathSumEqualsNumber {
    List<List<Integer>> list = new ArrayList<List<Integer>>();
    List<Integer> tempList = new ArrayList<>();

    /**
     * 本题就是需要在深度遍历的过程中，记录下经过的结点值，以及计算经过某一个结点时sum还剩下多少
     * 记录路径用到tempList，本质上应该每压入到栈中，压入的数据有自己的独一份路径，这样返回时不会影响
     * 但是List是引用，所以栈中的每一项中List都是引用，List本质上只有独一份，所以需要手动在返回时将访问的该节点进行删除
     * 保证返回到上一层时没有当前节点在路径中，这样才不受影响
     * 返回有两种情况：1.成功找到时需要将路径加入到list中，同时也需要将当前成功节点删除，这样返回到父节点路径是不包含刚探索的子节点的，这样
     * 父节点才能不受影响的继续在其他子节点探索 2.为找到时，说明该节点已经左右节点都探索过了，所以在最后需要将该节点移除到当前路径返回到调用处
     * 由于栈中每个数据都要有自己的sum，这样才进行下一层时，才知道上一层的sum计算了多少，所以不需要手动的加减
     * 每经过一个结点需要做的工作：计算 sum -= currNode.val，将当前节点加入探索路径中，如果sum = 0加入到list中
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        findPath(root, sum);
        return list;
    }

    // 这一这里的sum=0时是直接return;该节点探索完毕了，不需要探索该节点，所以返回前将当前成功节点删除
    // 返回到上一层有两个接口，一个是sum=0，一个是没探索到，所以两处都需要进行删除
    private void findPath(TreeNode node, int sum){
        if(node == null){
            return;
        }
        sum -= node.val;
        tempList.add(node.val);
        if(sum == 0 && node.left == null && node.right == null){
            // 深拷贝list的方式
            list.add(new ArrayList<>(tempList));
            tempList.remove(tempList.size() - 1);
            return;
        }
        if(node.left != null){
            findPath(node.left, sum);
        }
        if(node.right != null){
            findPath(node.right, sum);
        }
        tempList.remove(tempList.size() - 1);
    }

    // 注意这里的sum=0没有return语句，没有进行手动删除的
    // 因为成功后，并没有直接返回，而是接着进入下面的两个if判断语句，所以其返回到上一层只有通过最后的tempList.remove才可以返回
    // 所以没有手动删除，这样缺点就是多了两个if的判断后才返回
    private void findPath2(TreeNode node, int sum){
        if(node == null){
            return;
        }
        sum -= node.val;
        tempList.add(node.val);
        if(sum == 0 && node.left == null && node.right == null){
            // 深拷贝list的方式
            list.add(new ArrayList<>(tempList));
        }
        if(node.left != null){
            findPath(node.left, sum);
        }
        if(node.right != null){
            findPath(node.right, sum);
        }
        tempList.remove(tempList.size() - 1);
    }

//    /**
//     * 非递归写法，深度探索，压入栈中的每个数据需要保存上一步的sum
//     * 待验证：超时
//     * @param root
//     * @param sum
//     * @return
//     */
//    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
//        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
//        if(root == null){
//            return list;
//        }
//        stack.push(new Pair(root, sum - root.val));
//        tempList.add(root.val);
//        while(!stack.isEmpty()){
//            Pair<TreeNode, Integer> temp = stack.peek();
//            TreeNode node = temp.first;
//            int tempVal = temp.second;
//            if(node.right != null){
//                TreeNode curNode = node.right;
//                if(tempVal - curNode.val == 0 && curNode.left == null && curNode.right == null){
//                    tempList.add(curNode.val);
//                    list.add(new ArrayList<>(tempList));
//                }else{
//                    stack.push(new Pair<>(curNode, tempVal - curNode.val));
//                    tempList.add(curNode.val);
//                }
//            }
//            if(node.left != null){
//                TreeNode curNode = node.left;
//                if(tempVal - curNode.val == 0 && curNode.left == null && curNode.right == null){
//                    tempList.add(curNode.val);
//                    list.add(new ArrayList<>(tempList));
//                }else{
//                    stack.push(new Pair<>(curNode, tempVal - curNode.val));
//                    tempList.add(curNode.val);
//                }
//            }
//            if(node.left == null && node.right == null && tempList.size() != 0){
//                tempList.remove(tempList.size() - 1);
//            }
//        }
//        return list;
//    }

}
