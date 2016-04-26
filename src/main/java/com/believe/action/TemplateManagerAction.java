package com.believe.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.believe.po.Template;
import com.believe.service.ItemplateService;
import com.believe.service.impl.TemplateServiceImpl;
import com.believe.util.FileUtil;

@Controller
@RequestMapping("/templateManagerAction")
public class TemplateManagerAction
{
	@Resource
	private ItemplateService templateServiceImpl ; 
	
	@RequestMapping("/list")
	public String list(Model model) throws Exception
	{
		List<Template> templateList = this.templateServiceImpl.list() ; 
		
		//向页面添加数据
		model.addAttribute("templateList" , templateList) ; 
		
		return "/templateManager/templateList" ; 
	}
	
	@RequestMapping("/addUI")
	public String addUI()
	{
		return "/templateManager/templateAddUI" ; 
	}
	
	@RequestMapping(value = "/delete" , method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestBody String requestBody ) throws Exception
	{
		System.out.println(requestBody);
		JSONObject jsonObject = new JSONObject(requestBody) ; 
		//删除源文件
		FileUtil.delete(this.templateServiceImpl.getById(jsonObject.getInt("id")).getPath());
		this.templateServiceImpl.delete(jsonObject.getInt("id"));
		JSONObject jsonObject2 = new JSONObject() ;
		jsonObject2.put("info", "success") ; 
		
		return jsonObject2.toString() ; 
	}
	
	/**
	 * 保存模板
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/add" ,method = RequestMethod.POST)
	public String add(@RequestParam("file") MultipartFile file) throws Exception
	{
		if(!file.isEmpty())
		{
			String path = FileUtil.save(file) ;
			
			//保存template
			Template template = new Template() ; 
			template.setName(file.getOriginalFilename());
			template.setPath(path) ; 
			template.setSize(file.getSize() + "");
			this.templateServiceImpl.add(template);
			System.out.println(file.getSize());
		}
		
		return "redirect:/" ; 
	}
	
	@RequestMapping(value = "/download")
	public void download(@RequestParam String id , HttpServletResponse response) throws Exception
	{
		Template template = this.templateServiceImpl.getById(Integer.parseInt(id)) ; 
		String path = template.getPath() ; 
		InputStream inputStream = new FileInputStream(path) ; 
		OutputStream outputStream = response.getOutputStream() ; 
		response.setHeader("Content-Disposition", "attachment; filename="+template.getName()+"");
		IOUtils.copy(inputStream, outputStream) ; 
	}
	
	@RequestMapping(value = "/updateUI")
	public String updateUI(@RequestParam String id , Model model) throws Exception
	{
		model.addAttribute("id" , id) ; 
		return "/templateManager/updateUI" ; 
	}
	
	@RequestMapping(value = "/update")
	public String update(@RequestParam("id") String id ,@RequestParam("file") MultipartFile file) throws Exception
	{
		if(file != null && !file.isEmpty())
		{
			System.out.println(file.getOriginalFilename());
			Template template = this.templateServiceImpl.getById(Integer.parseInt(id)) ; 
			//删除源文件
			FileUtil.delete(template.getPath());
			//保存新文件
			String path = FileUtil.save(file) ; 
			Template template2 = new Template() ; 
			template2.setId(Integer.parseInt(id));
			template2.setSize("" + file.getSize());
			template2.setName(file.getOriginalFilename());
			template2.setPath(path);
			//更新
			this.templateServiceImpl.update(template2);
		}
		return "redirect:/" ; 
	}
	 
}
