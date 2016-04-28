package com.believe.test;

import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.believe.mapper.TemplateMapper;
import com.believe.po.Template;

public class TestTemplateMapper
{
	@Test
	public void testAdd() throws Exception
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		TemplateMapper templateMapper = 
				(TemplateMapper)applicationContext.getBean("templateMapper") ; 
		Template template = new Template() ; 
		template.setName("test");
		template.setPath("testPath");
		templateMapper.add(template);
	}
	
	@Test
	public void testList() throws Exception 
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		TemplateMapper templateMapper = 
				(TemplateMapper)applicationContext.getBean("templateMapper") ; 
		List<Template> list = templateMapper.list() ; 
		System.out.println(list.size());
	}
	
	@Test
	public void testDownload() throws Exception
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		TemplateMapper templateMapper = 
				(TemplateMapper)applicationContext.getBean("templateMapper") ; 
		Template template = templateMapper.getById(4) ; 
		System.out.println(template.getName());
		System.out.println(template.getPath());
	}
	
	@Test
	public void update() throws Exception
	{

		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		TemplateMapper templateMapper = 
				(TemplateMapper)applicationContext.getBean("templateMapper") ; 
		Template template = new Template() ; 
		template.setId(4);
		template.setName("test");
		template.setPath("testPath");
		templateMapper.update(template);
	}
}
