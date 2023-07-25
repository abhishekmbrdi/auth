/**
 * 
 */
package com.mb.auth.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mb.auth.auth.response.AuthReposnse;
import com.mb.auth.auth.service.AuthService;

/**
 * @author KABHISH
 *
 */
@RestController
public class AuthController {

	@Autowired
	AuthService authService;
	
	@GetMapping("v1/permissionTree")
	public AuthReposnse permissionTree() {
		System.out.println("I am here....");
		return authService.getPermissionTree();
	}
	
}
