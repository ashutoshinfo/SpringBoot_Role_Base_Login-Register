package info.ashutosh.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import info.ashutosh.model.Role;
import info.ashutosh.model.User;
import info.ashutosh.reposetory.UserReposetory;
import info.ashutosh.transferobject.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserReposetory reposetory;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	Environment environment;
	
	@Autowired
	Connection connection;

	@Override
	public User registerUser(UserRegistrationDto dto) {
		User user;

		user = new User(dto.getFirstName(), dto.getLastName(), dto.getUsername(), dto.getEmail(), bCryptPasswordEncoder.encode(dto.getPassword()), dto.getImage(), Arrays.asList(new Role("ROLE_USER")));

		return reposetory.save(user);
	}

	@Override
	public boolean[] checkUserAndEmailExistence(UserRegistrationDto dto) {
		Optional<User> findByUsername = reposetory.findByUsername(dto.getUsername());
		Optional<User> findByEmail = reposetory.findByEmail(dto.getEmail());
		System.out.println("++++++++++++++1");

		boolean[] count = new boolean[3];
		if (findByEmail.isPresent()) {
			System.out.println("++++++++++++++2");
			count[0] = true;
		}

		if (findByUsername.isPresent()) {
			System.out.println("++++++++++++++3");
			count[1] = true;
		}

		if (count[0] & count[1]) {
			System.out.println("++++++++++++++4");
			count[2] = true;
			return count;
		}
		return count;

	}

	@Override
	public boolean saveImage(MultipartFile multipartFile) {

		StringBuffer stringBuffer = new StringBuffer();
		Path path = Paths.get(environment.getProperty("user.dir") + "\\MyPhotos", multipartFile.getOriginalFilename());
		stringBuffer.append(path);

		try {
			Files.write(path, multipartFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			return true;
		}
		return false;

	}

	/*
	 * @Override public String checkUsername(String username) { Optional<User>
	 * findByUsername = reposetory.findByUsername(username); if
	 * (findByUsername.isPresent()) { return "true"; } else { return "false"; } }
	 */

}
