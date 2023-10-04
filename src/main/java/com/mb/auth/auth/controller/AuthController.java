/**
 * 
 */
package com.mb.auth.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mb.auth.auth.response.AuthReposnse;
import com.mb.auth.auth.response.Organization;
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
		//BTF Component, who the user is, what entitlemnts he has
		return authService.getPermissionTree();
	}
	
	@GetMapping("v1/tree")
	public String createHeirarchy() {
		Organization rootOrganization = authService.getHeirarchy();
		return rootOrganization.printHierachy("","");
	}
	
	@GetMapping("v1/tree/search")
	public String createPartHeirarchy(@RequestParam(required = true) String id) {
		Organization rootOrganization = authService.getPartHeirarchy(id);
		if(null == rootOrganization) {
			return "No such Organization available, please try with some other ID.";
		}
		return rootOrganization.printHierachy("","");
	}
	
	
}
