package org.tai.twitterchat.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.tai.twitterchat.domain.model.User;
import org.tai.twitterchat.domain.model.UserRole;

public class TwitterChatRealm extends AuthorizingRealm {
	private User adminUser = new User("admin", "admin", UserRole.ADMIN);

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken) {
		if (authenticationToken instanceof UsernamePasswordToken) {
			UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
			String username = usernamePasswordToken.getUsername();
			char[] password = usernamePasswordToken.getPassword();
			if (!UsersStorage.INSTANCE.checkCredentials(username,
					String.valueOf(password))) {
				throw new AuthenticationException("Invalid credentials");
			}

			SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
					username, password, "adminRealm");
			return simpleAuthenticationInfo;
		} else {
			return null;
		}
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection pricipalCollection) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		User user = UsersStorage.INSTANCE.findUser((String)pricipalCollection
				.getPrimaryPrincipal());
		
		if(user == null) {
			info.addRole("observer");
			return info;
		}
		
		switch (user.getUserRole()) {
		case ADMIN:
			info.addRole("admin");
		case WRITER:
			info.addRole("writer");
		default:
			info.addRole("observer");
		}
		
		return info;
	}

}
