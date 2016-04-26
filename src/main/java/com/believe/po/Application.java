package com.believe.po;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Application implements Serializable
{
	public static final String STATUS_RUNNING = "审批中" ; 
	public static final String STATUS_COMPLETE = "已通过" ; 
	public static final String STATUS_REJECTED = "未通过" ; 
	
	private long id ; 
	private long userId ; 
	private String title ; //标题
	private String path ; //文档的存储路径
	private String status ; //当前状态 
	private long templateId ; 
	private String processInstanceId ; //流程实例id
	private User user ; 
	
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	public String getProcessInstanceId()
	{
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId)
	{
		this.processInstanceId = processInstanceId;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getUserId()
	{
		return userId;
	}
	public void setUserId(long userId)
	{
		this.userId = userId;
	}
	public long getTemplateId()
	{
		return templateId;
	}
	public void setTemplateId(long templateId)
	{
		this.templateId = templateId;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getPath()
	{
		return path;
	}
	public void setPath(String path)
	{
		this.path = path;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	
	
}
