package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.UserModel;
import com.example.securitycustorm.SecurityUtils;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homeUser(Model model, @ModelAttribute("model") UserModel userModel, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getPrincipal() != "anonymousUser") {
			System.out.println("ok");
		}else {
			System.out.println("no");
		}
	
	   // String userName = SecurityUtils.getPrincipal().getFullName();
	   // model.addAttribute("userName", userName);
		return "user/list";
	}
	
}
