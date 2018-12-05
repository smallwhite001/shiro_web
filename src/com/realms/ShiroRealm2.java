package com.realms;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

public class ShiroRealm2  extends AuthorizingRealm {
    //验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("------------ShiroRealm2------------->token2:" + token.hashCode());
        System.out.println("------------ShiroRealm2------------->ShiroRealm:");
        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        //1.收集用户身份凭证，用户名/密码
        String username = userToken.getUsername();
        //2.从数据库中获取用户信息
        //从数据可中获取用户名
        //3.如果用户不存在，抛出异常
        if("unknown".equals(username)) {
            throw new UnknownAccountException("--------------->用户不存在");
        }
        //4.用户名锁定，抛出异常
        if("test".equals(username)) {
            throw new LockedAccountException("--------------->用户被锁定");
        }
        //5.返回SimpleAuthenticationInfo对象进行比对
        SimpleAuthenticationInfo info = null;
        Object principal = username;//实体信息

        Object credentials = "null";//密码
        if("admin".equals(username)){
            credentials = "0287040c474dbf44cdeb17eebb99d828";
        }else if("user".equals(username)){
            credentials = "645f94dcfe65935ca4b24d3a465ece0a";
        }
        String realName = getName();//当前用户的名字
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        //info = new SimpleAuthenticationInfo(principal,credentials,realName);
        info = new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,realName);
        System.out.println(info.getCredentials());
        return info;

    }
    public static void  main(String[] args){
        //指定加密算法名称
        String hashAlgorithmName = "MD5";
        Object credentials = "1234567";
        //盐值，必须唯一
        Object salt = null;
        salt = ByteSource.Util.bytes("admin");
        //加密次数
        int hashIterations = 1;
        Object result = new SimpleHash(hashAlgorithmName,credentials,salt,hashIterations);
        System.out.println(result);
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("----------------------->doGetAuthorizationInfo:授权");
        //首先principals中获取登录信息
        Object principal = principals.getPrimaryPrincipal();
        System.out.println(principal);
        //根据登录信息来获取角色权限
        Set<String> roles = new HashSet<>();
        roles.add("user");
        if("admin".equals(principal)){
            roles.add("admin");
        }
        //构建权限信息，并返回SimpleAuthorizationInfo对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        return info;
    }
}
