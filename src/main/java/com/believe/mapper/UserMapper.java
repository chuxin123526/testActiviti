package com.believe.mapper;

import java.util.List;

import com.believe.po.User;

public interface UserMapper
{
	public User getById(int id) throws Exception ;
	public User getByName(String name) throws Exception ; 
}
