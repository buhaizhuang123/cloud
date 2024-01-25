package com.ts;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;


/**
 * @author haizhuangbu
 * @date 2022/7/27 10:47
 * @mark TsOne
 */
public class TsOne {

    private int o = 0;

    /**
     * 二分查找法
     * @mark 有序队列查找
     */
    @Test
    public void twoSearch(){
        int [] order = new int[100];
        for (int i = 0; i < order.length; i++) {
            order[i] = i;
        }
        int randomNum = new Random().nextInt(100);
        System.out.println("randomNum = " + randomNum);
        two(order,0,order.length,randomNum);
    }
    /**
     * 二分
     */
    private void two(int[] order,int left,int right,int randomNum) {
        int index = (left + right) / 2;
        System.out.println("left = " + left + "   right = " + right);
        System.out.println("步数:"+(o+=1));
        if (order[index] > randomNum){
            this.two(order,left,index,randomNum);
        } else if (order[index] < randomNum){
            this.two(order,index,right,randomNum);
        } else {
            System.out.println(order[index]);
        }
    }

    /**
     * fastSort
     * 快速排序
     */
    @Test
    public void fastSort(){
        int [] sort = {1,2,21,4,34};
        sort(sort,0,sort.length - 1);
    }

    private void sort(int[] sort, int left, int right) {
        if (left < right){
            int mid = getMid(sort,left,right);
            System.out.println("sort = " + Arrays.toString(sort));
            this.sort(sort,left,mid-1);
            this.sort(sort,mid+1,right);
        }

    }

    private int getMid(int[] sort, int left, int right) {
        int pivot = sort[left];

        while (left < right){

            while (sort[right] >= pivot && left < right) right--;
            sort[left] = sort[right];

            while (sort[left] <= pivot && left<right) left++;
            sort[right] = sort[left];

        }
        sort[left] = pivot;
        return left;
    }


    @Test
    public void tsDate() throws ParseException {


    }

}
