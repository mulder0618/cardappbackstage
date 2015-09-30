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
     * Ȩ����֤
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // �����û������û���Ȩ��
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        String name = (String) getAvailablePrincipal(principals);
        List<String> roles = new ArrayList<String>();
        // ��Ĭ��һ���û����ɫ��ʵ����ĿӦUser user = userService.getByAccount(name);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // ���ӽ�ɫ
        info.addRoles(roles);
        return info;
    }

    /**
     * ��¼��֤
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
        // ��Ĭ��һ���û�,ʵ����ĿӦUser user = userService.getByAccount(token.getUsername());
        if ("test".equals(username)&&"123".equals(password)){
            return new SimpleAuthenticationInfo(username,password,username);
        }
        return null;
    }
}