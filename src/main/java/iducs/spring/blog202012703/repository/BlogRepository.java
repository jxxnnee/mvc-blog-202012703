package iducs.spring.blog202012703.repository;

import java.util.List;

import iducs.spring.blog202012703.domain.Blog;

public interface BlogRepository {
	int create(Blog blog);
	Blog read(Blog blog);
	List<Blog> readList();
	int update(Blog blog);
	int delete(Blog blog);
}
