package info.ashutosh.service;

import org.springframework.web.multipart.MultipartFile;

import info.ashutosh.model.User;
import info.ashutosh.transferobject.dto.UserRegistrationDto;

public interface UserService {
	User registerUser(UserRegistrationDto dto);

	boolean[] checkUserAndEmailExistence(UserRegistrationDto dto);

	boolean saveImage(MultipartFile multipartFile);

	// String checkUsername(String username);

}
