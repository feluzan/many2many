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

		userService.addFollow(user1,user2);
//		funcionarioRepository.save(new FuncionarioEntity(2, "Guilherme Araújo", 8000, 1));
//		funcionarioRepository.save(new FuncionarioEntity(3, "Marianna Santos", 10000, 2));
	}

}
