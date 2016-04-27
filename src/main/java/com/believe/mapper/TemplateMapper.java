package com.believe.mapper;

import java.util.List;

import com.believe.po.Template;

public interface TemplateMapper
{
	public void add(Template template) throws Exception ; 
	public List<Template> list() throws Exception ;
	public void delete(int id) throws Exception ; 
	public Template getById(int id) throws Exception ; 
	public void update(Template template) throws Exception ; 
	public Template getByDeploymentId(String deploymentId) throws Exception ; 
}
