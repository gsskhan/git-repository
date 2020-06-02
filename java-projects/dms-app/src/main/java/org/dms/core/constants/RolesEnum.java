package org.dms.core.constants;

public enum RolesEnum {
	
	SYSADMIN	("Role with access to all system functions action."), 
	APPROVER	("Role with access to approve documents."),
	REVIEWER	("Role with access to evaluate papers."),
	PUBLISHER	("Role with access to submit papers only.");
	
	private final String description;
	
	private RolesEnum(String roleDescription) {
		this.description = roleDescription;
	}
	
	public String getRoleDescription() {
		return this.description;
	}

}
