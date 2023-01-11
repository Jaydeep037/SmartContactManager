package com.smart.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.entities.User;
import com.smart.payload.Message;
import com.smart.service.UserService;

@Controller
public class HomeController {
@Autowired UserService userService;
	
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home - Smart Contact Manager");
		return "home";
	}
	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "About - Smart Contact Manager");
		return "about";
	}
	
	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Signup - Smart Contact Manager");
		model.addAttribute("user",new User());
		return "signup";
	}
	
	@PostMapping("/do_register")
	public String registerUser(@Valid @ModelAttribute("user")User user,BeanPropertyBindingResult result,@RequestParam(value="agreement",defaultValue = "false")boolean agreement, 
			Model model,HttpSession session) {
		try {
			if(!agreement) {
				throw new Exception("You have not agreed the terms and conditions");
			}
			if(result.hasErrors()) {
				System.out.println("Error"+result.toString());
				model.addAttribute("user", user);
				return "signup";
			}
			User savedUser=this.userService.createUser(user);
			model.addAttribute("user",new User());
			session.setAttribute("message", new Message("Successfully registered !!", "alert-success"));
			return "signup";
		} catch (Exception e) {
			session.setAttribute("message", new Message("Something went to wrong !!"+e.getMessage(), "alert-danger"));
			model.addAttribute("user",user);
			return "signup";
		}
	}
}
