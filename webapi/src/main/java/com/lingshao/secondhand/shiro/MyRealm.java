package com.lingshao.secondhand.shiro;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @description
 * @author LingShao
 * @date 2021/4/18
 */
public class MyRealm extends AuthorizingRealm {

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 检测用户权限的时候调用此方法，在需要验证权限的接口上添加注解@RequiresPermissions @RequiresRoles
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ArrayList<String> pers = new ArrayList<>();
        pers.add("data:list");
        Set<String> permission = new HashSet<>(pers);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        return new SimpleAuthenticationInfo(authenticationToken.getCredentials(), authenticationToken.getCredentials(), this.getName());
    }
}
