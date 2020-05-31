package iducs.spring.blog202012703.controller;


import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import iducs.spring.blog202012703.domain.Blog;
import iducs.spring.blog202012703.service.BlogService;

@Controller
public class BlogController {
	private String uploadPath;
	
	private BlogService blogService;

	private String uploadpath;
	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}
	
	@GetMapping("/blogs/all")
	public String getBlogs(Model model) {
		List<Blog> blogList = blogService.getBlogs();
		model.addAttribute("blogList", blogList);
		return "/blogs/get-blogs";
	}

	@GetMapping("/blogs/{id}")
	public String getBlog(@PathVariable("id") Long id,
			Model model) {
		model.addAttribute("blog", blogService.getBlog(id));
		return "/blogs/get-blog";
	}
	
	@GetMapping("/blogs/new")
	public String newBlog(Model model) {
		return "/blogs/new-form";
	}

	@PostMapping("/blogs")
	@Transactional
	public String postBlog( 
			MultipartHttpServletRequest request,
//			@RequestParam final String title, 
//			@RequestParam final String content,
//			@RequestParam("filepath") MultipartFile file,
//			@RequestParam final String blogger,
//			@RequestParam final Timestamp regDateTime,
			Model model) throws IllegalStateException, IOException {
		

		Blog blog = new Blog();
		blog.setTitle(request.getParameter("title"));
		blog.setContent(request.getParameter("content"));
		blog.setBlogger(request.getParameter("blogger"));
		blog.setRegDateTime(java.sql.Timestamp.valueOf(request.getParameter("regDateTime")));
		
		uploadpath = this.getClass().getResource("/").getPath() + "..\\..\\resources\\files";
		MultipartFile file = request.getFile("filepath");
		
		if (!file.getOriginalFilename().isEmpty()) {
	         file.transferTo(new File(uploadPath, file.getOriginalFilename()));
	         blog.setFilepath(file.getOriginalFilename());
	    } else {
	        System.out.println("FILE FALIE");
	    }
		
		blogService.postBlog(blog);
		return "redirect:/blogs/all";
	}
	
	@GetMapping("/blogs/edit/{id}")
	public String editBlog(@PathVariable("id") Long id,
			Model model) {
		model.addAttribute("blog", blogService.getBlog(id));
		return "/blogs/update-blog";
	}
	
	@PostMapping("/blogs/{id}")
	@Transactional
	public String putBlog(@PathVariable("id") Long id, 
			@RequestParam final String title, 
			@RequestParam final String content,
			@RequestParam("filepath") MultipartFile file,
			@RequestParam final String blogger,
			@RequestParam final Timestamp regDateTime,
			Model model) throws IllegalStateException, IOException {
		
		Blog blog = new Blog();
		blog.setId(id);
		blog.setTitle(title);
		blog.setContent(content);
		blog.setBlogger(blogger);
		blog.setRegDateTime(regDateTime);

		uploadpath = this.getClass().getResource("/").getPath() + "..\\..\\resources\\files";
		
		if (!file.getOriginalFilename().isEmpty()) {
	         file.transferTo(new File(uploadPath, file.getOriginalFilename()));
	         blog.setFilepath(file.getOriginalFilename());
	    } else {
	        System.out.println("FILE FALIE");
	    }
		
		blogService.putBlog(blog);
		
		return "redirect:/blogs/all";
	}
	
	
//	Blog blog = new Blog();
//	blog.setId(id);
//	blog.setTitle(title);
//	blog.setContent(content);
//	blog.setFilepath(filepath);
//	blog.setBlogger(blogger);
//	blog.setRegDateTime(regDateTime);
	
}
