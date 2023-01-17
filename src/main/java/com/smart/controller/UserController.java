package com.smart.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.smart.entities.Contact;
import com.smart.entities.User;
import com.smart.repositories.UserRepo;
import com.smart.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserRepo userRepo;
	
	@Value("${project.image}")
	private String path;

//	@PostMapping("/newUser")
//	public ResponseEntity<User> creatUser(@RequestBody User user){
//		User savedUser = this.userService.createUser(user);
//		return new ResponseEntity<User>(savedUser,HttpStatus.CREATED);
//	}

//	Method common data for response
//	@ModelAttribute
//	public void addCommonData(Model model, Principal principal) {
//		String userName = principal.getName();
//
//		User user = this.userRepo.findByEmail(userName);
//		model.addAttribute("user", user);
//	}

//	Dashboard home
	@RequestMapping("/index")
	public String userDashboard(Model model, Principal principal) {
		
		String userName = principal.getName();

		User user = this.userRepo.findByEmail(userName);
		model.addAttribute("user", user);

		model.addAttribute("title", "UserDashboard");
		return "normal/userdashboard";
	}

//	Open add form handler
	@GetMapping("/add-contact")
	public String openAddContactForm(Model model,Principal principal) {
		
		String userName = principal.getName();

		User user = this.userRepo.findByEmail(userName);
		model.addAttribute("user", user);
		
		
		model.addAttribute("title", "Add Contact");
		model.addAttribute("contact", new Contact());
		return "normal/add_contact_form";
	}

//	upload Image
	
	@PostMapping("/upload") public String uploadImage(Model model, @RequestParam("image") MultipartFile file) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(path, file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        model.addAttribute("msg", "Uploaded images: " + fileNames.toString());
        return "imageupload/index";
    }
	
}
