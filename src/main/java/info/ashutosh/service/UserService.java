package info.ashutosh.service;

import info.ashutosh.model.User;
import info.ashutosh.transferobject.dto.UserRegistrationDto;

public interface UserService {
	User registerUser(UserRegistrationDto dto);

	boolean[] checkUserAndEmailExistence(UserRegistrationDto dto);

	String checkUsername(String username);

}
