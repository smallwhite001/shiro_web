package com.realms;

import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

public class ShiroRealm3 extends AuthenticatingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("------------ShiroRealm3------------>token2:" + token.hashCode());
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
            credentials = "b5f50017653c165b94576300a4481ef80456ad35";
        }else if("user".equals(username)){
            credentials = "8b5ece381e3b4ba682d87566fe550833b513d518";
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
        String hashAlgorithmName = "SHA1";
        Object credentials = "1234567";
        //盐值，必须唯一
        Object salt = null;
        salt = ByteSource.Util.bytes("user");
        //加密次数
        int hashIterations = 1;
        Object result = new SimpleHash(hashAlgorithmName,credentials,salt,hashIterations);
        System.out.println(result);
    }
}
