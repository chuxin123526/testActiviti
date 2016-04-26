package com.believe.po;

import java.sql.Date;

public class ApproveInfo
{
	private long id ; 
	private long userId ; 
	private long applicationId ; 
	private Date approveDate ; //审批日期
	private boolean isPass ; //是否通过
	private String comment ; //评论
	
	public long getApplicationId()
	{
		return applicationId;
	}
	public void setApplicationId(long applicationId)
	{
		this.applicationId = applicationId;
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
	public Date getApproveDate()
	{
		return approveDate;
	}
	public void setApproveDate(Date approveDate)
	{
		this.approveDate = approveDate;
	}
	public boolean isPass()
	{
		return isPass;
	}
	public void setPass(boolean isPass)
	{
		this.isPass = isPass;
	}
	public String getComment()
	{
		return comment;
	}
	public void setComment(String comment)
	{
		this.comment = comment;
	}
	
	
}
