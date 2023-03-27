package com.security;

import com.dao.AdminDao;
import com.model.UmsAdmin;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @创建人 ly
 * @时间 03-27
 * @描述
 */
@Configuration("authorizer")
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private AdminDao adminDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
      //  UmsAdmin umsAdmin = (UmsAdmin) principalCollection.getPrimaryPrincipal();
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        UmsAdmin admin = adminDao.getByName(token.getUsername());
        if( admin != null ) {
            return new SimpleAuthenticationInfo(admin.getId(), admin.getPassword(), getName());
        } else {
            return null;
        }
    }
}
