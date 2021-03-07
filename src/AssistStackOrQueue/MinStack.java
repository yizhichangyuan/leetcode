package AssistStackOrQueue;

import java.util.Stack;

/**
 * @PackageName:AssistStackOrQueue
 * @NAME:MinStack
 * @Description:
 * 剑指 Offer 30. 包含min函数的栈
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 *
 *
 *
 * 示例:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.min();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.min();   --> 返回 -2.
 *
 *
 * 提示：
 *
 * 各函数的调用总次数不超过 20000 次
 * @author: yizhichangyuan
 * @date:2021/3/7 15:16
 */
public class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack; // 辅助栈
    /**
     * 要求每时每刻弹出栈中的min都是o(1)，这要求我们空间换时间，用一个数据结构始终保存当前栈的最小值变动历史
     * 因为存储元素的是栈，所以构造的辅助结构也应该是一个栈，这里称为辅助栈
     * 辅助栈minStack栈顶始终保存对应stack栈中的最小元素，minStack是从栈顶到栈底是单调递增的
     * 下方保存的数据是称为stack栈中最小值的历史数据，因为越早入min栈历史说明越久远
     * 因为出栈入栈都是从栈顶进行操作，如果stack索引为[0,k]，这时候新入栈元素[k+1]，比较此时入栈元素[k+1]与minStack栈顶元素大小（这时候minStack的栈顶对应[0-k]的最小值)
     * 如果比较小说明入栈元素[k]是这时候栈中[0,k+1]最小值，历史最薄，原来的栈顶是不需要删除的，因为其是对应stack[0-k]的最小值，stack[k+1]弹出时就需要历史数据了
     * 如果比较大，说明在现在以及以后不可能成为栈中最小值，因为新入栈，所以也最早出栈，呆的时间最短，不可能成为最小值，所以不加入到minStack中
     * 出栈时比较出栈的元素[k+1]是否对应minStack中的栈顶元素，如果确实是，说明minStack的栈顶对应[0,k+1]的最小值，所以也应该移除
     * 暴露出新栈顶是这时候stack[0,k]的最小元素，其是之前的历史数据
     */
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        // 这里采用的是>=，=也要存入栈中
        if(minStack.isEmpty() || minStack.peek() >= x){
            minStack.push(x);
        }
    }

    public void pop() {
        // 注意这里采用的是Equals而不是==，因为Integer对象是有一个IntegerCache缓存池的，会缓存[-128,127]范围内的Integer对象
        // 如果不采用new Integer()来创造新对象，==是比较的引用地址而不是内部的value
        // Integer a = 1; 此时缓存池为空，加入到缓存池中
        // Integer b = 1; 从缓存池取出
        // Integer c = new Integer(1); 新建对象，是不放入缓存池中的
        // a == b; true
        // a == c; false
        if(stack.pop().equals(minStack.peek())){
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }

    public static void main(String[] args) {
         Integer a = 1; //此时缓存池为空，加入到缓存池中
         Integer b = 1; //从缓存池取出
         Integer c = new Integer(1); //新建对象，是不放入缓存池中的
         System.out.println(a == b); //true
         System.out.println(a == c); //false
    }
}
