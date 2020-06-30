package iducs.spring.blog202012703.controller;


import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import iducs.spring.blog202012703.HomeController;
import iducs.spring.blog202012703.domain.Blog;
import iducs.spring.blog202012703.service.BlogService;
import iducs.spring.blog202012703.utils.CommonExceptionAdvice;
import iducs.spring.blog202012703.utils.Pagination;

@Controller
public class BlogController {
	private String IMAGE_PATH = "/Users/ooddymac/mvc-blog-202012703/src/main/webapp/resources/files/";
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private BlogService blogService;
	private CommonExceptionAdvice commonException;
	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}
	
	@GetMapping("/blogs/all")
	public String getBlogs(@RequestParam(value="curPage", required=false) Integer curPage, 
						@RequestParam(value="keyword", required=false) String keyword,
						@RequestParam(value="orderBy", required=false) String orderBy,
						Model model, Locale locale) {
		String order = orderBy != null ? orderBy : "DESC";
		String word = keyword != null ? keyword : "";
		int page = curPage != null ? curPage : 1;
		Pagination pagination = new Pagination(page, 3, 3, word);
		
		
		if (word != "" && word != null) {
			
			try {
				pagination.setTotalRowCount(blogService.getTotalRowCountByKeyword(word));
				logger.info("GET BLOG SEARCH LIST! The client locale is {}.", locale);
				List<Blog> blogList = blogService.getBlogs(pagination);
				model.addAttribute("blogList", blogList);
				model.addAttribute("pagination", pagination);
				
				return "/blogs/get-search-blogs";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return commonException.errorException(model, e);
			}
		} else {
			
			try {
				pagination.setTotalRowCount(blogService.getTotalRowCount());
				logger.info("GET BLOG LIST! The client locale is {}.", locale);
				List<Blog> blogList = blogService.getBlogs(pagination);
				model.addAttribute("blogList", blogList);
				model.addAttribute("pagination", pagination);
				
				return "/blogs/get-blogs";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return commonException.errorException(model, e);
			}
		}
	}

	@GetMapping("/blogs/{id}")
	public String getBlog(@PathVariable("id") Long id,
			Model model, Locale locale) {
		logger.info("GET BLOG INFO! The client locale is {}.", locale);
		try {
			model.addAttribute("blog", blogService.getBlog(id));
			return "/blogs/get-blog";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return commonException.errorException(model, e);
		}
	}
	
	@GetMapping("/blogs/new")
	public String newBlog(Model model) {
		return "/blogs/new-form";
	}

	@PostMapping("/blogs")
	@Transactional
	public String postBlog( 
			@RequestParam final String title, 
			@RequestParam final String content,
			@RequestParam("filepath") MultipartFile file,
			@RequestParam final String blogger,
			@RequestParam final Timestamp regDateTime,
			Model model, Locale locale) throws IllegalStateException, IOException {	
		logger.info("CREATE BLOG POST! The client locale is {}.", locale);
		
		Blog blog = new Blog();
		blog.setTitle(title);
		blog.setContent(content);
		blog.setBlogger(blogger);
		blog.setRegDateTime(regDateTime);	
		blog = saveImage(blog, file);
		
		try {
			blogService.postBlog(blog);
			return "redirect:/blogs/all";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return commonException.errorException(model, e);
		}
	}
	
	@GetMapping("/blogs/edit/{id}")
	public String editBlog(@PathVariable("id") Long id,
			Model model) {
		try {
			model.addAttribute("blog", blogService.getBlog(id));
			return "/blogs/update-blog";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return commonException.errorException(model, e);
		}
	}
	
	@PostMapping("/blogs/{id}")
	@Transactional
	public String putBlog(@PathVariable("id") Long id, 
			@RequestParam final String title, 
			@RequestParam final String content,
			@RequestParam("filepath") MultipartFile file,
			@RequestParam final String blogger,
			@RequestParam final Timestamp regDateTime,
			Model model, Locale locale) throws IllegalStateException, IOException {
		logger.info("UPDATE BLOG POST! The client locale is {}.", locale);
		
		Blog blog = new Blog();
		blog.setId(id);
		blog.setTitle(title);
		blog.setContent(content);
		blog.setBlogger(blogger);
		blog.setRegDateTime(regDateTime);
		blog = saveImage(blog, file);
		
		try {
			blogService.putBlog(blog);
			return "redirect:/blogs/all";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return commonException.errorException(model, e);
		}
	}
	
	@GetMapping("/blogs/delete/{id}")
	public String deleteBlog(@PathVariable("id") Long id,
			Model model, Locale locale) {
		logger.info("DELETE BLOG POST! The client locale is {}.", locale);
		
		try {
			int rows = blogService.deleteBlog(id);
			
			if (rows >= 1) return "redirect:/blogs/all";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return commonException.errorException(model, e);
		}
		
		return "redirect:/blogs/error";
	}
	
	
	public Blog saveImage(Blog blog, MultipartFile file) {
	
		String safeFile = IMAGE_PATH + file.getOriginalFilename();
		
		if (!file.getOriginalFilename().isEmpty()) {
	         try {
				file.transferTo(new File(safeFile));
				 blog.setFilepath(file.getOriginalFilename());
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    } else {
	        System.out.println("FILE FALIE");
	        
	        return blog;
	    }
		
		return blog;
	}
	
	
//	Blog blog = new Blog();
//	blog.setId(id);
//	blog.setTitle(title);
//	blog.setContent(content);
//	blog.setFilepath(filepath);
//	blog.setBlogger(blogger);
//	blog.setRegDateTime(regDateTime);
	
}
