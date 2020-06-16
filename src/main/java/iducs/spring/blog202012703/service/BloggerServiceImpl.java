package iducs.spring.blog202012703.service;

import java.util.List;

import org.springframework.stereotype.Service;

import iducs.spring.blog202012703.domain.Blogger;
import iducs.spring.blog202012703.repository.BloggerRepository;

@Service
public class BloggerServiceImpl implements BloggerService {
	private BloggerRepository bloggerRepository;

	public BloggerServiceImpl(BloggerRepository bloggerRepository) {	
		this.bloggerRepository = bloggerRepository;
	}

	@Override
	public Blogger getBlogger(long id) {
		// TODO Auto-generated method stub
		Blogger data = new Blogger();
		data.setId(id);
		return bloggerRepository.findBlogger(data);
	}

	@Override
	public Blogger getUserByuId(String uId) {
		// TODO Auto-generated method stub
		
		return bloggerRepository.findById(uId);
	}

	@Override
	public Blogger getUserByEmail(String uEmail) {
		// TODO Auto-generated method stub
		return bloggerRepository.findByEmail(uEmail);
	}
	

	@Override
	public Blogger getUserByName(String uName) {
		// TODO Auto-generated method stub
		return bloggerRepository.findByName(uName);
	}

	@Override
	public List<Blogger> getBloggers() {
		// TODO Auto-generated method stub
		return bloggerRepository.findBloggers();
	}

	@Override
	public int postBlogger(Blogger blogger) {
		// TODO Auto-generated method stub
		return bloggerRepository.create(blogger);
	}

	@Override
	public int updateBlogger(Blogger blogger) {
		// TODO Auto-generated method stub
		return bloggerRepository.update(blogger);
	}

	@Override
	public int deleteBlogger(long id) {
		// TODO Auto-generated method stub
		return bloggerRepository.delete(id);
	}


}
