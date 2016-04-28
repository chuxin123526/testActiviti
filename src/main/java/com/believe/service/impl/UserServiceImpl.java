package com.believe.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.believe.mapper.UserMapper;
import com.believe.po.User;
import com.believe.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService
{
	@Resource
	private UserMapper userMapper ; 
	
	public User getById(int id) throws Exception
	{
		return this.userMapper.getById(id) ; 
	}

	public User getByName(String name) throws Exception
	{
		return this.userMapper.getByName(name) ; 
	}
	
}
