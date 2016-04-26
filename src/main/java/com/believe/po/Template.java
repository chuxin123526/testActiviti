package com.believe.po;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Template
{
	private long id ; 
	private String name ; 
	private String deploymentId ; //流程定义key
	private String path ; 
	private Date updateDate ; 
	private String size ; 
	
	public String getSize()
	{
		return size;
	}
	public void setSize(String size)
	{
		this.size = size;
	}
	public Date getUpdateDate()
	{
		return updateDate;
	}
	public void setUpdateDate(Date updateDate)
	{
		this.updateDate = updateDate;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getDeploymentId()
	{
		return deploymentId;
	}
	public void setDeploymentId(String deploymentId)
	{
		this.deploymentId = deploymentId;
	}
	public String getPath()
	{
		return path;
	}
	public void setPath(String path)
	{
		this.path = path;
	}

	
	
}
