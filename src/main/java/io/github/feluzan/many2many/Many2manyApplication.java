package io.github.feluzan.many2many;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.ArrayList;

@SpringBootApplication
public class Many2manyApplication implements CommandLineRunner {

	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(Many2manyApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		User user1 = new User();
		user1.setUsername("user1");
		user1 = userService.save(user1);
		user1 = userService.findById(user1.getId());

		User user2 = new User();
		user2.setUsername("user2");
		user2 = userService.save(user2);
		user2 = userService.findById(user2.getId());

		User user3 = new User();
		user3.setUsername("user3");
		user3 = userService.save(user3);
		user3 = userService.findById(user3.getId());

		User user4 = new User();
		user4.setUsername("user4");
		user4 = userService.save(user4);
		user4 = userService.findById(user4.getId());

		userService.addFriendship(user1,user2);
		userService.addFriendship(user2,user3);
		userService.addFriendship(user4,user2);
		userService.addFriendship(user3,user4);
	}

}
