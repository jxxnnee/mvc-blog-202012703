package iducs.spring.blog202012703.service;

import java.util.List;

import iducs.spring.blog202012703.domain.Blog;

public interface BlogService {
	Blog getBlog(long id);
	
	List<Blog> getBlogs();
	List<Blog> getBlogsByTitle(String title);
	List<Blog> getBlogByBlogger(String blogger);
	List<Blog> getBlogByPage(int index, int size);

	int postBlog(Blog blog);
	void putBlog(Blog blog);
	void deleteBlog(long id);
}
