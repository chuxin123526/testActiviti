package com.believe.test;

import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestTaskService
{
	@Test
	public void test() throws Exception 
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		TaskService taskService =(TaskService) applicationContext.getBean("taskService") ; 
		List<Task> taskList = taskService.createTaskQuery().taskAssignee("manager").list() ; 
		for(Task task : taskList)
		{
			taskService.complete(task.getId());
		}
		
	}
}
