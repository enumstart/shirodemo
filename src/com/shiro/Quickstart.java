package com.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quickstart {

    private static final transient Logger log = LoggerFactory.getLogger(Quickstart.class);

    public static void main(String[] args) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject currentSubject = SecurityUtils.getSubject();
        //获取的到session
        Session session = currentSubject.getSession();
        session.setAttribute("someKey", "aValue");
        String value = (String) session.getAttribute("someKey");
        if ("aValue".equals(value)){
            System.out.println("....session of value " + value);
        }
        //测试当前用户是否已经被认证（是否登入）
        if (!currentSubject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            token.setRememberMe(true);
            try {
                currentSubject.login(token);
            }// 若没有指定的账户, 则 shiro 将会抛出 UnknownAccountException 异常.
            catch (UnknownAccountException uae) {
                log.info("----> There is no user with username of " + token.getPrincipal());
                return;
            }
            // 若账户存在, 但密码不匹配, 则 shiro 会抛出 IncorrectCredentialsException 异常。
            catch (IncorrectCredentialsException ice) {
                log.info("----> Password for account " + token.getPrincipal() + " was incorrect!");
                return;
            }
            // 用户被锁定的异常 LockedAccountException
            catch (LockedAccountException lae) {
                log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.");
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            // 所有认证时异常的父类.
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
            }
            if (currentSubject.hasRole("schwartz")){
                System.out.println();
            }
            // 测试用户是否具备某一个行为
            //schwartz = lightsaber:*
            if (currentSubject.isPermitted("lightsaber:weild")){

            }
            // 测试用户是否具备某一个行为.
            //goodguy = user:delete:zhangsan
            if (currentSubject.isPermitted("user:delete:zhangsan")){

            }
            currentSubject.logout();
        }
    }
}
