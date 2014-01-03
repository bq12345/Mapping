package com.bq.orm.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimpleQueryBuilder implements QueryBuilder{
	private StringBuilder query = new StringBuilder(" where 1=1 ");
	private List<Object> queryValues = new ArrayList<Object>();   
	@Override
	public QueryBuilder between(String propertyName, Object lo, Object hi) {
		query.append(" and ");
		query.append(propertyName+" >=? and "+propertyName+" <=?");
		queryValues.add(lo);
		queryValues.add(hi);
		return this;
		
	}

	@Override
	public QueryBuilder eq(String propertyName, Object value) {
		query.append(" and ");
		query.append(propertyName+" =? ");
		queryValues.add(value);
		return this;
		
	}

	@Override
	public QueryBuilder ge(String propertyName, Object value) {
		query.append(" and ");
		query.append(propertyName+" >=? ");
		queryValues.add(value);
		return this;
	}

	@Override
	public QueryBuilder gt(String propertyName, Object value) {
		query.append(" and ");
		query.append(propertyName+" >? ");
		queryValues.add(value);
		return this;
	}

	@Override
	public QueryBuilder in(String propertyName, Object[] values) {
		query.append(" and ");
		query.append(propertyName+" in ");
		buildMark(query,values.length);
		for(Object o:values){
			queryValues.add(o);	
		}
		return this;
	}
	
	private  void buildMark(StringBuilder sql,int length){
		if(length==0){
			return;
		}
		sql.append("?");	
		for(int i=1;i<=length;i++){
			sql.append(",?");
		}
	}		

	@Override
	public QueryBuilder in(String propertyName, Collection values) {
		query.append(" and ");
		query.append(propertyName+" in ");
		buildMark(query,values.size());
		queryValues.addAll(values);	
		return this;
	}

	@Override
	public QueryBuilder isNull(String propertyName) {
		query.append(" and ");
		query.append(propertyName+" is null ");
		return this;
	}

	@Override
	public QueryBuilder le(String propertyName, Object value) {
		query.append(" and ");
		query.append(propertyName+" <=? ");
		queryValues.add(value);
		return this;
	}

	@Override
	public QueryBuilder like(String propertyName, Object value) {
		query.append(" and ");
		query.append(propertyName+" like ? ");
		queryValues.add(value);
		return this;
	}

	
	@Override
	public QueryBuilder lt(String propertyName, Object value) {
		query.append(" and ");
		query.append(propertyName+" <? ");
		queryValues.add(value);
		return this;
	}

	@Override
	public QueryBuilder notEq(String propertyName, Object value) {
		query.append(" and ");
		query.append(propertyName+"!=? ");
		queryValues.add(value);
		return this;
	}
	public String getQuery() {
		return query.toString();
	}
	public List getQueryValues() {
		return queryValues;
	}

}
