package BinaryTree;

import javafx.util.Pair;

import java.util.*;

/**
 * @PackageName:BinaryTree
 * @NAME:SerializeTreeAndDeSerialize
 * @Description:
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 *
 * 示例: 
 *
 * 你可以将以下二叉树：
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * 序列化为 "[1,2,3,null,null,4,5]"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: yizhichangyuan
 * @date:2021/3/6 16:38
 */
public class SerializeTreeAndDeSerialize {
    // Encodes a tree to a single string.

    /**
     * 序列化过程就是层次遍历的过程，但是需要注意层次遍历后的数组有null，也就是null结点也要入Queue
     * 取出时，如果为null，就加上"null"，如果不为空就加上该节点
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        if(root == null){
            return "[]";
        }
        StringBuilder result = new StringBuilder();
        result.append("[");
        Queue<TreeNode> queue = new LinkedList<>();
        if(root != null){
            queue.add(root);
        }
        while(!queue.isEmpty()){
            TreeNode temp = queue.poll();
            if(temp != null){
                result.append(temp.val + ",");
                queue.add(temp.left); // 为null的话也同样加入，而不是if(temp.left != null){queue.add(temp.left);}才加入
                queue.add(temp.right); // 为null的话也同样加入
            }else{
                result.append("null,");
            }
        }
        result = result.deleteCharAt(result.length() - 1);
        result.append("]");
        return result.toString();
    }

    /**
     * 观察序列化的数组，可以看到分布是一层一层的，是层序遍历的
     * 所以反序列化也采用层序遍历的方式，数组索引0为root结点，其左节点就是1，右子节点就是2，
     * 然后左节点的左子节点位置就是3，左节点的右子节点的位置就是4，可以看到这个位置是逐渐加1的
     * 加一的时机就是每建立好一个左节点加1，每建立一个右节点就加1，所以保持一个指针，刚开始的位置指向1也就是根节点的左子节点位置
     * @param data
     * @return
     */
    public TreeNode deserialize(String data){
        if(data.equals("[]")){
            return null;
        }
        String[] tempData = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(tempData[0]));
        int i = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode temp = queue.poll();
            if(!tempData[i].equals("null")){
                temp.left = new TreeNode(Integer.parseInt(tempData[i]));
                queue.add(temp.left);
            }
            // 建立完左节点后，指针加1
            i++;
            if(!tempData[i].equals("null")){
                temp.right = new TreeNode(Integer.parseInt(tempData[i]));
                queue.add(temp.right);
            }
            // 建立完右节点后，指针加1
            i++;
        }
        return root;
    }

    public TreeNode deserialize2(String data){
        if(data.equals("[]")){
            return null;
        }
        String[] tempData = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(tempData[0]));
        int i = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode temp = queue.poll();
            if(!tempData[i].equals("null")){
                temp.left = new TreeNode(Integer.parseInt(tempData[i]));
                queue.add(temp.left);
            }
            // 如果为null，那么左结点就是默认的null
            // 建立完左节点后，指针加1
            i++;
            if(!tempData[i].equals("null")){
                temp.right = new TreeNode(Integer.parseInt(tempData[i]));
                queue.add(temp.right);
            }else{
                temp.right = null;
            }
            // 如果为null，那么右结点就是默认的null
            // 建立完右节点后，指针加1
            i++;
        }
        return root;
    }

    /**
     * 正常来说父节点在列表中的位置i 与子节点的索引保持这样的关系
     * 左节点 = 2 * 父节点 + 1； 右节点 = 2 * 父节点 + 2
     * 但是有空结点：其位置索引关系发生了变化
     * 左节点 = 2 * (父节点 - 父节点索引位置之前null结点总数) + 1
     * 右节点 = 2 * (父节点 - 父节点索引位置之前null结点总数）+ 2
     * 所以需要提前记录每个点位置之前null结点总数
     * 利用前序遍历先建立根节点，然后建立左节点，右节点，递归方法参数需要指出从哪个点位置建立
     */
    String[] tempData;
    Map<Integer, Integer> map = new HashMap<>(); // 记录每个位置之前的null个数
    // Decodes your encoded data to tree.
    public TreeNode deserialize3(String data) {
        data = data.replace("[", "");
        data = data.replace("]", "");
        if(data.equals("")){
            return null;
        }
        tempData = data.split(",");
        int nullNum = 0;
        for(int i = 0; i < tempData.length; i++){
            if(tempData[i].equals("null")){
                nullNum += 1;
            }
            map.put(i, nullNum);
        }
        TreeNode root = buildTree(0);
        return root;
    }

    private TreeNode buildTree(int index){
        if(index > tempData.length - 1){
            return null;
        }
        if(tempData[index].equals("null")){
            return null;
        }
        TreeNode temp = new TreeNode(Integer.parseInt(tempData[index]));
        temp.left = buildTree(2*(index - map.get(index)) + 1);
        temp.right = buildTree(2*(index - map.get(index)) + 2);
        return temp;
    }




    public static void main(String[] args) {
//        SerializeTreeAndDeSerialize code = new SerializeTreeAndDeSerialize();
//        String data = code.serialize(null);
//        System.out.println(data);
//        TreeNode temp = code.deserialize(data);
//        System.out.println(temp);
        int[] a = new int[]{3,4,2};
        Arrays.sort(a);
        System.out.println(a[a.length - 1]);
    }
}
