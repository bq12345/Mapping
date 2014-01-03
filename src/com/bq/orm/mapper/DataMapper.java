package com.bq.orm.mapper;

import java.sql.PreparedStatement;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.bq.orm.config.ORMConfiguration;



public class DataMapper {
	
	public static void setValue(PreparedStatement pstmt,Object... args) throws Exception{
		if(null == args || args.length==0){
			return;
		}
		int i=1;
		for(Object o:args){
			System.out.println(i+"="+args[i-1]);
			pstmt.setObject(i, (Object)args[i-1]);
			i++;
		}
	}
	
	
	
	public static void setInsertValue(PreparedStatement pstmt,Object o) throws Exception{
		ORMConfiguration cf=ORMConfiguration.configure();
		ModelMapper mm=cf.getModelMapper(o.getClass().getName());
		setIdValue(pstmt, BeanUtils.getProperty(o, mm.getId().getFieldName()),1);
		setColumnValue(pstmt, o,2);
	}
	
	private static int  setColumnValue(PreparedStatement pstmt,Object o,int index) throws Exception{
		ORMConfiguration cf=ORMConfiguration.configure();
		ModelMapper mm=cf.getModelMapper(o.getClass().getName());
		List<ColumnMapper> columns = mm.getColumnMaps();
		int i=index;
		for(ColumnMapper cm :columns){
			pstmt.setObject(i++,BeanUtils.getProperty(o, cm.getFieldName()));
		}
		return i;
	}
	
	public static void setIdValue(PreparedStatement pstmt,Object uuid,int idIndex) throws Exception{
		
		System.out.println("uuid is:"+uuid);
		
		pstmt.setObject(idIndex,uuid);
		
	}
	
	public static void setDeleteValue(PreparedStatement pstmt,Object o) throws Exception{
		ORMConfiguration cf=ORMConfiguration.configure();
		ModelMapper mm=cf.getModelMapper(o.getClass().getName());
		setIdValue(pstmt, BeanUtils.getProperty(o, mm.getId().getFieldName()),1);
		
	}
	
	public static void setUpdateValue(PreparedStatement pstmt,Object o) throws Exception{
		int idIndex = setColumnValue(pstmt, o,1);
		ORMConfiguration cf=ORMConfiguration.configure();
		ModelMapper mm=cf.getModelMapper(o.getClass().getName());
		System.out.println(mm.getId().getFieldName());
		setIdValue(pstmt, BeanUtils.getProperty(o, mm.getId().getFieldName()),idIndex);
		
	}
}
