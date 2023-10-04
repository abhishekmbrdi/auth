/**
 * 
 */
package com.mb.auth.auth.store;

import java.util.ArrayList;
import java.util.List;

import com.mb.auth.auth.response.Organization;

/**
 * @author KABHISH
 *
 */
public enum OrgStore {

	eINSTANCE;

	private List<Organization> orgList = new ArrayList<Organization>();
	
	private Organization rootOrganization;

	public List<Organization> getOrgList() {
		return orgList;
	}

	public Organization getRootOrganization() {
		return rootOrganization;
	}

	public void setRootOrganization(Organization rootOrganization) {
		this.rootOrganization = rootOrganization;
	}
	
}
