package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Test
	public void contextLoads() {
		User user;
		for (int i = 1; i <= 10; i++){
			user = i % 2 == 1 ? userRepository.findById(1L) : userRepository.findByIdFromTest2(2L);
			System.out.println(user.getId() + " :: " + user.getName());
		}
	}

}
