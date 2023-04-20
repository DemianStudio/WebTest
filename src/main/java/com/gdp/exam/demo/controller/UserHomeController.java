package com.gdp.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdp.exam.demo.service.MemberService;

@Controller
public class UserHomeController {
	@RequestMapping("/user/home/main")
	public String showMain() {
		return "user/home/main";
	}
	
	@RequestMapping("/")
	public String showRoot() {
		return "redirect:/user/home/main";
	}
}