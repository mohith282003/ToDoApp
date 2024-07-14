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

@WebServlet("/delete")
public class deleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		try {
			TodoDAO dao = new TodoDAO(DBConnect.getConn());
			boolean f = dao.deleteTodo(id);
			HttpSession session = req.getSession();
			if (f) {
                session.setAttribute("sucMsg", "Todo deleted Successfully");
                resp.sendRedirect("index.jsp");
            } else {
                session.setAttribute("failedMsg", "Failed to update Todo");
                resp.sendRedirect("index.jsp");
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
