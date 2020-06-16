package iducs.spring.blog202012703.service;

import java.util.List;

import iducs.spring.blog202012703.domain.Blogger;

public interface BloggerService {
	Blogger getBlogger(long id);
	Blogger getUserByuId(String uId);
	Blogger getUserByEmail(String uEmail);
	Blogger getUserByName(String uName);
	
	List<Blogger> getBloggers();
	
	int postBlogger(Blogger blogger);
	int updateBlogger(Blogger blogger);
	int deleteBlogger(long id);

}
