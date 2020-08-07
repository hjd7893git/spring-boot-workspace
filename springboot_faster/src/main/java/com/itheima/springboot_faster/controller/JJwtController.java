package com.itheima.springboot_faster.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JJwtController {
    public static void main(String[] args) {
        validateToken(getJToken());
    }

    /**
     * 创建token
     *
     * @return
     */
    public static String getJToken() {
        long now = System.currentTimeMillis();
        long exp = now + 1000 * 30;//30秒过期

        JwtBuilder builder = Jwts.builder()
                .setId("888")   //设置唯一编号
                .setSubject("小白")//设置主题  可以是JSON数据
                .setIssuedAt(new Date())//签发时间
                .setExpiration(new Date(exp))//过期时间//设置签发日期
                .claim("hjd","123")  //可以加上自己定义的其它参数
                .signWith(SignatureAlgorithm.HS256, "haha");//设置签名 使用HS256算法，并设置SecretKey(字符串)
        System.out.println(builder.compact());
        return builder.compact();
    }

    /**
     * 验证token
     * 我们刚才已经创建了token ，在web应用中这个操作是由服务端进行然后发给客户端，
     * 客户端在下次向服务端发送请求时需要携带这个token（这就好像是拿着一张门票一样），
     * 那服务端接到这个token 应该解析出token中的信息（例如用户id）,根据这些信息查询数据库返回相应的结果。
     */
    public static void validateToken(String token) {
        Claims claims = Jwts.parser().setSigningKey("haha").parseClaimsJws(token).getBody();
        System.out.println(claims);
    }
}
