package com.design.decorator;

/**
 * @author haizhuangbu
 * @date 2023/4/17 13:27
 * @mark Decorator
 */
public class Decorator extends BaseComponent {

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operator() {
        component.operator();
    }
}


interface Component {

    void operator();

}

class BaseComponent implements Component {

    @Override
    public void operator() {
        System.out.println("基础组件");
    }
}


class DecoratorA extends Decorator {

    public DecoratorA(Component component) {
        super(component);
    }

    @Override
    public void operator() {
        super.operator();
        System.out.println("A组件");
    }
}

class DecoratorB extends Decorator {

    public DecoratorB(Component component) {
        super(component);
    }

    @Override
    public void operator() {
        super.operator();
        System.out.println("B组件");
    }
}


class MainDecorator {

    public static void main(String[] args) {

        BaseComponent base = new BaseComponent();

        DecoratorA decoratorA = new DecoratorA(base);
        decoratorA.operator();

        DecoratorB decoratorB = new DecoratorB(base);
        decoratorB.operator();

        System.out.println("============");
        DecoratorB decoratorC = new DecoratorB(decoratorA);
        decoratorC.operator();


    }

}



