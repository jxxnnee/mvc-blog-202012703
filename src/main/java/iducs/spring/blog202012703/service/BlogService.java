package iducs.spring.blog202012703.service;

import java.util.List;

import iducs.spring.blog202012703.domain.Blog;
import iducs.spring.blog202012703.utils.Pagination;

public interface BlogService {
	Blog getBlog(long id);
	
	List<Blog> getBlogs(Pagination pagination);
	List<Blog> getBlogsByTitle(String title);
	List<Blog> getBlogByBlogger(String blogger);
	List<Blog> getBlogByPage(int index, int size);
	
	int getTotalRowCount();
	int getTotalRowCountByKeyword(String keyword);
	int postBlog(Blog blog);
	void putBlog(Blog blog);
	int deleteBlog(long id);
}
