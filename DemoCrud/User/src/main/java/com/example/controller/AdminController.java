package com.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.RoleEntity;
import com.example.model.RoleModel;
import com.example.model.UserModel;
import com.example.securitycustorm.SecurityUtils;
import com.example.service.RoleService;
import com.example.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homeAdmin(Model model, @ModelAttribute("model") UserModel userModel, HttpServletRequest request) {
		List<UserModel> listModels = userService.findAll(userModel);
		userModel.setListResult(listModels);
		userModel.setTotalItems(userService.countTotalItem(userModel));
		double totalPage = (double) userModel.getTotalItems() / userModel.getMaxPageItems();
		userModel.setTotalPages((int) Math.ceil(totalPage));
		System.out.println((int) Math.ceil(totalPage));
		List<Integer> changesRecords = Arrays.asList(5, 10, 15);
		userModel.setChangeRecord(changesRecords);
		model.addAttribute("model", userModel);
		return "admin/list";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editUser(Model model, @PathVariable(value = "id", required = true) Long id) {
		UserModel userModel = userService.findOneById(id);
		model.addAttribute("model", userModel);
		model.addAttribute("roles", roleService.findAllRole());
		return "admin/detail";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addUser(Model model) {
		model.addAttribute("model", new UserModel());
		model.addAttribute("roles", roleService.findAllRole());
		return "admin/detail";
	}
	
	@RequestMapping(value = "/drop-drag", method = RequestMethod.GET)
	public String getUserByRole(Model model) {	
		List<RoleModel> models = roleService.findAllRoleByUser();
		model.addAttribute("roles", roleService.findAllRole());
		model.addAttribute("models", models);
		return "admin/dropdrag";
	}

	
}
