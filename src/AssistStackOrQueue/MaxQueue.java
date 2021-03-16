package AssistStackOrQueue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @PackageName:AssistStackOrQueue
 * @NAME:MaxQueue
 * @Description:
 * 剑指 Offer 59 - II. 队列的最大值
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 *
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 *
 * 示例 1：
 *
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * 示例 2：
 *
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 * @author: yizhichangyuan
 * @date:2021/3/16 14:39
 */
public class MaxQueue {
    Deque<Integer> queue;
    Deque<Integer> maxValueQueue;

    public MaxQueue() {
        queue = new LinkedList<Integer>();
        maxValueQueue = new LinkedList<Integer>();
    }

    public int max_value() {
        return maxValueQueue.isEmpty() ? -1 : maxValueQueue.peekFirst();
    }

    public void push_back(int value) {
        while(!maxValueQueue.isEmpty() && maxValueQueue.peekLast() <= value){
            maxValueQueue.removeLast();
        }
        maxValueQueue.addLast(value);
        queue.addLast(value);
    }

    public int pop_front() {
        if(queue.isEmpty()){
            return -1;
        }
        int header = queue.removeFirst();
        if(header == maxValueQueue.peekFirst()){
            maxValueQueue.removeFirst();
        }
        return header;
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
