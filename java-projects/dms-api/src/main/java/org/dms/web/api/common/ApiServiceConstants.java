package org.dms.web.api.common;

public class ApiServiceConstants {

	private ApiServiceConstants() {
	}

	public static final String API_VERSION = "/api/v1";

	public static final String CONSTANTS_CONTROLLER_URI = API_VERSION + "/constants";
	public static final String GET_ALL_SYSTEM_VARIABLES_URI = "/sysvars/all";
	public static final String GET_SYSTEM_VARIABLES_BY_NAME_URI = "/sysvars/name/{name}";
	public static final String GET_SYSTEM_VARIABLES_BY_VALUE_URI = "/sysvars/value/{value}";
	public static final String GET_SYSTEM_VARIABLES_BY_ID_URI = "/sysvars/id/{id}";
	public static final String POST_ADD_SYSTEM_VARIABLES = "/sysvars/add";

}
