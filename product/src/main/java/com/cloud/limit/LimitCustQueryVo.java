package com.cloud.limit;

import lombok.Data;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author haizhuangbu
 * @date 2022/11/8 09:34
 * @mark LimitCustQueryVo
 */
@Data
public class LimitCustQueryVo {

    private String custName;

    private String idNo;

    private String mobilePhone;

    public static void main(String[] args) {



        SpelExpressionParser parse = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();


        String name = parse.parseExpression("name").getValue(context, String.class);
        System.out.println("name = " + name);

    }
}
