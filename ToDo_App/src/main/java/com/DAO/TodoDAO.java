package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Entity;

public class TodoDAO {
	private Connection conn;

	public TodoDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	public boolean addTodo(String name,String todo,String status)
	{
		boolean f= false;
		
		try
		{
			String sql = "insert into todo_app(name,todo,status) values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, todo);
			ps.setString(3, status);
			int i = ps.executeUpdate();
			if(i==1)
			{
				f=true;
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
		
		
		return f;
	}
	public List<Entity> getTodo()
	{
		List<Entity> list = new ArrayList<>();
		Entity t = null;
		try
		{
			String sql = "select * from todo_app";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				t = new Entity();
				t.setId(rs.getInt(1));
				t.setName(rs.getString(2));
				t.setStatus(rs.getString(3));
				t.setTodo(rs.getString(4));
				list.add(t);
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return list;
		
	}
	public Entity getTodoById(int id)
	{
		Entity t = null;
		try
		{
			String sql = "select * from todo_app where id =?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				t = new Entity();
				t.setId(rs.getInt(1));
				t.setName(rs.getString(2));
				t.setStatus(rs.getString(3));
				t.setTodo(rs.getString(4));
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return t;
	}
	public boolean updateTodo(Entity t)
	{
		boolean f =false;
		try
		{
			String sql = "update todo_app set name =? todo = ? where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,t.getName());
			ps.setString(2,t.getTodo());
			ps.setString(3, t.getStatus());
			ps.setInt(4, t.getId());
			int i = ps.executeUpdate();
			if(i==1)
			{
				f=true;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return f;
	}
	public boolean deleteTodo(int id)
	{
		boolean f =false;
		try
		{
			String sql = "delete from todo_app where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, t.getId());
			int i = ps.executeUpdate();
			if(i==1)
			{
				f=true;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return f;
		
	}

}
