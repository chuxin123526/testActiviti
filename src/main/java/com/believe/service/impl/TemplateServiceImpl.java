package com.believe.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.believe.mapper.TemplateMapper;
import com.believe.po.Template;
import com.believe.service.ItemplateService;

@Service("templateServiceImpl")
@Transactional
public class TemplateServiceImpl implements ItemplateService
{
	@Resource
	private TemplateMapper templateMapper ;

	public void add(Template template) throws Exception
	{
		this.templateMapper.add(template); 
	}

	public List<Template> list() throws Exception
	{
		List<Template> list = this.templateMapper.list() ; 
		return list ; 
	}

	public void delete(int id) throws Exception
	{
		this.templateMapper.delete(id);
	}

	public Template getById(int id) throws Exception
	{
		return this.templateMapper.getById(id) ; 
	}

	public void update(Template template) throws Exception
	{
		this.templateMapper.update(template);
	}

	public Template getByDeploymentId(String deploymentId) throws Exception
	{
		return this.templateMapper.getByDeploymentId(deploymentId) ; 
	} 

}
