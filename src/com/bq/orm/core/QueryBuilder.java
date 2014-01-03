package com.bq.orm.core;

import java.util.Collection;
import java.util.List;

public interface QueryBuilder<T> extends java.io.Serializable {
	/**  
     * �ж��Ƿ����  
     * @param propertyName  
     * @param value  
     */  
    public abstract QueryBuilder<T> eq(String propertyName, Object value);   
  
    /**  
     * �ж��Ƿ�ƥ��  
     * @param propertyName  
     * @param value  
     */  
    public abstract QueryBuilder<T> like(String propertyName, Object value);   
  
  
    public abstract QueryBuilder<T> gt(String propertyName, Object value);   
  
    /**  
     * �ж��Ƿ�С��  
     * @param propertyName  
     * @param value  
     */  
    public abstract QueryBuilder<T> lt(String propertyName, Object value);   
  
    /**  
     * �ж��Ƿ�С�ڵ���  
     * @param propertyName  
     * @param value  
     */  
    public abstract QueryBuilder<T> le(String propertyName, Object value);   
  
    /**  
     * �ж��Ƿ���ڵ���  
     * @param propertyName  
     * @param value  
     */  
    public abstract QueryBuilder<T> ge(String propertyName, Object value);   
  
    /**  
     * ������.  
     * @param propertyName  
     * @param value  
     */  
    public QueryBuilder<T> notEq(String propertyName, Object value);   
  
    /**  
     * �ж��Ƿ��ڸ�����������  
     * @param propertyName  
     * @param lo  
     * @param hi  
     */  
    public abstract QueryBuilder<T> between(String propertyName, Object lo,   
            Object hi);   
  
    /**  
     * �ж��Ƿ��ڸ�����������  
     * @param propertyName  
     * @param values  
     */  
    public abstract QueryBuilder<T> in(String propertyName, Object[] values);   
  
    /**  
     * �ж��Ƿ��ڸ����ļ�����  
     * @param propertyName  
     * @param values  
     */  
    public abstract QueryBuilder<T> in(String propertyName, Collection<T> values);   
  
    /**  
     * �ж��Ƿ�Ϊ��  
     * @param propertyName  
     */  
    public abstract QueryBuilder<T> isNull(String propertyName);   
    
    public String getQuery();
    public List getQueryValues();

}
