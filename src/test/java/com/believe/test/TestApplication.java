package com.believe.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.believe.mapper.ApplicationMapper;
import com.believe.po.Application;

public class TestApplication
{
	@Test
	public void test() throws Exception
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		ApplicationMapper applicationMapper = (ApplicationMapper)
				applicationContext.getBean("applicationMapper") ; 
		List<Application> list = applicationMapper.list(9) ; 
		for(Application application : list)
		{
			System.out.println(application.getId());
			System.out.println(application.getUser().getName());
		}
	}
}
