package com.bq.orm.mapper;

import java.sql.ResultSet;

import org.apache.commons.beanutils.BeanUtils;

import com.bq.orm.config.ORMConfiguration;

public class RowMapperImpl implements RowMapper {

	@Override
	public Object mapper(ResultSet result, Class clazz) throws Exception {
		ORMConfiguration cf=ORMConfiguration.configure();
		ModelMapper mm=cf.getModelMapper(clazz.getName());
		Object o = Class.forName(mm.getClassName()).newInstance();
		BeanUtils.setProperty(o, mm.getId().getFieldName(), result.getObject(mm.getId().getColumnName()));
		for(ColumnMapper cm:mm.getColumnMaps()){
			BeanUtils.setProperty(o, cm.getFieldName(), result.getObject(cm.getColumnName()));
		}
		return o;
	}


}
