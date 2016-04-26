package com.believe.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.believe.mapper.ApplicationMapper;
import com.believe.po.Application;
import com.believe.service.IApplicationService;

@Service
@Transactional
public class ApplicationServiceImpl implements IApplicationService
{
	@Resource
	private ApplicationMapper applicationMapper ; 
	
	public void add(Application application) throws Exception
	{
		this.applicationMapper.add(application);
		
	}

	public List<Application> list(long userId) throws Exception
	{
		return this.applicationMapper.list(userId) ;
	}
	
	
	

}
