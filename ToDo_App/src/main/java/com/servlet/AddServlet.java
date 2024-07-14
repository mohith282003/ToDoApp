package com.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.DAO.TodoDAO;
import com.DB.DBConnect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String todo = req.getParameter("todo");
		String status = req.getParameter("status");
		//System.out.println(username +" "+ todo+" "+status);
		try {
			TodoDAO dao = new TodoDAO(DBConnect.getConn());
			boolean f = dao.addTodo(username, todo, status);
			HttpSession session = req.getSession();
			if(f==true)
			{
				//System.out.println("Data Inserted Successfully");
				session.setAttribute("sucMsg", "Todo Added Successfully");
				resp.sendRedirect("index.jsp");
			
			}
			else
			{
				session.setAttribute("failedMsg", "Todo Added Successfully");
				resp.sendRedirect("index.jsp");
				//System.out.println("Error");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
