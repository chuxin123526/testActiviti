package com.believe.mapper;

import java.util.List;

import com.believe.po.Application;

public interface ApplicationMapper 
{
	public void add(Application application) throws Exception ; 
	public List<Application> list(long userId) throws Exception ; 
	public Application getById(long id) throws Exception  ; 
}
