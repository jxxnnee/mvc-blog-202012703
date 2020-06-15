package iducs.spring.blog202012703.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
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
	
	private SqlSession sqlSession;
	
	public BlogRepositoryImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	private static String namespace = "iducs.spring.blog202012703.mapper.blogMapper";
	@Override
	public int create(Blog blog) {
		int rows = 0;
		
		try {
			rows = sqlSession.insert(namespace + ".create", blog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rows;	
	}

	@Override
	public Blog read(Blog blog) {
		Blog data = new Blog();
		
		try {
			data = sqlSession.selectOne(namespace + ".read", blog.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}

	@Override
	public List<Blog> readList() {
		List<Blog> data = new ArrayList<>();
		
		try {
			data = sqlSession.selectList(namespace + ".readList");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}

	@Override
	public int update(Blog blog) {
		int rows = 0;
		try {
			rows = sqlSession.update(namespace + ".update", blog);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rows;
	}

	@Override
	public int delete(Blog blog) {
		int rows = 0;
		
		try {
			rows = sqlSession.delete(namespace + ".delete", blog.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rows;
	}
}
