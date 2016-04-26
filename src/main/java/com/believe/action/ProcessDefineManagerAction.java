package com.believe.action;

import java.util.List;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.believe.po.Template;
import com.believe.service.ItemplateService;
import com.believe.util.FileUtil;


@Controller
@RequestMapping("/processDefineManagerAction")
public class ProcessDefineManagerAction
{
	@Resource
	private RepositoryService repositoryService ; 
	@Resource
	private ItemplateService templateServiceImpl ;
	
	@RequestMapping(value = "/list")
	public String list(Model model) throws Exception
	{
		//查询流程定义列表
		List<ProcessDefinition> processDefinitionList = this.repositoryService.createProcessDefinitionQuery().list() ; 
		model.addAttribute("processDefinitionList", processDefinitionList) ;
	
		return "/processDefineManager/list" ; 
	}
	
	@RequestMapping(value = "/addUI")
	public String addUI() throws Exception
	{
		return "processDefineManager/addUI" ; 
	}
	
	@RequestMapping(value = "/add")
	@ResponseBody
	public String add(@RequestParam("textfile") MultipartFile file) throws Exception 
	{
		System.out.println(file.getOriginalFilename());
		//部署流程定义
		String zipFileName = file.getOriginalFilename() ; 
		this.repositoryService.createDeployment()
		.addZipInputStream(new ZipInputStream(file.getInputStream()))
		.name(zipFileName)
		.deploy() ; 
	
		
		
		JSONObject jsonObject = new JSONObject() ; 
		jsonObject.put("info", "success") ; 
		return jsonObject.toString() ; 
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete(@RequestBody String json) throws Exception
	{
		System.out.println(json);
		JSONObject jsonObject = new JSONObject(json) ; 
		int id = jsonObject.getInt("id") ; 
		this.repositoryService.deleteDeployment(id + "" , true);
		JSONObject jsonObjectInfo = new JSONObject() ; 
		jsonObjectInfo.put("info", "success") ; 
		return jsonObjectInfo.toString() ; 
	}
	
	@RequestMapping(value = "/uploadTemplateUI")
	public String uploadTemplateUI(@RequestParam("deploymentId") String deploymentId , 
			Model model) throws Exception
	{
		model.addAttribute("deploymentId" , deploymentId) ;
		return "/processDefineManager/uploadTemplateUI" ; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/uploadTemplate")
	public String uploadTemplate(@RequestParam("file") MultipartFile file , 
			@RequestParam("deploymentId") String deploymentId) throws Exception
	{
		System.out.println("enter");
		System.out.println(deploymentId);
		System.out.println(file.getOriginalFilename());
		
		//保存模板
		String path = FileUtil.save(file) ; 
		//保存模板信息
		Template template = new Template() ; 
		template.setName(file.getOriginalFilename());
		template.setPath(path);
		template.setSize(file.getSize() + "");
		template.setDeploymentId(deploymentId);
		this.templateServiceImpl.add(template);
		
		JSONObject jsonObject = new JSONObject() ; 
		jsonObject.put("info", "success") ; 
		return jsonObject.toString() ; 
	}
	
}
