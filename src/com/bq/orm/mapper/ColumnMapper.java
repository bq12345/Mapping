package com.bq.orm.mapper;

public class ColumnMapper {
	private String fieldName;
	private String columnName;
	public ColumnMapper() {
	
	}
	public ColumnMapper(String fieldName,String columnName) {
		this.fieldName = fieldName;
		this.columnName = columnName;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
}
