package com.atguigu.yygh.common.utils;

import com.atguigu.yygh.common.exception.YyghException;
import com.atguigu.yygh.common.result.ResultCode;
import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtHelper {

    static String subject = "YYGH-USER"; //令牌主题
    static String key = "atguigu123"; //签名秘钥
    static long time = 0;//1000 * 60 * 60 * 24; //过期时间 24小时

    /**
     * 生成jwt
     * @param userId 用户id，会作为jwt的载荷存入
     * @param userName 用户名，会作为jwt的载荷存入
     * @return 得到令牌
     */
    public static String createToken(Long userId, String userName) {

        JwtBuilder builder = Jwts.builder();
        String token = builder
                //设置JWT头
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                //载荷
                .setSubject(subject)//主题
                .setExpiration(new Date(System.currentTimeMillis() + time)) //过期时间
                .claim("userId", userId)
                .claim("userName", userName)
                //签名：注意如果使用字符串作为第二个参数，那么要将字符串转换成base64
                .signWith(SignatureAlgorithm.HS256, key.getBytes(StandardCharsets.UTF_8))
                //将三部分用.连起来，生成JWT
                .compact();


        return token;
    }


    /**
     * 解析jwt
     * @param token
     * @return 得到jwt载荷（令牌中存储的数据）
     */
    public static Claims parseToken(String token) {

        JwtParser parser = Jwts.parser();
        //设置签名秘钥
        parser.setSigningKey(key.getBytes(StandardCharsets.UTF_8));
        Jws<Claims> claimsJws = null;
        try {
            claimsJws = parser.parseClaimsJws(token);
        } catch (Exception e) {
            throw new YyghException(ResultCode.ERROR, "需要登录", e);
        }
        Claims body = claimsJws.getBody();
        return body;
    }

    /**
     * 通过token获取userId
     * @param token
     * @return
     */
    public static Long getUserId(String token) {
        if(StringUtils.isEmpty(token)){
            throw new YyghException("需要登录", ResultCode.ERROR);
        }
        Claims claims = parseToken(token);
        Integer userId = (Integer)claims.get("userId");
        return userId.longValue();
    }

    public static String getUserName(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new YyghException("需要登录", ResultCode.ERROR);
        }
        Claims claims = parseToken(token);
        String userName = (String)claims.get("userName");
        return userName;
    }


    public static void main(String[] args) {

        String token = createToken(1L, "张茂龙");
        System.out.println(token);

        String userName = getUserName(token);
        System.out.println(userName);

        Long userId = getUserId(token);
        System.out.println(userId);
    }
}
