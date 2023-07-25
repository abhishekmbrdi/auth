/**
 * 
 */
package com.mb.auth.auth.loader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KABHISH
 *
 */
public enum AuthLoader {

	
	INSTANCE;
	
	private List<String> entitlementList = new ArrayList<String>();
	
	public List<String> getEntitlements(){
		return entitlementList;
	}
}
