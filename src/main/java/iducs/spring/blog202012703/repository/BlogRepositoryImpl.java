package iducs.spring.blog202012703.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import iducs.spring.blog202012703.domain.Blog;

public class BlogRepositoryImpl implements BlogRepository {
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public BlogRepositoryImpl(SimpleDriverDataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public int create(Blog blog) {
		int rows = 0;
		String sql = "INSERT INTO blog(title, content, filepath, blogger, reg_date_time)"
				+ "VALUES(:title, :content, :filepath, :blogger, :regDateTime)";
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(blog);
		rows = jdbcTemplate.update(sql, params);
		
		return rows;	
	}

	@Override
	public Blog read(Blog blog) {
		String sql = "select * from blog where id = :id";
		Map<String, Integer> params = Collections.singletonMap("id", (int) blog.getId());
//		List<Blog> blogList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Blog>(Blog.class));
		Blog alog = jdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<Blog>(Blog.class));
		System.out.println(alog.getFilepath());
		
		return alog;
	}

	@Override
	public List<Blog> readList() {
		String sql = "select * from blog order by id desc";
		List<Blog> blogList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Blog>(Blog.class));
		
		return blogList;
	}

	@Override
	public int update(Blog blog) {
		int rows = 0;
		try {
			String sql = "update blog set title=:title, content=:content, filepath=:filepath, blogger=:blogger," 
					+ "reg_date_time=:regDateTime where id=:id";
			BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(blog);
			rows = jdbcTemplate.update(sql, params);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rows;
	}

	@Override
	public int delete(Blog blog) {
		int rows = 0;

		String sql = "delete from blog where id=:id";
		Map<String, Integer> params = Collections.singletonMap("id", (int) blog.getId());
		
		rows = jdbcTemplate.update(sql, params);

		return rows;
	}
}
