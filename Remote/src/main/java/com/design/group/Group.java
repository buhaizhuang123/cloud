package com.design.group;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/4/17 13:57
 * @mark Group 组合模式
 */
public class Group {

    public static void main(String[] args) {
        Composite root = new Composite();
        Composite composite = new Composite();
        composite.add(new Leaf("leafA"));
        composite.add(new Leaf("leafB"));
        Composite composite1 = new Composite();
        composite1.add(new Leaf("leafC"));
        composite1.add(new Leaf("leafD"));
        composite.add(composite1);
        root.add(composite);
        root.operator();
    }

}


interface Component {

    void add(Component component);

    void remove(Component component);

    void operator();

    Component getChild(int i);
}


class Composite implements Component {
    private List<Component> list = new ArrayList<>();

    @Override
    public void add(Component component) {
        list.add(component);
    }

    @Override
    public void remove(Component component) {
        list.remove(component);
    }

    @Override
    public void operator() {
        for (Component component : list) {
            component.operator();
        }
    }

    @Override
    public Component getChild(int i) {
        return list.get(i);
    }
}

class Leaf implements Component {
    private String leafName;

    public Leaf(String leafName) {
        this.leafName = leafName;
    }

    @Override
    public void add(Component component) {

    }

    @Override
    public void remove(Component component) {

    }

    @Override
    public void operator() {
        System.out.println("leaf-name = " + leafName);
    }

    @Override
    public Component getChild(int i) {
        return null;
    }
}
