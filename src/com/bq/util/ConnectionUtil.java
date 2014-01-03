package com.bq.util;

import java.sql.Connection;
import java.sql.SQLException;
public class ConnectionUtil {
	//返回线程绑定的连接 flag为true返回一个新连接
	public static  Connection getCurrentConnection(){
			Connection conn = getInnerConnection();
			if(conn == null){
				try {
					conn = DataSourceProvider.getConnection();
					conn.setAutoCommit(false);
					ThreadUtil.set(conn);
					System.out.println("打开数据库连接！");
				} catch (SQLException e) {
					System.out.println("连接异常！");
					e.printStackTrace();
				}
			}
			return conn;
		
	}
	public static void  rollback(){
	   if(getInnerConnection()!=null){	
		   try {
			   getInnerConnection().rollback();
		} catch (Exception e) {
			System.out.println("回滚异常！");
		}
	   }
	}
	
	private static Connection getInnerConnection(){
		return (Connection) ThreadUtil.get();
	}
	
	
	public static void closeConnection() throws Exception{
		getCurrentConnection().commit();
		getCurrentConnection().close();
		System.out.println("关闭数据库连接！");
		ThreadUtil.set(null);
	}
	
	
private static boolean hasConnBinding(){
		
		return  ThreadUtil.get()!=null;
	}
	
	public static Connection getConnection(){
		if(hasConnBinding()){
			return (Connection) ThreadUtil.get();
		}
		Connection conn = null;
		try{
			conn = DataSourceProvider.getConnection();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void releaseConnection(Connection conn){
		if(!hasConnBinding()){
			try {
				if(conn!=null){
					conn.commit();
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
