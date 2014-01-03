package com.bq.orm.core;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bq.orm.mapper.DataMapper;
import com.bq.orm.mapper.RowMapper;
import com.bq.orm.mapper.RowMapperImpl;
import com.bq.util.ConnectionUtil;

public class BaseDAOImpl<T extends Serializable> implements IDAO{

	@Override
	public boolean delete(Object t) {
		boolean flag = false;
		Connection conn = null;
		try{
			conn = ConnectionUtil.getConnection();
			String s =SQLBuilder.builderDelete(t);
			PreparedStatement pstmt = conn.prepareStatement(s);
			DataMapper.setDeleteValue(pstmt, t);
			if(pstmt.executeUpdate()==1){
				
				flag = true;
				System.out.println("success delete");
			}
			System.out.println(t);
			pstmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
		  ConnectionUtil.releaseConnection(conn);
		}
		return flag;
		
	}

	@Override
	public boolean deleteBatch(List objects) {
		Connection conn = null;
		try{
			conn = ConnectionUtil.getConnection();
			String s =SQLBuilder.builderDelete(objects.get(0));
			PreparedStatement pstmt = conn.prepareStatement(s);
			
			int i=1;
			for(Object t:objects){
				pstmt.addBatch();
				if(i++%20==0){
					pstmt.executeBatch();
				}
			}
			pstmt.executeBatch();
			pstmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
		  ConnectionUtil.releaseConnection(conn);
		}
		return true;
		
	}
	@Override
	public Integer getCount(Class clazz) {
		int count = 0;
		Connection conn = null;
		try{
			conn = ConnectionUtil.getConnection();
			String s =SQLBuilder.builderSelectCount(clazz);
			PreparedStatement pstmt = conn.prepareStatement(s);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				count =  rs.getInt(1);
				break;
			}
			pstmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
		  ConnectionUtil.releaseConnection(conn);
		}
		return count;
	}

	@Override
	public boolean save(Object t) {
		boolean flag = false;
		Connection conn = null;
		try{
			conn = ConnectionUtil.getConnection();
			String s =SQLBuilder.builderSave(t);
			PreparedStatement pstmt = conn.prepareStatement(s);
			DataMapper.setInsertValue(pstmt, t);
			if(pstmt.executeUpdate()==1){
				flag = true;
			}
			pstmt.close();
		}catch (Throwable e) {
			e.printStackTrace();
		}
		finally{
		  ConnectionUtil.releaseConnection(conn);
		}
		return flag;
		
	}
	

	@Override
	public boolean update(Object t) {
		boolean flag = false;
		Connection conn = null;
		try{
			conn = ConnectionUtil.getConnection();
			String s =SQLBuilder.builderUpdate(t);
			PreparedStatement pstmt = conn.prepareStatement(s);
			DataMapper.setUpdateValue(pstmt, t);
			if(pstmt.executeUpdate()==1){
				flag = true;
			}
			pstmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
		  ConnectionUtil.releaseConnection(conn);
		}
		return flag;
	}

	

	public static void main(String[] args) {

//		Test t1 = new Test();
//		Test t2 = new Test();
//		Test t3 = new Test();
//		Test t4 = new Test();
//		Test t5 = new Test();
//		t1.setId("1");
//		t1.setName("zjamgsssssslaoi");
//		t2.setId("2");
//		t3.setId("33");
//		t4.setId("22");
//		List l = new ArrayList();
//		l.add(t1);
//		l.add(t2);
//		l.add(t3);
//		l.add(t4);
//		IDAO dao= new BaseDAOImpl();
//		String query = "and value=?";
//		System.out.println(dao.getCount(query, Test.class,"232"));
//		QueryBuilder q = new SimpleQueryBuilder();
//		q.eq("value", "232");
//		System.out.println("==============="+dao.find(q, Test.class,0,90));
	}

	@Override
	public Object getByUuid(Class clazz, Object uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveBatch(List objects) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBatch(List objects) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List find(String query, Class clazz, Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(QueryBuilder queryBuilder, Class clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(QueryBuilder queryBuilder, Class clazz,
			Integer currentPage, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCount(QueryBuilder queryBuilder, Class clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCount(String countQuery, Class clazz, Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getAll(Class clazz) {
		Connection conn = null;
		List results = new ArrayList();
		try{
			conn = ConnectionUtil.getConnection();
			String s =SQLBuilder.builderSelectAll(clazz);
			PreparedStatement pstmt = conn.prepareStatement(s);
			ResultSet result = pstmt.executeQuery();
			RowMapper rowMapper = new RowMapperImpl();
			while(result.next()){
				results.add(rowMapper.mapper(result, clazz));
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
		  ConnectionUtil.releaseConnection(conn);
		}
		return results;
	}

}
