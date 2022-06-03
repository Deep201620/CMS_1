package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Role;
import com.example.demo.entity.Subject;
import com.example.demo.entity.Chapter;
import com.example.demo.entity.JwtRequest;
import com.example.demo.entity.JwtResponse;
import com.example.demo.entity.User;
import com.example.demo.service.ChapterService;
import com.example.demo.service.JwtService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import com.example.demo.service.SubjectService;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired 
    private JwtService jwtService;
    
    
    @Autowired 
    private SubjectService subjectService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private ChapterService chapterService;
    
    

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }
    

//    @PostMapping({"/registerNewUser"})
//    @PreAuthorize("hasRole('Admin')")
//    public User registerNewUser(@RequestBody User user) {
//        return userService.registerNewUser(user);
//    }
//
// 
    
    // Mine added code
    
	@GetMapping("/")
//	@ResponseBody
	public String login() {
		return "login";
		
	}
	
//	@GetMapping("/admin")
//	@PreAuthorize("hasRole('Admin')")
//	public String admin() {
//		return "admin";
//	}
//	
//	@GetMapping("/registerUser")
////	@PreAuthorize("hasRole('Admin')")
//		public String addUser(Model model) {
//		List<Role> roles = roleService.getAllRoles();
//		model.addAttribute("roles", roles);
//		model.addAttribute("role", new Role());
//			return "registerUser";
//		}
	

	@PostMapping(value="/addUser")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/deleteUser/{Id}")
	@PreAuthorize("hasRole('Admin')")
	@ResponseBody
	public String deleteUser(@PathVariable("Id") String Id){
		return userService.deleteUser(Id);
	}

//	@PostMapping("/adminLogin")
//	public String adminLogin() {
//		
//	}
	
//	@GetMapping("/addSubjectForm")
//	@PreAuthorize("hasRole('Admin')")
//	public String addsubjectForm() {
//		return "addsubjectForm";
//	}
	
	@PostMapping(value="/addSubject")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Subject> saveSubject(@RequestBody Subject subject){
		return new ResponseEntity<Subject>(subjectService.saveSubject(subject), HttpStatus.CREATED);
	}
	
	
//	@GetMapping("/showSubjects")
//	public String getAllSubjects(Model model) {
//		List<Subject> subjects =  subjectService.getAllSubjects();
//		model.addAttribute("subjects", subjects);
//		return "showSubjects";
//	}
	
	@DeleteMapping("/delSubject/{subjectId}")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<String> deleteSubject(@PathVariable("subjectId") long subjectId){
		subjectService.deleteSubject(subjectId);
		return new ResponseEntity<String>("Subject deleted successfully",HttpStatus.OK);
	}
	
	@PostMapping("/addChapter")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Chapter> addCourse(@RequestBody Chapter chapter){
		return new ResponseEntity<Chapter>(chapterService.addChapter(chapter), HttpStatus.CREATED);
	}
	
    
	@DeleteMapping("/delChapter/{chapterId}")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<String> deleteChapter(@PathVariable("chapterId") long chapterId){
		chapterService.deleteChapter(chapterId);
		return new ResponseEntity<String>("Chapter deleted successfully",HttpStatus.OK);
	}
	
}
