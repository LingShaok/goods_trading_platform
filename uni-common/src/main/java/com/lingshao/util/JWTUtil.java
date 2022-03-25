package com.lingshao.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author LingShao
 * @since 2018-12-02
 */
public class JWTUtil {

    public static final Long EXPIRE_TIME = 60*1000L;
    /**
     * 校验token是否正确
     * @param token 密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String userNo, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("userNo", userNo)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUserNo(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userNo").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,指定时间后过期,一经生成不可修改，令牌在指定时间内一直有效
     * @param userNo 用户编号#学校编号
     * @param secret 用户的密码
     * @return 加密的token
     */
    public static String sign(String userNo, String secret, Long expireTime) {
        try {
            Date date = new Date(System.currentTimeMillis()+ expireTime*EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim("userNo", userNo)
                    .withExpiresAt(date)
                    .sign(algorithm);

        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 验证登录是否过期
     * @author patton（刘彦辉）
     * @param token
     * @date 2019-5-31 10:21:18
     * @return
     */
    public static boolean verifyWhetherOverdue(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            //过期时间
            long time = jwt.getExpiresAt().getTime();
            //当前系统时间
            long nowDate = System.currentTimeMillis();
            //如果两个时间差小于0，即登录过期
            if (time - nowDate < 0){
                return false;
            }else {
                return true;
            }
        } catch (JWTDecodeException e) {
            return false;
        }
    }
}
