package coll;

import java.util.Stack;

/**
 * @author haizhuangbu
 * @date 2023/8/31 14:44
 * @mark TranStack
 */
public class TranStack {

    private Stack<Integer> data;

    public TranStack() {
        data = new Stack<>();
    }

    public void add(Integer ele) {
        data.push(ele);
    }

    public void pop() {
        if (!data.isEmpty()) {
            Integer pop = this.data.pop();
            pop();
            System.out.println(pop);
        }
    }

    public Integer getAndRemoveLastEle(Stack<Integer> stack) {

        Integer pop = stack.pop();
        if (stack.isEmpty()) {
            return pop;
        } else {
            Integer andRemoveLastEle = getAndRemoveLastEle(stack);
            stack.push(pop);
            return andRemoveLastEle;
        }
    }

    public static void main(String[] args) {
        TranStack tranStack = new TranStack();
        tranStack.add(1);
        tranStack.add(2);
        tranStack.add(3);
        Stack<Integer> data1 = tranStack.data;
        System.out.println(tranStack.getAndRemoveLastEle(data1));
        System.out.println(tranStack.getAndRemoveLastEle(data1));
        System.out.println(tranStack.getAndRemoveLastEle(data1));
    }


}
