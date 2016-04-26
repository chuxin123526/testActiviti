package com.believe.action;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.believe.mapper.UserMapper;
import com.believe.po.User;

public class TestMybatis_Spring
{
	@Test
	public void testMybatisSpring()
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		SqlSessionFactory sqlSessionFactory = 
				(SqlSessionFactory)applicationContext.getBean("sqlSessionFactory") ; 
		SqlSession sqlSession = sqlSessionFactory.openSession() ; 
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class) ;
	}
	
	@Test
	public void testMapper()
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		UserMapper userMapper =(UserMapper) applicationContext.getBean("userMapper") ; 
	
	}
}
