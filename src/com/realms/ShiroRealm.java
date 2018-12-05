package com.realms;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
//	@Override
//	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
//		//总结
//		//1 获取当前的subject，SecurityUtils.getSubject();
//		//2 判断当前用户是否已经被认证，subject。isAuthenticated()
//		//3 如果没有被认证，则需要把用户名和密码进行封装为UsernamePasswordToken
//			// 3.1 创建一个提交页面
//			// 3.2将用户名密码输入后提交到controller
//			// 3.3在controller中获取用户名、密码
//		//4执行登录操作subject.login(token);
//		//5最后登录信息会跳转realm中，在realm中进行获取数据的操作，并返回realm
//			//5.1 如果只进行认证，则继承AuthenticatingRealm
//			//5.2实现getAuthenticationInfo回调方法
//		//6有shiro完成密码的对比工作。
//
//		System.out.println("------------------------->ShiroRealm:");
//
//
//		return null;
//	}
 public class ShiroRealm extends AuthorizingRealm {
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("----------------->ShiroRealm1:------------------------------");
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
		Object credentials = "1234567";//密码
		String realName = getName();//当前用户的名字
		info = new SimpleAuthenticationInfo(principal,credentials,realName);
		return info;
	}


	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

		return null;
	}
}
