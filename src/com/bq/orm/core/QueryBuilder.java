package com.bq.orm.core;

import java.util.Collection;
import java.util.List;

public interface QueryBuilder<T> extends java.io.Serializable {
	/**  
     * 判断是否相等  
     * @param propertyName  
     * @param value  
     */  
    public abstract QueryBuilder<T> eq(String propertyName, Object value);   
  
    /**  
     * 判断是否匹配  
     * @param propertyName  
     * @param value  
     */  
    public abstract QueryBuilder<T> like(String propertyName, Object value);   
  
  
    public abstract QueryBuilder<T> gt(String propertyName, Object value);   
  
    /**  
     * 判断是否小于  
     * @param propertyName  
     * @param value  
     */  
    public abstract QueryBuilder<T> lt(String propertyName, Object value);   
  
    /**  
     * 判断是否小于等于  
     * @param propertyName  
     * @param value  
     */  
    public abstract QueryBuilder<T> le(String propertyName, Object value);   
  
    /**  
     * 判断是否大于等于  
     * @param propertyName  
     * @param value  
     */  
    public abstract QueryBuilder<T> ge(String propertyName, Object value);   
  
    /**  
     * 不等于.  
     * @param propertyName  
     * @param value  
     */  
    public QueryBuilder<T> notEq(String propertyName, Object value);   
  
    /**  
     * 判断是否在给定的区间里  
     * @param propertyName  
     * @param lo  
     * @param hi  
     */  
    public abstract QueryBuilder<T> between(String propertyName, Object lo,   
            Object hi);   
  
    /**  
     * 判断是否在给定的数组里  
     * @param propertyName  
     * @param values  
     */  
    public abstract QueryBuilder<T> in(String propertyName, Object[] values);   
  
    /**  
     * 判断是否在给定的集合里  
     * @param propertyName  
     * @param values  
     */  
    public abstract QueryBuilder<T> in(String propertyName, Collection<T> values);   
  
    /**  
     * 判断是否为空  
     * @param propertyName  
     */  
    public abstract QueryBuilder<T> isNull(String propertyName);   
    
    public String getQuery();
    public List getQueryValues();

}
