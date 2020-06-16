package iducs.spring.blog202012703.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import iducs.spring.blog202012703.HomeController;
import iducs.spring.blog202012703.domain.Blogger;
import iducs.spring.blog202012703.service.BloggerService;

@Controller
public class BloggerController {
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private BloggerService bloggerService;
	public BloggerController(BloggerService bloggerService) {
		this.bloggerService = bloggerService;
	}
	
	
	///////////// GET MAPPINGS /////////////
	
	@GetMapping("/bloggers/all")
	public String getBlogs(Model model, Locale locale) {
		logger.info("GET BLOGGER LIST! The client locale is {}.", locale);
		List<Blogger> blogList = bloggerService.getBloggers();
		model.addAttribute("blogList", blogList);
		return "/bloggers/get-bloggers";
	}

	@GetMapping("/bloggers/{id}")
	public String getBlog(@PathVariable("id") Long id,
			Model model, Locale locale) {
		logger.info("GET BLOGGER INFO! The client locale is {}.", locale);
		model.addAttribute("blogger", bloggerService.getBlogger(id));
		return "/bloggers/info-form";
	}
	
	@GetMapping("/bloggers/new")
	public String newBlog(Model model) {
		return "/bloggers/new-form";
	}
	
	@GetMapping("/bloggers/login") 
    public String loginForm(Model model) throws Exception {    
        return "/bloggers/login-form";
    }
    
    @GetMapping("/bloggers/logout") 
    public String logoutBlogger(HttpSession session, Model model) throws Exception {
    	session.invalidate();
    	return "redirect:/";
    }
    
    @GetMapping("/bloggers/edit") // 정보 확인과 수정을 구분하는 경우만 사용함
    public String editBlog(@RequestParam(name="id") long id, Model model) throws Exception {
    	Blogger blogger = bloggerService.getBlogger(id);
        model.addAttribute("blogger", blogger);
        return "/bloggers/edit-form";
    }
    
    @GetMapping("/bloggers/delete") 
    public String deleteBlog(@RequestParam(name="id") long id, Model model) throws Exception {
    	Blogger blogger = bloggerService.getBlogger(id);
        model.addAttribute("blogger", blogger);
        return "/bloggers/delete-form";
    }
	
	
	///////////// POST MAPPINGS /////////////
	
	@PostMapping("/bloggers")
	@Transactional
	public String postBlog( 
			@RequestParam final String uId, 
			@RequestParam final String uPw,
			@RequestParam final String uName,
			@RequestParam final String uEmail,
			Model model, Locale locale) throws IllegalStateException, IOException {	
		logger.info("CREATE NEW BLOGGER! The client locale is {}.", locale);
		
		Blogger data = new Blogger();
		data.setuId(uId);
		data.setuPw(uPw);
		data.setuName(uName);
		data.setuEmail(uEmail);
		
		bloggerService.postBlogger(data);
		return "redirect:" + "/";
	}
	
	@PostMapping("/bloggers/login")
	public String loginBlogger(
			@RequestParam final String uId, 
			@RequestParam final String uPw,
			HttpSession session,
			HttpServletRequest request,
			Model model) throws Exception {
		Blogger blogger = bloggerService.getUserByuId(uId);
		if(blogger != null && blogger.getuPw().equals(uPw)) {
    		session.setAttribute("blogger", blogger);
    		
    		System.out.println(session.getAttribute("uri").toString());
    		return "redirect:" + session.getAttribute("uri").toString();
    	}
    	else {
    		return "redirect:/bloggers/error";
    	}	
	}
	
	@PutMapping("/bloggers/{id}") 
    @Transactional
    public String updateBlogger(
    		@PathVariable long id,
    		@RequestParam final String uId, 
			@RequestParam final String uPw,
			@RequestParam final String uName,
			@RequestParam final String uEmail,
			Model model) throws IllegalStateException, IOException {
		Blogger data = new Blogger();
		data.setId(id);
		data.setuId(uId);
		data.setuPw(uPw);
		data.setuName(uName);
		data.setuEmail(uEmail);
    	
		int rows = bloggerService.updateBlogger(data);
		if (rows > 0 ) {
			return "redirect:/bloggers/" + id;
		} else {
			return "redirect:/bloggers/error";
		}
	}
	
	@DeleteMapping("/bloggers/{id}")
    public String deleteBlog(@PathVariable long id, HttpSession session, Model model) throws Exception {
    	int count = bloggerService.deleteBlogger(id);
    	if(count > 0) {
    		session.invalidate();
    		return "redirect:/";
    	}
    	else     		
    		return "redirect:/bloggers/error";
    } 
	
	
	
}
