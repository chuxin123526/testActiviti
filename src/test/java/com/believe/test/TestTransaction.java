package com.believe.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.believe.po.Template;
import com.believe.service.ItemplateService;

public class TestTransaction
{
	@Test
	public void test() throws Exception
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		ItemplateService itemplateService =
				(ItemplateService)applicationContext.getBean("templateServiceImpl") ; 
		Template template = new Template() ;
		template.setName("testTransaction");
		itemplateService.add(template);
	}
}
