package com.shiro.realms;

import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * Created by enum on 2018/1/24.
 */
public class SecondRealm extends AuthenticatingRealm{//只做认证实现此接口即可

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("----->>>doGetAuthenticationInfo " + token);
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        if ("unknow".equals(username)){
            throw new UnknownAccountException("未知账号");
        }
        if ("monister".equals(username)){
            throw new LockedAccountException("账号被锁定");
        }

        String principal = username;
        //数据库密码
//        String credentials = "123456";
//        String credentials = "fc1709d0a95a6be30bc5926fdb7f22f4";//数据库查询出来的结果
        String credentials = null;
        if ("admin".equals(username)){
            credentials = "ce2f6417c7e1d32c1d81a797ee0b499f87c5de06";
        }else if("user".equals(username)){
            credentials = "073d4c3ae812935f23cb3f2a71943f49e082a718";
        }
        String realmName = getName();
        //盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        SimpleAuthenticationInfo auth = null;// new SimpleAuthenticationInfo(principal, credentials, realmName);
        auth = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);//需要把盐值返回
        return auth;
    }

    public static void main(String[] args) {
        String algorithmName = "SHA1";   //加密的方式
        String source = "123456";       //需要加密的
//        String salt = null;             //盐(为了两个密码相同情况加密后的结果也不一样)
        String salt = "admin";             //盐(为了两个密码相同情况加密后的结果也不一样)
        int hashIterations = 1024;      //加密次数
        Object result = new SimpleHash(algorithmName, source, salt, hashIterations);
        System.out.println(result);
    }
}
