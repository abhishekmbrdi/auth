package com.mb.auth.auth.service;

import com.mb.auth.auth.response.AuthReposnse;
import com.mb.auth.auth.response.Organization;

public interface AuthService {

	
	public AuthReposnse getPermissionTree();

	public Organization getHeirarchy();
	
	public Organization getPartHeirarchy(String id);
	
	
}
