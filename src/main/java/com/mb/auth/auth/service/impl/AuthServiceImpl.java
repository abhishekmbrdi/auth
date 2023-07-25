/**
 * 
 */
package com.mb.auth.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.mb.auth.auth.loader.AuthConstant;
import com.mb.auth.auth.loader.AuthLoader;
import com.mb.auth.auth.response.AuthReposnse;
import com.mb.auth.auth.service.AuthService;

/**
 * @author KABHISH
 *
 */

@Service
public class AuthServiceImpl implements AuthService {

	
	@Autowired
    private Environment environment;
	
	@Override
	public AuthReposnse getPermissionTree() {
		String context = environment.getProperty(AuthConstant.APPLICATION_CONTEXT);
		AuthReposnse authReposnse = new AuthReposnse();
		authReposnse.setApplciationName(environment.getProperty(AuthConstant.APPLICATION_NAME));
		
		if(null != context) {
			List<String> entitlements = AuthLoader.INSTANCE.getEntitlements();
			for (String entitlement : entitlements) {
				if(entitlement.contains(context)) {
					authReposnse.setHasAccess(true);
					authReposnse.setApplciationName(environment.getProperty(AuthConstant.APPLICATION_NAME));;
					break;
				}
			}
		}
		return authReposnse;
	}

}
