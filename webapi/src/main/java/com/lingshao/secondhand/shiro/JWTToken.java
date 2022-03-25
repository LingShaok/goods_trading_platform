package com.lingshao.secondhand.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @description
 * @author LingShao
 * @date 2021/4/18
 */
public class JWTToken implements AuthenticationToken {

    // 密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
