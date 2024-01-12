package coll;

import java.util.Stack;

/**
 * @author haizhuangbu
 * @date 2023/8/31 14:24
 * @mark NewStack 实现 栈的基本功能,获取栈中最小值
 */
public class NewStack {

    private Stack<Integer> data = new Stack<>();

    private Stack<Integer> minData = new Stack<>();


    public void push(Integer ele) {

        if (data.isEmpty()) {
            minData.push(ele);
        } else if (ele <= getMin()) {
            minData.push(ele);
        }
        data.push(ele);

    }

    public Integer pop() {

        if (data.isEmpty()) {
            throw new RuntimeException(" 元素 内容为空");
        }

        Integer value = data.pop();
        if (value <= getMin()) {
            minData.pop();
        }
        return value;
    }


    public Integer getMin() {
        if (minData.isEmpty()) {
            throw new RuntimeException(" 元素 内容为空");
        }

        return minData.peek();
    }


}
