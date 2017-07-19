package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.modal.BankUser;

public class BankUserDaoImpl implements BankUserDao {

	private static final String USERNAME = "aws_bank_db";
	private static final String PASSWORD = "p4ssw0rd";
	private static final String URL = "jdbc:oracle:thin:@octocat.co2covhnzldx.us-east-2.rds.amazonaws.com:1521:ORCL";
	
	@Override
	public BankUser getBankUserByUsername(BankUser user) {
			BankUser dbUser = null;
			try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);){
				String sql = "SELECT * FROM bank_user WHERE bu_username = ?";
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, user.getUsername());
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					dbUser = new BankUser(rs.getInt("BU_ID"),rs.getString("BU_USERNAME"),rs.getString("BU_FIRST_NAME"),
							rs.getString("BU_LAST_NAME"), rs.getString("BU_PASSWORD"));
				};
				
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		
		return dbUser;
	}

}
