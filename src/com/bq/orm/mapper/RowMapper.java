package com.bq.orm.mapper;

import java.sql.ResultSet;

public interface RowMapper {
	public Object mapper(ResultSet result,Class clazz)  throws Exception ;
}
