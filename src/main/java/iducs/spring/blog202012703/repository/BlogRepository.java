package iducs.spring.blog202012703.repository;

import java.util.List;

import iducs.spring.blog202012703.domain.Blog;
import iducs.spring.blog202012703.utils.Pagination;

public interface BlogRepository {
	int create(Blog blog);
	Blog read(Blog blog);
	List<Blog> readList(Pagination pagination);
	int update(Blog blog);
	int delete(Blog blog);
	int readTotalRowCount();
	int readTotalRowCountByKeyword(String keyword);
}
