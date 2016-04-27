package com.believe.action;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.believe.po.Application;
import com.believe.po.User;
import com.believe.service.IApplicationService;
import com.believe.util.FileUtil;

@Controller
@RequestMapping("/applyManagerAction")
public class ApplyManagerAction
{
	@Resource
	private RepositoryService repositoryService  ; 
	@Resource
	private RuntimeService runtimeService ; 
	@Resource
	private IApplicationService applicationServiceImpl ; 
	@Resource
	private ProcessEngineConfiguration processEngineConfiguration ; 
	
	@RequestMapping(value = "/list")
	public String list(Model model) throws Exception
	{
		//查询所有流程部署
		List<Deployment> deploymentList = this.repositoryService.createDeploymentQuery().list() ;
		model.addAttribute("deploymentList", deploymentList) ; 
		return "/applyManager/list" ;
	}
	
	@RequestMapping(value = "selectProcess")
	public String selectProcess(Model model ,@RequestBody String json) throws Exception
	{
		System.out.println(json);
		JSONObject jsonObject = new JSONObject(json) ; 
		int deploymentId = jsonObject.getInt("deploymentId") ; 
		model.addAttribute("deploymentId" , deploymentId) ; 
		return "/applyManager/bootProcessUI" ; 
	}
	
	@RequestMapping(value = "/bootProcessUI")
	public String bootProcessUI(Model model , @RequestParam("deploymentId") String deploymentId) throws Exception
	{
		model.addAttribute("deploymentId", deploymentId) ; 
		
		return "/applyManager/bootProcessUI" ; 
	}
	
	@RequestMapping(value = "/bootProcess")
	@ResponseBody
	public String bootProcess(@RequestParam("file") MultipartFile file , 
			HttpServletRequest httpServletRequest , 
			@RequestParam("deploymentId") String deploymentId 
			) throws Exception
	{
		String path = FileUtil.save(file) ; 
		
		//启动流程
		ProcessDefinition definition = 
				this.repositoryService.createProcessDefinitionQuery()
				.deploymentId(deploymentId).singleResult() ; 
		String processDefinitionId = definition.getId() ; 
		System.out.println(processDefinitionId);
		ProcessInstance processInstance = this.runtimeService.startProcessInstanceById(processDefinitionId) ; 
		
		System.out.println("启动流程实例");
		//保存申请信息
		Application application = new Application() ; 
		application.setPath(path);
		application.setTemplateId(14);
		application.setStatus(Application.STATUS_RUNNING);
		User user = (User)httpServletRequest.getSession().getAttribute("user") ; 
		if(user != null)
		{
			application.setUserId(user.getId());
		}
		application.setProcessInstanceId(processInstance.getId()) ; 
		this.applicationServiceImpl.add(application);
		//设置流程变量
		this.runtimeService.setVariableLocal(processInstance.getId(), "application", application);
		
		JSONObject jsonObject = new JSONObject() ;
		jsonObject.put("info", "success") ; 
		return jsonObject.toString() ; 
	}
	
	@RequestMapping(value = "/myApplicationList")
	public String myApplicationList(HttpServletRequest httpServletRequest , Model model) throws Exception
	{
		User user = (User)httpServletRequest.getSession().getAttribute("user") ; 
		List<Application> myApplicationList = null ; 
		if(user != null)
		{
			long userId = user.getId() ;
			myApplicationList = this.applicationServiceImpl.list(userId) ; 
		}
		model.addAttribute("myApplicationList" , myApplicationList) ;
		
		return "/applyManager/myApplicationList" ; 
		
	}
	
	@RequestMapping(value = "/seeProcessDiagram")
	public void seeProcessDiagram(@RequestParam("applicationId") String applicaitonId , OutputStream outputStream) throws Exception
	{
		Application application = this.applicationServiceImpl.getById(Long.parseLong(applicaitonId)) ; 
		String processInstanceId = application.getProcessInstanceId() ; 
		ProcessInstance processInstance = 
				this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult() ; 
		BpmnModel bpmnModel = this.repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
		// 得到正在执行的环节
		List<String> activeIds = this.runtimeService.getActiveActivityIds(processInstance.getId());
		InputStream inputStream = new DefaultProcessDiagramGenerator().generateDiagram(bpmnModel, "png", activeIds,
				Collections.<String> emptyList(), this.processEngineConfiguration.getActivityFontName(),
				this.processEngineConfiguration.getLabelFontName(), null, 1.0);
		for(String s : activeIds)
		{
			System.out.println(s);
		}
		byte[] buffer = new byte[1024];
		int n = 0;
		while ((n = inputStream.read(buffer)) != -1)
		{
			outputStream.write(buffer, 0, n);
		}
		
	}
}
