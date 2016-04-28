package com.believe.test;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHistory
{
	@Test
	public void test() throws Exception
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		RuntimeService runtimeService = (RuntimeService)applicationContext.getBean("runtimeService") ; 
		TaskService taskService = (TaskService)applicationContext.getBean("taskService") ;
		runtimeService.startProcessInstanceByKey("myProcess") ; 
		
	}
}
