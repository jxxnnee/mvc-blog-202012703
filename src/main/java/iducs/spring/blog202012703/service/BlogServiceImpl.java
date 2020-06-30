package iducs.spring.blog202012703.service;

import java.util.List;

import org.springframework.stereotype.Service;

import iducs.spring.blog202012703.domain.Blog;
import iducs.spring.blog202012703.repository.BlogRepository;
import iducs.spring.blog202012703.utils.Pagination;

@Service
public class BlogServiceImpl implements BlogService {
	private BlogRepository blogRepository;

	public BlogServiceImpl(BlogRepository blogRepository) {	
		this.blogRepository = blogRepository;
	}

	@Override
	public Blog getBlog(long id) {
		// TODO Auto-generated method stub
		Blog blog = new Blog();
	    blog.setId(id);
		return blogRepository.read(blog);
	}

	@Override
	public List<Blog> getBlogs(Pagination pagination) {
		// TODO Auto-generated method stub
		return blogRepository.readList(pagination);
	}

	@Override
	public List<Blog> getBlogsByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Blog> getBlogByBlogger(String blogger) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Blog> getBlogByPage(int index, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int postBlog(Blog blog) {
		// TODO Auto-generated method stub
		int rows = blogRepository.create(blog);	
		return rows;
	}

	@Override
	public void putBlog(Blog blog) {
		// TODO Auto-generated method stub
		System.out.println(blog);
		blogRepository.update(blog);
	}

	@Override
	public int deleteBlog(long id) {
		// TODO Auto-generated method stub
		Blog blog = new Blog();
		blog.setId(id);
		int rows = blogRepository.delete(blog);
		
		return rows;
	}

	@Override
	public int getTotalRowCount() {
		// TODO Auto-generated method stub
		return blogRepository.readTotalRowCount();
	}

	@Override
	public int getTotalRowCountByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return blogRepository.readTotalRowCountByKeyword(keyword);
	}
}
