package com.design.construction;

/**
 * @author haizhuangbu
 * @date 2023/4/17 10:55
 * @mark Construction
 */
public class Construction {

    private C1 c1;

    private C2 c2;

    public C1 getC1() {
        return c1;
    }

    public void setC1(C1 c1) {
        this.c1 = c1;
    }

    public C2 getC2() {
        return c2;
    }

    public void setC2(C2 c2) {
        this.c2 = c2;
    }

    public static void main(String[] args) {
        // construction 构造组成
        Construction construction = new Construction();
        construction.setC1(new C1());
        construction.setC2(new C2());

    }


}



class C1 {

}


class C2 {

}