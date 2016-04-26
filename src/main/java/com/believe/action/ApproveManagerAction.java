package com.believe.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.believe.po.Application;
import com.believe.po.ApproveInfo;
import com.believe.po.User;
import com.believe.service.IApproveInfoService;

@Controller
@RequestMapping(value = "/approveManagerAction")
public class ApproveManagerAction
{
	@Resource 
	private RuntimeService runtimeService ; 
	@Resource
	private TaskService taskService ; 
	@Resource
	private IApproveInfoService approveInfoServiceImpl ; 
	
	@RequestMapping("/waitForApprove")
	public String waitForApprove(Model model,HttpServletRequest httpServletRequest) throws Exception
	{
		User user =(User) httpServletRequest.getSession().getAttribute("user") ; 
		if(user == null)
		{
			System.out.println("user is null");
		}
		else
		{
			List<Task> taskList = this.taskService.createTaskQuery().taskAssignee(user.getName()).list() ; 
			System.out.println("userTaskListSize" + taskList.size());
			model.addAttribute("taskList", taskList) ; 
		}
		
		return "/approveManager/waitForApprove" ; 
	}
	
	@RequestMapping("approveUI")
	public String approveUI(@RequestBody String json , Model model) throws Exception
	{
		JSONObject jsonObject = new JSONObject(json) ; 
		int taskId = jsonObject.getInt("taskId") ;
		model.addAttribute("taskId" , taskId) ; 
		
		return "/approveManager/approveUI" ; 
	}
	
	@RequestMapping(value = "approve")
	@ResponseBody
	public String approve(@RequestParam("optionsRadios") String radioValue,
			HttpServletRequest httpServletRequest , 
			@RequestParam("textarea") String comment , 
			@RequestParam("taskId") String taskId
			) throws Exception
	{
		
		ApproveInfo approveInfo = new ApproveInfo() ; 
		User user = (User)httpServletRequest.getSession().getAttribute("user") ; 
		if(user == null)
		{
			System.out.println("user is null");
			throw new RuntimeException() ; 
		}
		else
		{
			approveInfo.setUserId(user.getId());
			long applicationId = ((Application)this.taskService.getVariable(taskId + "" , "application")).getId() ; 
			approveInfo.setApplicationId(applicationId);
			approveInfo.setComment(comment);
			if(radioValue.equals("true"))
			{
				approveInfo.setPass(true);
			}
			else
			{
				approveInfo.setPass(false);
			}
			//保存审批信息
			this.approveInfoServiceImpl.add(approveInfo);
			//完成任务
			this.taskService.complete(taskId + "");
			
		}
		JSONObject jsonObject = new JSONObject() ; 
		jsonObject.put("info", "success") ;  
		return jsonObject.toString() ; 
	}
	
}
