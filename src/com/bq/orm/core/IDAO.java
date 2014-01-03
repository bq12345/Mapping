package com.bq.orm.core;

import java.io.Serializable;
import java.util.List;

public interface IDAO<T> {

	/**
	 * 查询clazz类对应的所有记录
	 * 
	 * @param clazz
	 *            要返回的实体类类型
	 * @return clazz类对应的所有记录
	 */
	public List<T> getAll(Class<T> clazz);

	/**
	 * 
	 * @param clazz
	 *            要返回的实体类类型
	 * @param uuid
	 *            主键
	 * @return
	 */
	public T getByUuid(Class<T> clazz, Object uuid);

	public boolean save(T t);

	public boolean saveBatch(List<T> objects);

	public boolean delete(T t);

	public boolean deleteBatch(List objects);

	public boolean update(T t);

	public boolean updateBatch(List<T> objects);

	public List<T> find(String query, Class clazz, Object... args);

	public List<T> find(QueryBuilder<T> queryBuilder, Class clazz);

	@SuppressWarnings("unchecked")
	public List<T> find(QueryBuilder queryBuilder, Class clazz,
			Integer currentPage, Integer pageSize);

	public Integer getCount(QueryBuilder queryBuilder, Class clazz);

	public Integer getCount(String countQuery, Class clazz, Object... args);

	public Integer getCount(Class clazz);


}
