package info.ashutosh;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import info.ashutosh.model.Role;
import info.ashutosh.model.User;
import info.ashutosh.reposetory.UserReposetory;

@SpringBootApplication
public class SpringBootCustomRegisterLoginApplication implements CommandLineRunner {

	@Autowired
	private UserReposetory reposetory;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCustomRegisterLoginApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		reposetory.save(new User("ADMIN", "ADMIN", "ADMIN", "ADMIN@ADMIN.ADMIN", bCryptPasswordEncoder.encode("ADMIN"), Arrays.asList(new Role("ROLE_ADMIN"),new Role("ROLE_USER"))));

	}

}
