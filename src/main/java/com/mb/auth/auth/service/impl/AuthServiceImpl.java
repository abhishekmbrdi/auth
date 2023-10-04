/**
 * 
 */
package com.mb.auth.auth.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.mb.auth.auth.loader.AuthConstant;
import com.mb.auth.auth.loader.AuthLoader;
import com.mb.auth.auth.loader.HierarchyLoader;
import com.mb.auth.auth.response.AuthReposnse;
import com.mb.auth.auth.response.Organization;
import com.mb.auth.auth.service.AuthService;
import com.mb.auth.auth.store.OrgStore;

/**
 * @author KABHISH
 *
 */

@Service
public class AuthServiceImpl implements AuthService {


	@Autowired
	private Environment environment;

	@Autowired
	private ApplicationContext ctx;

	@Override
	public AuthReposnse getPermissionTree() {
		String context = environment.getProperty(AuthConstant.APPLICATION_CONTEXT);
		AuthReposnse authReposnse = new AuthReposnse();
		authReposnse.setApplciationName(environment.getProperty(AuthConstant.APPLICATION_NAME));
		//USer will entitlements
		if(null != context) {
			List<String> entitlements = AuthLoader.INSTANCE.getEntitlements();//All the entitlement the prduct has
			for (String entitlement : entitlements) {
				if(entitlement.contains(context)) {
					authReposnse.setHasAccess(true);
					authReposnse.setApplciationName(environment.getProperty(AuthConstant.APPLICATION_NAME));
					break;
				}
			}
		}
		return authReposnse;
	}


	@Override
	public Organization getHeirarchy() {
		loadDataFromSource();
		OrgStore.eINSTANCE.getRootOrganization().printHierachy("","");
		return OrgStore.eINSTANCE.getRootOrganization();
	}


	private void loadDataFromSource() {
		if(OrgStore.eINSTANCE.getOrgList().isEmpty()) {
			Resource resource = ctx.getResource("classpath:sales_unit_de.csv");
			List<Organization> records = new ArrayList<Organization>();

			try (Scanner scanner = new Scanner(resource.getFile().toPath())) {
				while (scanner.hasNextLine()) {
					records.add(getRecordFromLine(scanner.nextLine()));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			records.remove(0);
			OrgStore.eINSTANCE.getOrgList().addAll(records);
			getHierarchicalList(OrgStore.eINSTANCE.getOrgList());
			OrgStore.eINSTANCE.setRootOrganization(OrgStore.eINSTANCE.getOrgList().get(0));
		}
	}




	private Organization getRecordFromLine(String line) {
		List<String> values = new ArrayList<String>();
		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(",");
			while (rowScanner.hasNext()) {
				values.add(rowScanner.next());
			}
		}
		Organization organization = convertToOrganization(values);
		return organization;
	}

	private Organization convertToOrganization(List<String> values) {
		Organization organization = new Organization();
		organization.setLevel(values.get(1));
		organization.setParentID(values.get(2));
		organization.setSalesUnitID(values.get(3));
		organization.setCountry(values.get(4));
		organization.setCountryISO(values.get(5));
		organization.setClassicID(values.get(6));
		organization.setCompanyID(values.get(7));
		organization.setVbet(values.get(8));
		return organization;
	}


	public List<Organization> getHierarchicalList(final List<Organization> originalList) {
		List<Organization> copyList = new ArrayList<>(originalList);

		copyList.forEach(element -> {
			originalList
			.stream()
			.filter(parent -> parent.getSalesUnitID().equals(element.getParentID())  && !parent.getSalesUnitID().equals(element.getSalesUnitID()))
			.findAny()
			.ifPresent(parent -> {
				parent.getChildOrganizations().add(element);
			});
		});
		return originalList;
	}
	
	
	@Override
	public Organization getPartHeirarchy(String id) {
		loadDataFromSource();
		for (Organization organization : OrgStore.eINSTANCE.getOrgList()) {
			if(!organization.getClassicID().equalsIgnoreCase(id)) {
				return OrgStore.eINSTANCE.getRootOrganization().search1(id);
			}else {
				return organization;
			}
		}
		return null;
	}

}
