package com.revature.service;

import com.revature.dao.BankUserDao;
import com.revature.dao.BankUserDaoImpl;
import com.revature.modal.BankUser;

public class AppService {

	public BankUser validateUser(BankUser user) {
		BankUserDao dao = new BankUserDaoImpl();

		BankUser dbUser = dao.getBankUserByUsername(user);
		if (dbUser != null) {
			if (dbUser.getUsername().equals(user.getUsername()) && dbUser.getPassword().equals(user.getPassword())) {
				return dbUser;
			}
		}
		return null;
	}
}
