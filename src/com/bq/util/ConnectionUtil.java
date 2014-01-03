package com.bq.util;

import java.sql.Connection;
import java.sql.SQLException;
public class ConnectionUtil {
	//�����̰߳󶨵����� flagΪtrue����һ��������
	public static  Connection getCurrentConnection(){
			Connection conn = getInnerConnection();
			if(conn == null){
				try {
					conn = DataSourceProvider.getConnection();
					conn.setAutoCommit(false);
					ThreadUtil.set(conn);
					System.out.println("�����ݿ����ӣ�");
				} catch (SQLException e) {
					System.out.println("�����쳣��");
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
			System.out.println("�ع��쳣��");
		}
	   }
	}
	
	private static Connection getInnerConnection(){
		return (Connection) ThreadUtil.get();
	}
	
	
	public static void closeConnection() throws Exception{
		getCurrentConnection().commit();
		getCurrentConnection().close();
		System.out.println("�ر����ݿ����ӣ�");
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
