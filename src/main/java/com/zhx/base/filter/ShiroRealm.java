package com.zhx.base.filter;

import java.util.ArrayList;
import java.util.List;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
/**
 * Created by mulder on 2015/9/29.
 */
public class ShiroRealm extends AuthorizingRealm {

    /**
     * 权限认证
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 根据用户配置用户与权限
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        String name = (String) getAvailablePrincipal(principals);
        List<String> roles = new ArrayList<String>();
        // 简单默认一个用户与角色，实际项目应User user = userService.getByAccount(name);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 增加角色
        info.addRoles(roles);
        return info;
    }

    /**
     * 登录认证
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username =token.getUsername();
        String password = String.valueOf(token.getPassword());
        // 简单默认一个用户,实际项目应User user = userService.getByAccount(token.getUsername());
        if ("test".equals(username)&&"123".equals(password)){
            return new SimpleAuthenticationInfo(username,password,username);
        }
        return null;
    }
}