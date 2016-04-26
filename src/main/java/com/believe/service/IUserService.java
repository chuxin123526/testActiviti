package com.believe.service;

import com.believe.po.User;

public interface IUserService
{
	public User getById(int id) throws Exception ; 
	public User getByName(String name) throws Exception ;
}
