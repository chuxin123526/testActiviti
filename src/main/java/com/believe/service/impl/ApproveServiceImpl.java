package com.believe.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.believe.mapper.ApproveInfoMapper;
import com.believe.po.ApproveInfo;
import com.believe.service.IApproveInfoService;

@Service
@Transactional
public class ApproveServiceImpl implements IApproveInfoService
{
	@Resource
	private ApproveInfoMapper approveInfoMapper ;

	public void add(ApproveInfo approveInfo) throws Exception
	{
		this.approveInfoMapper.add(approveInfo);
	}

}
