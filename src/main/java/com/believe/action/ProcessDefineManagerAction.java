package com.believe.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
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
	private RepositoryService repositoryService;
	@Resource
	private ItemplateService templateServiceImpl;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private ProcessEngineConfiguration processEngineConfiguration ; 

	@RequestMapping(value = "/list")
	public String list(Model model) throws Exception
	{
		Logger logger = Logger.getLogger(this.getClass()) ; 
		logger.error("testERROR");
		logger.info("testINFO");
		logger.debug("testDEBUG");
		logger.warn("testWARN") ; 
		// 查询流程定义列表
		List<ProcessDefinition> processDefinitionList = this.repositoryService.createProcessDefinitionQuery().list();
		model.addAttribute("processDefinitionList", processDefinitionList);

		return "/processDefineManager/list";
	}

	@RequestMapping(value = "/addUI")
	public String addUI() throws Exception
	{
		return "processDefineManager/addUI";
	}

	@RequestMapping(value = "/add")
	@ResponseBody
	public String add(@RequestParam("textfile") MultipartFile file) throws Exception
	{
		System.out.println(file.getOriginalFilename());
		// 部署流程定义
		String zipFileName = file.getOriginalFilename();
		this.repositoryService.createDeployment().addZipInputStream(new ZipInputStream(file.getInputStream()))
				.name(zipFileName).deploy();

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("info", "success");
		return jsonObject.toString();
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete(@RequestBody String json) throws Exception
	{
		System.out.println(json);
		JSONObject jsonObject = new JSONObject(json);
		int id = jsonObject.getInt("id");
		this.repositoryService.deleteDeployment(id + "", true);
		//删除模板
		this.templateServiceImpl.deleteByDeploymentId(id + "") ; 

		JSONObject jsonObjectInfo = new JSONObject();
		jsonObjectInfo.put("info", "success");
		return jsonObjectInfo.toString();
	}

	@RequestMapping(value = "/uploadTemplateUI")
	public String uploadTemplateUI(@RequestParam("deploymentId") String deploymentId, Model model) throws Exception
	{
		model.addAttribute("deploymentId", deploymentId);
		return "/processDefineManager/uploadTemplateUI";
	}

	@RequestMapping(value = "/uploadTemplate")
	public void uploadTemplate(@RequestParam("file") MultipartFile file,
			@RequestParam("deploymentId") String deploymentId , HttpServletResponse response) throws Exception
	{
		System.out.println("enter");
		System.out.println(deploymentId);
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getContentType());
		JSONObject jsonObject = new JSONObject();
		if(!file.isEmpty())
		{
			if(FileUtil.check(file))
			{
				// 保存模板
				String path = FileUtil.save(file);
				// 保存模板信息
				Template template = new Template();
				template.setName(file.getOriginalFilename());
				template.setPath(path);
				template.setSize(file.getSize() + "");
				template.setDeploymentId(deploymentId);
				this.templateServiceImpl.add(template);
				jsonObject.put("info", "success");
			}
			else
			{
				System.out.println("contentType error");
				jsonObject.put("info", "文件类型错误") ;
			}
		}
		else
		{
			System.out.println("file is null");
			jsonObject.put("info", "文件不能为空") ; 
		}
		 response.setCharacterEncoding("UTF-8"); //设置编码格式  
         response.setContentType("text/html");   //设置数据格式  
         PrintWriter out = response.getWriter(); //获取写入对象  
         out.print(jsonObject.toString()); //将json数据写入流中  
         out.flush();  
	}

	@RequestMapping(value = "/seeProcessDiagram")
	public void seeProcessDiagram(OutputStream outputStream, @RequestParam("deploymentId") String deploymentId)
			throws Exception
	{
		ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery()
				.deploymentId(deploymentId).singleResult();
		String processDiagram = processDefinition.getDiagramResourceName();
		InputStream inputStream = this.repositoryService.getResourceAsStream(deploymentId, processDiagram);
		byte[] buffer = new byte[1024];
		int n = 0;
		while ((n = inputStream.read(buffer)) != -1)
		{
			outputStream.write(buffer, 0, n);
		}

//		ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery().list().get(0);
//		BpmnModel bpmnModel = this.repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
//
//		// 得到正在执行的环节
//		List<String> activeIds = this.runtimeService.getActiveActivityIds(processInstance.getId());
//		InputStream inputStream = new DefaultProcessDiagramGenerator().generateDiagram(bpmnModel, "png", activeIds,
//				Collections.<String> emptyList(), this.processEngineConfiguration.getActivityFontName(),
//				this.processEngineConfiguration.getLabelFontName(), null, 1.0);
//		
//		byte[] buffer = new byte[1024];
//		int n = 0;
//		while ((n = inputStream.read(buffer)) != -1)
//		{
//			outputStream.write(buffer, 0, n);
//		}

		inputStream.close();
		outputStream.close();
	}
	
	@RequestMapping(value = "downloadTemplate")
	public void downloadTemplate(@RequestParam("deploymentId") String deploymentId , 
			HttpServletResponse response) throws Exception
	{
		Template template = this.templateServiceImpl.getByDeploymentId(deploymentId) ; 
		String path = template.getPath() ; 
		File file = new File(path) ;  
		if(!file.exists())
		{
			throw new FileNotFoundException() ; 
		}
		InputStream inputStream = new FileInputStream(path) ; 
		System.out.println(path);
		System.out.println(template.getName());
		response.setHeader("Content-Disposition", "attachment; filename="+template.getName());
		OutputStream outputStream = response.getOutputStream() ; 
		IOUtils.copy(inputStream, outputStream) ; 
		
		//finilly
		inputStream.close();  
		outputStream.close();
	}

}
