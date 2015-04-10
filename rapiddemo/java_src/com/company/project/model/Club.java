package com.company.project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Club {

	private List<TuserInfo> userList = new ArrayList<TuserInfo>();
	private HashMap<String,TuserInfo> userMap = new HashMap<String, TuserInfo>();

	/**
	 * @return the userList
	 */
	public List<TuserInfo> getUserList() {
		return userList;
	}

	/**
	 * @param userList the userList to set
	 */
	public void setUserList(List<TuserInfo> userList) {
		this.userList = userList;
	}

	/**
	 * @return the userMap
	 */
	public HashMap<String, TuserInfo> getUserMap() {
		return userMap;
	}

	/**
	 * @param userMap the userMap to set
	 */
	public void setUserMap(HashMap<String, TuserInfo> userMap) {
		this.userMap = userMap;
	}

}
