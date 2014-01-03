package com.bq.orm.mapper;

import java.util.ArrayList;
import java.util.List;

public class ModelMapper {
	private String className;
	private String tableName;
	private ColumnMapper id = new ColumnMapper(); 
	private List<ColumnMapper> columnMaps = new ArrayList<ColumnMapper>();
	
	private static ModelMapper instance = new ModelMapper();
	public ModelMapper(){
	//do something
	}
	//这里提供了一个供外部访问本class的静态方法，可以直接访问
	public static ModelMapper getInstance(){
	return instance;
	}
		
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<ColumnMapper> getColumnMaps() {
		return columnMaps;
	}
	public void setColumnMaps(List<ColumnMapper> columnMaps) {
		this.columnMaps = columnMaps;
	}
	
	public void addColumn(String fieldName,String columnName){
		columnMaps.add(new ColumnMapper(fieldName,columnName));
	}
	public ColumnMapper getId() {
		return id;
	}
	public void setId(String fieldName,String columnName) {
		id.setFieldName(fieldName);
		id.setColumnName(columnName);
	}
	
	
}
