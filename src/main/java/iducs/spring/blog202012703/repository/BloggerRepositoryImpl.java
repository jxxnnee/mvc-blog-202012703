package iducs.spring.blog202012703.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import iducs.spring.blog202012703.domain.Blogger;

public class BloggerRepositoryImpl implements BloggerRepository {
	private SqlSession sqlSession;
	private static String namespace = "iducs.spring.blog202012703.mapper.bloggerMapper";
	
	public BloggerRepositoryImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public Blogger findBlogger(Blogger blogger) {
		// TODO Auto-generated method stub
		
		Blogger data = new Blogger();
		
		try {
			data = sqlSession.selectOne(namespace + ".getBlogger", blogger.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}

	@Override
	public Blogger findById(String uId) {
		// TODO Auto-generated method stub
		Blogger data = new Blogger();
		
		try {
			data = sqlSession.selectOne(namespace + ".getUserByuId", uId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public Blogger findByEmail(String uEmail) {
		// TODO Auto-generated method stub
		Blogger data = new Blogger();
		
		try {
			data = sqlSession.selectOne(namespace + ".getUserByEmail", uEmail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}

	@Override
	public Blogger findByName(String uName) {
		// TODO Auto-generated method stub
		Blogger data = new Blogger();
		
		try {
			data = sqlSession.selectOne(namespace + ".getUserByName", uName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}

	@Override
	public List<Blogger> findBloggers() {
		// TODO Auto-generated method stub
		List<Blogger> data = new ArrayList<>();
		
		try {
			data = sqlSession.selectOne(namespace + ".getbloggerList");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}

	@Override
	public int create(Blogger blogger) {
		// TODO Auto-generated method stub
		int rows = 0;
		
		try {
			rows = sqlSession.insert(namespace + ".create", blogger);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rows;
	}

	@Override
	public int update(Blogger blogger) {
		// TODO Auto-generated method stub
		int rows = 0;
		
		try {
			rows = sqlSession.update(namespace + ".update", blogger);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		int rows = 0;
		
		try {
			rows = sqlSession.delete(namespace + ".delete", id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rows;
	}

}
