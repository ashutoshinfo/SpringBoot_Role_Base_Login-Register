package info.ashutosh.service;

import java.util.Arrays;

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
		User user = new User(dto.getFirstName(), dto.getLastName(), dto.getEmail(),
				bCryptPasswordEncoder.encode(dto.getPassword()), Arrays.asList(new Role("ROLE_USER")));

		return reposetory.save(user);
	}

}
