package iducs.spring.blog202012703.repository;

import java.util.List;

import iducs.spring.blog202012703.domain.Blogger;

public interface BloggerRepository {
	Blogger findBlogger(Blogger blogger);
	Blogger findById(String uId);
	Blogger findByEmail(String uEmail);
	Blogger findByName(String uName);
	
	List<Blogger> findBloggers();
	
	int create(Blogger blogger);
	int update(Blogger blogger);
	int delete(long id);
}
