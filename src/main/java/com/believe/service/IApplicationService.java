package com.believe.service;

import java.util.List;

import com.believe.po.Application;

public interface IApplicationService
{
	public void add(Application application) throws Exception ; 
	public List<Application> list(long userId) throws Exception ; 
}
