/**
 * 
 */
package com.mb.auth.auth.response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KABHISH
 *
 */
public class Organization {

	private String level;

	private String parentID;

	private String salesUnitID;

	private String country;

	private String countryISO;

	private String classicID;

	private String companyID;

	private String vbet;

	private List<Organization> childOrganizations = new ArrayList<Organization>();

	@Override
	public String toString() {
		return "Hierarchy Level: " + level + ", Parent Organization: " + parentID + ", Sales Unit ID: " + salesUnitID + ", Classical ID:  " + classicID;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getSalesUnitID() {
		return salesUnitID;
	}

	public void setSalesUnitID(String salesUnitID) {
		this.salesUnitID = salesUnitID;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryISO() {
		return countryISO;
	}

	public void setCountryISO(String countryISO) {
		this.countryISO = countryISO;
	}

	public String getClassicID() {
		return classicID;
	}

	public void setClassicID(String classicID) {
		this.classicID = classicID;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public String getVbet() {
		return vbet;
	}

	public void setVbet(String vbet) {
		this.vbet = vbet;
	}

	public List<Organization> getChildOrganizations() {
		return childOrganizations;
	}

	public boolean equals(Object o) {
		if(o == null)
		{
			return false;
		}
		if (o == this)
		{
			return true;
		}
		if (getClass() != o.getClass())
		{
			return false;
		}

		Organization e = (Organization) o;
		return (this.getSalesUnitID().equals(e.getSalesUnitID()));
	}

	@Override
	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + getSalesUnitID().hashCode();
		return getSalesUnitID().hashCode();
	}

	public String printHierachy(String tabInheritence, String hierachy) {
		String tab = "-----";
		tab = tab+tabInheritence;
		System.err.println(tab+ this);
		hierachy = hierachy + tab + this +"<br/>";
		if(!getChildOrganizations().isEmpty()) {
			for (Organization organization : getChildOrganizations()) {
				hierachy = organization.printHierachy(tab, hierachy);
			}
		}
		return hierachy;
	}

	public Organization search(String id) {
		if(!getChildOrganizations().isEmpty()) {
			if(getChildOrganizations().contains(this)) {
				return getChildOrganizations().get(getChildOrganizations().indexOf(this));
			}else {
				for (Organization org : getChildOrganizations()) {
					Organization search = org.search(id);
					if(null != search) {
						return search;
					}
				}

			}
		}
		return null;
	}
	
	public Organization search1(String id) {
		if(!getChildOrganizations().isEmpty()) {
			if(null != getChildOrgBasedOnClassicID(id)) {
				return getChildOrganizations().get(getChildOrganizations().indexOf(getChildOrgBasedOnClassicID(id)));
			}else {
				for (Organization org : getChildOrganizations()) {
					Organization search = org.search1(id);
					if(null != search) {
						return search;
					}
				}

			}
		}
		return null;
	}

	
	public Organization getChildOrgBasedOnClassicID(String id) {
		if(!getChildOrganizations().isEmpty()) {
			for (Organization organization : childOrganizations) {
				if(organization.getClassicID().equalsIgnoreCase(id)) {
					return organization;
				}
			}
		}
		return null;
	}
}
