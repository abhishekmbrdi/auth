package com.mb.auth.auth.response;

public class AuthReposnse {

	
	private boolean hasAccess = false;
	
	private String applciationName="";

	public boolean isHasAccess() {
		return hasAccess;
	}

	public void setHasAccess(boolean hasAccess) {
		this.hasAccess = hasAccess;
	}

	public String getApplciationName() {
		return applciationName;
	}

	public void setApplciationName(String applciationName) {
		this.applciationName = applciationName;
	}
}
