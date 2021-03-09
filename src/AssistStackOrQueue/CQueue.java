package AssistStackOrQueue;

import java.util.Stack;

/**
 * @PackageName:AssistStackOrQueue
 * @NAME:CQueue
 * @Description:
 * 剑指 Offer 09. 用两个栈实现队列
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 *
 *  
 *
 * 示例 1：
 *
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * 示例 2：
 *
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: yizhichangyuan
 * @date:2021/3/9 00:32
 */
public class CQueue {
    Stack<Integer> stackMain;
    Stack<Integer> stackAssist;

    public CQueue() {
        stackMain = new Stack<>();
        stackAssist = new Stack<>();
    }

    public void appendTail2(int value) {
        stackMain.push(value);
    }

    /**
     * 栈的入栈顺序决定底部是最先入栈元素，栈顶是最后入栈元素
     * 题目要求为逆序，那么我们要尽快弹出队首元素，只能通过辅助栈将栈不断弹出放入，从而逆序，最后再将辅助栈的元素放入到主栈中
     * 这样实现思路上没什么问题，但是最后一步将辅助栈的元素放回到主栈中，此时辅助栈栈顶已经是队首元素了，如果这时候再搬回到主栈中
     * 如果下一步紧接着仍然是弹出队首，这就又要倒腾一次，复杂度增加了
     */
    public int deleteHead2() {
        if (stackMain.isEmpty()) {
            return -1;
        }
        while (!stackMain.isEmpty()) {
            stackAssist.push(stackMain.pop());
        }
        int item = stackAssist.pop();
        // 又再次倒腾回去是费时费力的
        while (!stackAssist.isEmpty()) {
            stackMain.push(stackAssist.pop());
        }
        return item;
    }

    /**
     * 改进思路：再次将辅助栈元素倒腾到主栈中，是费时费力的，此时辅助栈的栈顶就是头部元素
     * 如果下一次又是deleteHead直接从辅助栈栈首拿那得多方便，就不用再折腾一步
     * 如果不倒腾回去，那么下次如果对应的是append操作呢，应该怎么办
     * 那就往主栈中push，此时就是辅助栈栈顶到栈尾、主栈栈尾到栈首就是一条FIFO队列
     */
    public void appendTail(int value){
        stackMain.push(value);
    }

    public int deleteHead(){
        if(stackAssist.isEmpty()){
            if(stackMain.isEmpty()){
                return -1;
            }
            while(!stackMain.isEmpty()){
                stackAssist.push(stackMain.pop());
            }
        }
        return stackAssist.pop();
    }




}
