package info.ashutosh.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

	@Override
	public User registerUser(UserRegistrationDto dto) {
		User user;

		user = new User(dto.getFirstName(), dto.getLastName(), dto.getUsername(), dto.getEmail(), bCryptPasswordEncoder.encode(dto.getPassword()), Arrays.asList(new Role("ROLE_USER")));

		return reposetory.save(user);
	}

	@Override
	public boolean[] checkUserAndEmailExistence(UserRegistrationDto dto) {
		Optional<User> findByUsername = reposetory.findByUsername(dto.getUsername());
		Optional<User> findByEmail = reposetory.findByEmail(dto.getEmail());

		boolean[] count = new boolean[3];
		if (findByEmail.isPresent()) {
			count[0] = true;

		}

		if (findByUsername.isPresent()) {
			count[1] = true;
		}

		if (count[0] & count[1]) {
			count[2] = true;
			return count;
		} else {
			return count;
		}
	}

	@Override
	public String checkUsername(String username) {
		Optional<User> findByUsername = reposetory.findByUsername(username);
		if (findByUsername.isPresent()) {
			return "true";
		} else {
			return "false";
		}
	}

}
