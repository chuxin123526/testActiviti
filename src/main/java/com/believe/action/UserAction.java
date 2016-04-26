package com.believe.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.believe.po.User;
import com.believe.service.IUserService;
import com.believe.service.impl.UserServiceImpl;

@Controller
@RequestMapping(value = "/userAction")
public class UserAction
{
	@Resource
	private IUserService userServiceImpl ; 
	
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest httpServletRequest, @RequestParam("userName") String userName) throws Exception 
	{
		User user = this.userServiceImpl.getByName(userName) ; 
		httpServletRequest.getSession().setAttribute("user", user);
		System.out.println(user);
		if(user != null)
		{
			System.out.println(user.getName());
		}
		
		return "redirect:/" ; 
	}
}
