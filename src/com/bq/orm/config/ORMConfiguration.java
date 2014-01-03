package com.bq.orm.config;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.bq.model.Student;
import com.bq.orm.core.BaseDAOImpl;
import com.bq.orm.core.IDAO;
import com.bq.orm.mapper.ModelMapper;



public class ORMConfiguration {
	private static ORMConfiguration cfg = null;
	private static Map<String,ModelMapper> modelMapper = new HashMap<String,ModelMapper>();
	
	public static ORMConfiguration configure(String root){
		if(cfg==null){
			synchronized (ORMConfiguration.class) {
				cfg = new ORMConfiguration();
				cfg.builder(root);
			}
		}
		return cfg;
	}
	
	public static ORMConfiguration configure(){
		return configure(null);
	}
	
	private void builder(String rootStr){
		if(rootStr==null){
			rootStr = getClass().getClassLoader().getResource(".").getPath().substring(1);
			
		}
		File root = new File(rootStr);
		parse(root,rootStr.length());
	}
	
	private void parse(File root,int length){
		if(root.isDirectory()){
			for(File file:root.listFiles()){
				parse(file,length);
			}
		}else{
			if(root.getName().endsWith(".xml")){
				
				String clazzName = root.getAbsolutePath();
				clazzName = clazzName.substring(length,clazzName.length()-4).replace(File.separatorChar, '.');
				Object clazz;
				try {
					System.out.println(clazzName);
					clazz = Class.forName(clazzName).newInstance();
					parse(clazz);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	public ModelMapper getModelMapper(String clazzName){
		return modelMapper.get(clazzName);
	}
public static IDAO getDao(){
		return new BaseDAOImpl();
	}
	public static void parse(Object o) {
		ModelMapper mm = new ModelMapper();
		mm.setClassName(o.getClass().getName());
		String s = o.getClass().getName().replaceAll("\\.", "/");
		System.out.println("/" + s + ".xml");
		InputStream is = o.getClass().getResourceAsStream("/" + s + ".xml");
		try {
			Document documnet = getDocument(is);
			Element root = getRoot(documnet);
			// System.out.println(root.getText());
			List<Element> mappings = getElementsByName(root, "class");
			Attribute tableName = getAttributeByName(mappings.get(0), "table");
			mm.setClassName(o.getClass().getName());
			mm.setTableName(tableName.getText());
			mm.setId("id", "id");
			System.out.println(tableName.getText());
			if (mappings != null && mappings.size() > 0) {
				for (Element mapping : mappings) {
					List<Element> rows = getElements(mapping);
					for (Element elem : rows) {
						
						Attribute att = getAttributeByName(elem, "name");
						
						if(att.getText().equalsIgnoreCase("id")){
							System.out.println("if ....."+att.getText());
							mm.setId(att.getText(), att.getText());
							continue;
						}
							
						System.out.println("name is:" + att.getText());
						Attribute col = getAttributeByName(elem, "column");
						System.out.println("column is:" + col.getText());
						mm.addColumn(col.getText(), col.getText());
						
					}
				}
				modelMapper.put(o.getClass().getName(),mm);
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}

	public static Document getDocument(File file) throws DocumentException {
		SAXReader xmlReader = new SAXReader();
		return xmlReader.read(file);
	}

	public static Document getDocument(InputStream is) throws DocumentException {
		SAXReader xmlReader = new SAXReader();
		return xmlReader.read(is);
	}

	public static Element getRoot(Document doc) {
		return doc.getRootElement();
	}

	public static List<Element> getElements(Element root) {
		return root.elements();
	}

	public static List<Element> getElementsByName(Element root, String name) {
		return root.elements(name);
	}

	public static Element getElementByName(Element root, String name) {
		return root.element(name);
	}

	public static List<Attribute> getAttributes(Element root) {
		return root.attributes();
	}

	public static Attribute getAttributeByName(Element root, String name) {
		return root.attribute(name);
	}
	
	public static void main(String[] args) throws Exception{
	
		ORMConfiguration cf=ORMConfiguration.configure();
		Student t=new Student();
		ModelMapper m=cf.getModelMapper(t.getClass().getName());
		System.out.println(m.getTableName());
		
	}
	
}
