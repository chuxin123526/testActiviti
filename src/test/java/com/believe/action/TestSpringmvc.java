package com.believe.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestSpringmvc
{
	@RequestMapping("/test")
	public String test()
	{
		System.out.println("enter");
		return "/testView.jsp" ; 
	}
}
