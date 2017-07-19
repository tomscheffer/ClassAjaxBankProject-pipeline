package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.modal.BankUser;
import com.revature.service.AppService;

@WebServlet("login")
public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		System.out.println("loginServlet - POST");
		
		//Create a BankUser and populate it with the information given from Client-Side
		BankUser clientUser = new BankUser();
		
		//Pulled the username and password by the name attribute in the html form
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		clientUser.setUsername(username);
		clientUser.setPassword(password);
		
		//Validate the credentials entered by the clientUser compared to record stored in the db
									//validateUser returns
										//null if credentials don't match
										//a valid clientUser record if credentials do match
		clientUser = new AppService().validateUser(clientUser);
		
		//Null Check of clientUser
		if(clientUser != null){
			HttpSession session = req.getSession();
			
			session.setAttribute("user", clientUser);
			
			req.getRequestDispatcher("app.html").forward(req,resp);
		}else{
			resp.sendRedirect("login.html");
		}
	}
}
