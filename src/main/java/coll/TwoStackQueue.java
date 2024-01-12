package coll;

import java.util.Stack;

/**
 * @author haizhuangbu
 * @date 2023/8/31 14:37
 * @mark TwoStackQueue
 */
public class TwoStackQueue {

    private Stack<Integer> push;

    private Stack<Integer> pop;

    public TwoStackQueue() {
        push = new Stack<>();
        pop = new Stack<>();
    }

    public void pushToPop() {

        if (pop.isEmpty()) {
            while (!push.isEmpty()) {
                pop.push(push.pop());
            }
        }
    }


    public void add(Integer ele) {
        push.add(ele);
        pushToPop();
    }

    public Integer pop() {
        pushToPop();
        return pop.pop();
    }


    public Integer peek() {
        pushToPop();
        return pop.peek();
    }

    public static void main(String[] args) {

        TwoStackQueue twoStackQueue = new TwoStackQueue();
        twoStackQueue.add(1);
        twoStackQueue.add(2);
        twoStackQueue.add(3);
        System.out.println(twoStackQueue.pop());
        System.out.println(twoStackQueue.pop());
        System.out.println(twoStackQueue.pop());
    }


}
