package com.revature.servlets;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.modal.BankTx;
import com.revature.modal.BankUser;

/**
 * Servlet implementation class AjaxProcessTxServlet
 */
@WebServlet("ajaxProcessTx")
public class AjaxProcessTxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxProcessTxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//What is the objective??????
			//recieve the JSON object with the tx data and persist to the db
		System.err.println("AjaxProcessTxServlet -POST");
		
		//Grab all parameters, in this case only 1 JSON String
		Map<String, String[]> myMap = request.getParameterMap();
		
		//Get the keySet from the map, returns a Set
		Set<String> txObject = myMap.keySet();
		System.out.println(txObject);

		//API for converting our JSON into a Java Object
		ObjectMapper jackson = new ObjectMapper();
		
		//Convert the keySet into an array, then get the first element(index 0) from that set
		Object obj = txObject.toArray()[0];
		
		//Convert the JSON String into the Class specified in the second object
		BankTx tx = (BankTx) jackson.readValue((String)obj, BankTx.class);
		System.out.println(tx);
		
		HttpSession session = request.getSession();
		BankUser user = (BankUser) session.getAttribute("user"); //the variable we used when the user logged in, in the login servlet
		System.out.println("the user's id: " + user.getId());
		
		/*
		 * 	use service class to process the transaction
		 */
	}

}
