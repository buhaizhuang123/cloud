package com.cloud.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.sys.dto.User;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author haizhuangbu
 * @date 2022/5/6 09:21
 * @mark JwtUtils
 */
public class JwtUtils {

    /**
     * @author haizhuang.bu
     * @date 09:22 2022/5/6
     * @function 签名需要的私有密钥
     */
    private static final String SECRET = "mySecret";

    /**
     * @param user 登录人
     * @author haizhuang.bu
     * @date 09:23 2022/5/6
     * @function 通过用户信息生成jwt
     */
    public static String createJwt(User user) {
        return createJwt(user, -1);
    }

    /**
     * @param expire 设置超时时间
     * @param user   用户
     * @author haizhuang.bu
     * @date 09:25 2022/5/6
     * @function
     */
    private static String createJwt(User user, int expire) {
        Map map = JSON.parseObject(JSON.toJSONString(user), Map.class);
        SignatureAlgorithm hs256 = SignatureAlgorithm.HS256;
        long startDate = System.currentTimeMillis();
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(String.valueOf(user.getUserId()))
                // 签发者
                .setIssuer("mr.bu")
                .setClaims(map)
                .setIssuedAt(new Date())
                .signWith(hs256, SECRET);
        if (expire > 0) {
            long expMillis = startDate + expire;
            jwtBuilder.setExpiration(new Date(expMillis));
        }
        return jwtBuilder.compact();
    }

    /**
     * 解析jwt，解析时若过期会抛出ExpiredJwtException异常
     *
     * @param jwt token
     * @return jwt对象
     */
    public static Claims parseJwt(String jwt) {
        //解析jwt
        JwtParser parser = Jwts.parser();
        //获取解析后的对象
        Claims claims = Jwts.parser()
                //设置签名秘钥，和生成的签名的秘钥一模一样
                .setSigningKey(SECRET)
                //设置需要解析的jwt
                .parseClaimsJws(jwt)
                .getBody();

        return claims;
    }


    public static void main(String[] args) {

        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        String jwt = JwtUtils.createJwt(user);
        System.out.println("jwt = " + jwt);

        Claims claims = JwtUtils.parseJwt(jwt);
        String id = claims.getId();
        System.out.println("id = " + id);
        System.out.println("claims = " + claims);

    }

}
