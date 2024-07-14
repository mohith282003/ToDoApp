package com.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.DAO.TodoDAO;
import com.DB.DBConnect;
import com.entity.Entity;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	
    	
    	System.out.println("Received parameters:");
    	for (String paramName : req.getParameterMap().keySet()) {
    	    System.out.println(paramName + ": " + req.getParameter(paramName));
    	}
        HttpSession session = req.getSession();
        
        String idString = req.getParameter("id");
        String username = req.getParameter("username");
        String todo = req.getParameter("todo");
        String status = req.getParameter("status");
        
        // Check if any required parameters are missing
        if (idString == null || username == null || todo == null || status == null) {
            session.setAttribute("failedMsg", "Missing required parameters");
            resp.sendRedirect("index.jsp");
            return;
        }
        
        int id;
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            session.setAttribute("failedMsg", "Invalid ID format");
            resp.sendRedirect("index.jsp");
            return;
        }
        
        try {
            TodoDAO dao = new TodoDAO(DBConnect.getConn());
            Entity t = new Entity();
            t.setId(id);
            t.setName(username);
            t.setTodo(todo);
            t.setStatus(status);
            
            boolean f = dao.updateTodo(t);
            
            if (f) {
                session.setAttribute("sucMsg", "Todo Updated Successfully");
            } else {
                session.setAttribute("failedMsg", "Failed to update Todo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("failedMsg", "Database error occurred");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("failedMsg", "An unexpected error occurred");
        }
        
        resp.sendRedirect("index.jsp");
    }
}