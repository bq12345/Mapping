package com.bq.test;

import java.util.List;

import com.bq.model.Person;
import com.bq.model.Student;
import com.bq.orm.config.ORMConfiguration;
import com.bq.orm.core.BaseDAOImpl;

public class Index {
	
	public static void main(String[] args){
		
		ORMConfiguration cf=ORMConfiguration.configure();
		Index index=new Index();
		Student t=new Student();
		Person p=new Person();
		t.setId(8);
		t.setAddress("wangyuan");
		t.setName("cbw");
		p.setId(4);
		p.setName("°×Ç¿");
		p.setOther("A student");
		index.testSave(t);
		//index.testQuery(t);
		//index.testDelete(t);
		//index.testUpadate(t);
	}
	public void testSave(Object o){
		BaseDAOImpl bd=new BaseDAOImpl();
		bd.save(o);
	}
	public void testQuery(Object o){
		
		BaseDAOImpl bd=new BaseDAOImpl();
		
		List list=bd.getAll(o.getClass());
		for(int i=0;i<list.size();i++){
			Student t=(Student) list.get(i);
			System.out.println(t.getId());
			//System.out.println(list.get(i));
		}
	}
	public void testDelete(Object o){
		BaseDAOImpl bd=new BaseDAOImpl();
		List list=bd.getAll(o.getClass());
		Student t=(Student) list.get(0);
		
		System.out.println(t.getId());
		System.out.println(bd.delete(t));
	}
	public void testUpadate(Object o){
		BaseDAOImpl bd=new BaseDAOImpl();
		List list=bd.getAll(o.getClass());
		Student t=(Student) list.get(0);
		System.out.println(t.getAddress());
		t.setAddress("China");
		t.setName("never");
		System.out.println(t.getId());
		System.out.println(bd.update(t));
	}
	
}
