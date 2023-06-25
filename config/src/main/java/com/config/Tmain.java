package com.config;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * @author haizhuangbu
 * @date 2022/10/21 09:24
 * @mark Tmain
 */
public class Tmain {

    public static void main(String[] args) {

//        try {
//            if (true) throw new EmpExe();
//        } catch (EmpExe e) {
//            System.out.println("emp");
//        } catch (Exception e) {
//            System.out.println("error");
//        }
        BigDecimal bigDecimal = new BigDecimal("111.20");
        System.out.println(bigDecimal);

        DecimalFormat decimalFormat = new DecimalFormat("##,###.00");

        String format = decimalFormat.format(999999999.22);
        System.out.println("format = " + format);

        String strNumber = "9,999,999.22";

        try {
            Number parse = decimalFormat.parse(strNumber);
            System.out.println(parse.doubleValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        boolean matches = "123".matches("([0-9\\.\\*])");
        System.out.println(matches);


    }

}
