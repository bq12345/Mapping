package com.bq.orm.core;

import com.bq.orm.config.ORMConfiguration;
import com.bq.orm.mapper.ModelMapper;


public class SQLBuilder {
	
	public static String builderSave(Object o){
		ORMConfiguration cf=ORMConfiguration.configure();
		ModelMapper mm=cf.getModelMapper(o.getClass().getName());
		StringBuilder sql = new StringBuilder("insert into ");
		System.out.println(mm);
		sql.append(mm.getTableName());
		sql.append("(");
		buildColumn(sql,mm);
		sql.append(")");
		sql.append(" values (");
		buildMark(sql,mm.getColumnMaps().size());
		sql.append(")");
		System.out.println(sql.toString());
		return sql.toString();
		
	}
	
	
	public static String builderUpdate(Object o){
		ORMConfiguration cf=ORMConfiguration.configure();
		ModelMapper mm=cf.getModelMapper(o.getClass().getName());
		StringBuilder sql = new StringBuilder("update  ");
		String clazzName = o.getClass().getName();
		sql.append(mm.getTableName());
		sql.append(" set ");
		buildUpdateColumn(sql, mm);
		buildIndexWhere(sql, mm);
		System.out.println(sql.toString());
		return sql.toString();
		
	}
	
	public static String builderSelectCount(Class clazz){
		StringBuilder sql = new StringBuilder("select count(*) from  ");
		String clazzName = clazz.getName();
		ModelMapper mm = ModelMapper.getInstance();
		sql.append(mm.getTableName());
		return sql.toString();
		
	}
	
	public static StringBuilder builderSelect(Class clazz){
		ModelMapper mm = ModelMapper.getInstance();
		StringBuilder sql = new StringBuilder("select *  from  ");
		sql.append(mm.getTableName());
		sql.append("  ");
		return sql;
	}
	
	public static String builderGetByUuid(Class clazz){
		ModelMapper mm = ModelMapper.getInstance();
		StringBuilder sql = builderSelect(clazz);
		buildIndexWhere(sql, mm);
		return sql.toString();
	}
	
	
	public static String builderSelectAll(Class clazz){
		ORMConfiguration cf=ORMConfiguration.configure();
		ModelMapper mm=cf.getModelMapper(clazz.getName());
		StringBuilder sql = new StringBuilder("select * from  ");
		sql.append(mm.getTableName());
		return sql.toString();
		
	}
	
	
	public static String builderDelete(Object o){
		ORMConfiguration cf=ORMConfiguration.configure();
		ModelMapper mm=cf.getModelMapper(o.getClass().getName());
		StringBuilder sql = new StringBuilder("delete from ");
		sql.append(mm.getTableName());
		buildIndexWhere(sql, mm);
		System.out.println(sql.toString());
		return sql.toString();
	}
	
	private static void buildIndexWhere(StringBuilder sql,ModelMapper mm){
		sql.append(" where ");
		sql.append(mm.getId().getColumnName());
		sql.append("=?");
	}
	
	private static void buildColumn(StringBuilder sql,ModelMapper mm){
		
		if(mm.getColumnMaps().size()==0){
			return;
		}
			sql.append(mm.getId().getColumnName());
		for(int i=1;i<=mm.getColumnMaps().size();i++){
			sql.append(","+mm.getColumnMaps().get(i-1).getColumnName());
		}
		
	}
	
	private static void buildUpdateColumn(StringBuilder sql,ModelMapper mm){
		if(mm.getColumnMaps().size()==0){
			return;
		}
		for(int i=1;i<mm.getColumnMaps().size();i++){
			sql.append(mm.getColumnMaps().get(i-1).getColumnName());
			sql.append("=?, ");
		}
		sql.append(mm.getColumnMaps().get(mm.getColumnMaps().size()-1).getColumnName());
		sql.append("=? ");
		
	}
	
	
	private static void buildMark(StringBuilder sql,int length){
		if(length==0){
			return;
		}
		sql.append("?");	
		for(int i=1;i<=length;i++){
			sql.append(",?");
		}
		
		
	}
}
