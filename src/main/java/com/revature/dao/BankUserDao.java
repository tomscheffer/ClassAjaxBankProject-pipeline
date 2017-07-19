package com.revature.dao;

import com.revature.modal.BankUser;

public interface BankUserDao {

	/*
	 * 	CRUD ops only
	 */
	
	//CREATE
	
	//READ
	public BankUser getBankUserByUsername(BankUser user);
	
	//UPDATE
	
	//DELETE
}
