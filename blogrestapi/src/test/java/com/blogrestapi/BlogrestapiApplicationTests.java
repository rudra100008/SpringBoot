package com.blogrestapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blogrestapi.Dao.UserDao;

@SpringBootTest
class BlogrestapiApplicationTests {
	@Autowired
	private UserDao userDao;

	@Test
	void contextLoads() {
	}

	@Test
	public void repoTest()
	{
		String className=this.userDao.getClass().getName();
		String packageName=this.userDao.getClass().getPackageName();
		System.out.println("\n\n\nClass Name: "+className+"\nPackage Name: "+packageName+"\n\n");
	}

}
